<template>
    <div>
        <div style="width: 90%; margin: 0 auto;">
            <DataTable :value="state[currentDay].foods" tableStyle="min-width: 50rem">
                <Column field="name" header="Name" style="width: 20%"></Column>
                <Column field="servings" header="Servings" style="width: 20%"></Column>
                <Column field="calories" header="Calories" style="width: 20%"></Column>
                <Column field="protein" header="Protein (g)" style="width: 20%"></Column>
                <Column field="fat" header="Fat (g)" style="width: 20%"></Column>
                <Column field="carbs" header="Carbs (g)" style="width: 20%"></Column>
                <Column>
                    <template #body="slotProps">
                        <span class="p-buttonset">
                            <Button icon="pi pi-pencil" severity="success" @click="changeServings(slotProps.data)" />
                            <Button icon="pi pi-trash" severity="danger"
                                @click="deleteFood(slotProps.index, slotProps.data)" />
                        </span>
                    </template>
                </Column>
            </DataTable>
            <div id="searchBar">
                <div class="row g-3">
                    <div class="col-auto">
                        <form>
                            <input id="search" v-model="search" placeholder="Search Food">
                        </form>
                    </div>
                    <div class="col-auto">
                        <Button type="submit" icon="pi pi-search" severity="secondary" text rounded aria-label="Search"
                            @click="addFood()" />
                    </div>
                </div>
            </div>
            <div>
                <Button id="update" type="submit" label="Update journal" @click="updateEntry()" />
            </div>
            <div id="macros" class="row g-3">
                <div class="col-auto">
                    <label for="cals">Calories</label>
                    <h4 id="cals">{{ state[currentDay].totalCalories }}</h4>
                </div>
                <div class="col-auto">
                    <label for="fat">Fat</label>
                    <h4 id="fat">{{ state[currentDay].totalFat }}g</h4>
                </div>
                <div class="col-auto">
                    <label for="protein">Protein</label>
                    <h4 id="protein">{{ state[currentDay].totalProtein }}g</h4>
                </div>
                <div class="col-auto">
                    <label for="carbs">Carbs</label>
                    <h4 id="carbs">{{ state[currentDay].totalCarbs }}g</h4>

                </div>
            </div>
            <DynamicDialog />
        </div>
    </div>
</template>

<!-- Stores the days entry in state and then updates the state
    to the backend when the user presses the update button -->
<script setup>
import Button from 'primevue/button';
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { useDialog } from 'primevue/usedialog';
import { inject, onMounted, provide, reactive } from 'vue';
import FoodSearch from '../components/FoodSearch.vue';
import Servings from '../components/Servings.vue';

const store = inject('store')
let search = "";
let state = reactive(store.foodJournal.value)
const dialog = useDialog();
// Defaults to today, could be modified to fetch previous entries if implemented
let currentDay = getToday()

provide('dialog', dialog);



onMounted(() => {
    store.methods.loadFood()
    store.methods.loadFoodJournal(store.userState.person.id)
    console.log(state)
    console.log(state[currentDay])
})


function getToday() {
    let today = 0
    // yyyy-mm-dd
    const clientDate = new Date().toLocaleString('sv', { timeZoneName: 'short' }).slice(0, 10)
    for (let i = 0; i < state.length; i++) {
        // yyyy-mm-dd
        let entryDate = new Date(state[i].creationDate).toISOString().replace(/T.*/, '').split('-').join('-')
        if (clientDate == entryDate) {
            today = i
            return today
        }
    }
    return today
}

function changeServings(food) {
    // necessary if user changes the serving more than once on an item.
    resetFood(food)
    dialog.open(Servings, {
        props: {
            header: 'Search Results',
            style: {
                width: '40vw',
            },
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            modal: true,
        },
        onClose(size) {
            food.servings = size.data
            calcFoodMacros(food)
            addToTotal(food)
        }
    });
}

Food(food) {
    subFromTotal(food)

    food.fat /= food.servings
    food.protein /= food.servings
    food.carbs /= food.servings
    food.calories = ((food.fat * 9) + (food.protein * 4) + (food.carbs * 4))


}

function calcFoodMacros(food) {
    food.fat *= food.servings
    food.protein *= food.servings
    food.carbs *= food.servings
    // assume calories formula is fat * 9 + protein * 4 + carbs * 4
    food.calories = ((food.fat * 9) + (food.protein * 4) + (food.carbs * 4))
}


function addFood() {
    dialog.open(FoodSearch, {
        props: {
            header: 'Search Results',
            style: {
                width: '40vw',
            },
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            modal: true,
        },
        data: { search },
        // Return data from dialog
        onClose(options) {
            addToTotal(options.data)
            state[currentDay].foods.push(options.data)
        }
    });
}


function deleteFood(index, food) {
    subFromTotal(food)
    state[currentDay].foods.splice(index, 1)
}

function updateEntry() {
    store.methods.updateFoodJournalEntry(state[currentDay]);
}

function addToTotal(food) {
    state[currentDay].totalCarbs += food.carbs
    state[currentDay].totalProtein += food.protein
    state[currentDay].totalFat += food.fat
    state[currentDay].totalCalories += food.calories
}

function subFromTotal(food) {
    state[currentDay].totalCarbs -= food.carbs
    state[currentDay].totalProtein -= food.protein
    state[currentDay].totalFat -= food.fat
    state[currentDay].totalCalories -= food.calories
}

</script>

<style scoped>
#searchBar {
    position: absolute;
    right: 125px;
    padding-top: 50px;
    padding-right: 30px;
    padding-bottom: 50px;
    padding-left: 80px;
}

#search {
    position: relative;
    top: 10px;
}

#update {
    top: 50px;
}

#macros {
    position: relative;
    width: 500px;
}
</style>

