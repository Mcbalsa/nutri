<template>
    <div>
        <div style="width: 90%; margin: 0 auto;">
            <DataTable :value="state" paginator :rows="5" :rowsPerPageOption="[5, 10, 20, 50]"
                tableStyle="min-width: 50rem">
                <Column field="name" header="Name" style="width: 25%"></Column>>
                <Column field="protein" header="Protein" style="width: 25%"></Column>>
                <Column field="fat" header="Fat" style="width: 25%"></Column>>
                <Column field="carbs" header="Carbs" style="width: 25%"></Column>>
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

const store = inject('store')
let search = "";

const state = reactive(store.foodJournal.value)
const dialog = useDialog();
let currentDay = 0

provide('dialog', dialog);

onMounted(() => {
    let clientDate = new Date().getMonth() + new Date().getDate() + new Date().getFullYear()
    for (let i = 0; i < state.length; i++) {
        let entry = state[i]
        let entryDate = new Date(entry.creationDate).getMonth() + new Date(entry.creationDate).getDate()
            + new Date(entry.creationDate).getFullYear()
        if (clientDate == entryDate) {
            currentDay = i
        }
    }
    console.log(state[currentDay])
})


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
            state.push(options.data)
        }
    });

}

</script>