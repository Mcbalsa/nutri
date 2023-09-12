<template>
    <h2>Food Search</h2>
    <div>
        <div style="width: 90%; margin: 0 auto;">
            <DataTable :value="state.food" paginator :rows="5" :rowsPerPageOption="[5, 10, 20, 50]" @rowSelect="onRowSelect"
                selectionMode="single" dataKey="id" :metaKeySelection="false" tableStyle="min-width: 50rem">
                <Column field="name" header="Name" style="width: 25%"></Column>>
                <Column field="protein" header="Protein" style="width: 25%"></Column>>
                <Column field="fat" header="Fat" style="width: 25%"></Column>>
                <Column field="carbs" header="Carbs" style="width: 25%"></Column>>
            </DataTable>
        </div>
    </div>
</template>

<script setup>

import Column from 'primevue/column';
import DataTable from 'primevue/datatable';
import { inject, onBeforeMount, reactive } from 'vue';

const dialogRef = inject('dialogRef');
const store = inject('store');
const state = reactive({
    search: "",
    food: "",
});

onBeforeMount(() => {
    state.search = dialogRef.value.data.search
    store.methods.loadFood();
    state.food = store.food.value?.filter(x => (x.name.toLowerCase().includes(state.search.toLocaleLowerCase())))
});

const onRowSelect = (event) => {
    // Return the data back to parent component
    dialogRef.value.close(event.data);
};


</script>