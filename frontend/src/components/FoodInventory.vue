<template>
    <div>
        <div style="width: 90%; margin: 0 auto;">
            <DataTable :value="store.food.value" paginator :rows="5" :rowsPerPageOption="[5, 10, 20, 50]"
            @rowSelect="onRowSelect"  selectionMode="single" dataKey="id" :metaKeySelection="false" tableStyle="min-width: 50rem">
                <Column field="name" header="Name" style="width: 25%"></Column>>
                <Column field="protein" header="Protein" style="width: 25%"></Column>>
                <Column field="fat" header="Fat" style="width: 25%"></Column>>
                <Column field="carbs" header="Carbs" style="width: 25%"></Column>>
            </DataTable>
            <Button label="Add Food" @click="addFood()" />
            <DynamicDialog />
        </div>
    </div>
</template>

<script setup>

import Button from 'primevue/button';
import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { useDialog } from 'primevue/usedialog';
import { inject, onMounted, provide } from 'vue';
import AdminAddFood from '../components/AdminAddFood.vue';
import FoodDetails from '../components/FoodDetails.vue';

const store = inject('store')
const dialog = useDialog();
provide('dialog', dialog);

onMounted(() => {
    store.methods.loadFood();
})

function addFood() {
    dialog.open(AdminAddFood, {
        props: {
            header: 'Food Details',
            style: {
                width: '40vw',
            },
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            modal: true,
        },
    });
}

const onRowSelect = (event) => {
    dialog.open(FoodDetails, {
        props: {
            header: 'Food Details',
            style: {
                width: '40vw',
            },
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            modal: true,
        },
        data: { Food: event.data }
    });
    
};
</script>


