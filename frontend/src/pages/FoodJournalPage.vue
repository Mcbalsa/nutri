<template>
    <div>
        <div style="width: 90%; margin: 0 auto;">
            <DataTable :value="state[currentDay].foods" paginator :rows="5" :rowsPerPageOption="[5, 10, 20, 50]"
                tableStyle="min-width: 50rem">
                <Column field="name" header="Name" style="width: 20%"></Column>
                <Column field="protein" header="Protein" style="width: 20%"></Column>
                <Column field="fat" header="Fat" style="width: 20%"></Column>
                <Column field="carbs" header="Carbs" style="width: 20%"></Column>
                <Column field="servings" header="Servings" style="width: 20%"></Column>
                <Column>
                    <template #body="slotProps">
                        <span class="p-buttonset">
                            <Button type="submit" label="Delete" @click="deleteFood(slotProps.index)" />
                        </span>
                    </template>
                </Column>
            </DataTable>
            <div>
                <form class="col-lg-10 offset-lg-1">
                    <div class="row g-3 justify-content-center">
                        <div class="col-md-auto">
                            <input id="search" v-model="search" placeholder="Search Food">
                        </div>
                    </div>
                </form>
                <Button type="submit" label="Add food" @click="addFood()" />
            </div>
            <div>
                <Button type="submit" label="Update journal" @click="updateEntry()" />
            </div>
            <div>
                <span>Total Carbs {{ state[currentDay].totalCarbs }}</span>
                <span>Total Protein {{ state[currentDay].totalProtein }}</span>
                <span>Total Fat {{ state[currentDay].totalFat }}</span>
            </div>
            <DynamicDialog />
        </div>
    </div>
</template>

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
let currentDay = getToday()

provide('dialog', dialog);



onMounted(() => {
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
            state[currentDay].foods.push(options.data)
        }
    });
}

function changeServing(serving) {
    dialog.open(Servings, {
        props: {
            header: 'Servings',
            style: {
                width: '40vw',
            },
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            modal: true,
        },
        data: { serving },
        // Return data from dialog
        onClose(output) {

        }
    });
}

function deleteFood(index) {
    state[currentDay].totalCarbs -= state[currentDay].foods[index].carbs
    state[currentDay].totalProtein -= state[currentDay].foods[index].protein
    state[currentDay].totalFat -= state[currentDay].foods[index].fat
    state[currentDay].foods.splice(index, 1)
}

function updateEntry() {
    store.methods.updateFoodJournalEntry(state[currentDay]);
}

</script>