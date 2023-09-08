<template>
    <div>
        <div style="width: 90%; margin: 0 auto;">
            <DataTable :value="state[currentDay].foods" tableStyle="min-width: 50rem">
                <Column field="name" header="Name" style="width: 20%"></Column>
                <Column field="calories" header="Calories" style="width: 20%"></Column>
                <Column field="protein" header="Protein (g)" style="width: 20%"></Column>
                <Column field="fat" header="Fat (g)" style="width: 20%"></Column>
                <Column field="carbs" header="Carbs (g)" style="width: 20%"></Column>
                <Column field="servings" header="Servings" style="width: 20%"></Column>
                <Column>
                    <template #body="slotProps">
                        <span class="p-buttonset">
                            <Button type="submit" label="Delete" @click="deleteFood(slotProps.index)" />
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
            <div id="macros">
                <p>Total Caloires {{ state[currentDay].totalCalories }}</p>
                <p>Total Carbs {{ state[currentDay].totalCarbs }}g</p>
                <p>Total Protein {{ state[currentDay].totalProtein }}g</p>
                <p>Total Fat {{ state[currentDay].totalFat }}g</p>
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

const store = inject('store')
let search = "";
let state = reactive(store.foodJournal.value)
const dialog = useDialog();
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
    const clientDate = new Date().toLocaleString('sv', { timeZoneName: 'short' }).slice(0, 10)
    for (let i = 0; i < state.length; i++) {
        let entryDate = new Date(state[i].creationDate).toISOString().replace(/T.*/, '').split('-').join('-')
        if (clientDate == entryDate) {
            today = i
            return today
        }
    }
    return today
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
            state[currentDay].totalCarbs += options.data.carbs
            state[currentDay].totalProtein += options.data.protein
            state[currentDay].totalFat += options.data.fat
            state[currentDay].totalCalories += options.data.calories
            state[currentDay].foods.push(options.data)
        }
    });
}


function deleteFood(index) {
    state[currentDay].totalCarbs -= state[currentDay].foods[index].carbs
    state[currentDay].totalProtein -= state[currentDay].foods[index].protein
    state[currentDay].totalFat -= state[currentDay].foods[index].fat
    state[currentDay].totalCalories -= state[currentDay].foods[index].calories
    state[currentDay].foods.splice(index, 1)
}

function updateEntry() {
    store.methods.updateFoodJournalEntry(state[currentDay]);
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

#search{
    position: relative;
    top: 10px;
}

#update {
    top: 50px;
}

#macros{
    position: absolute;
    left: 125px;
}
</style>