<template>
    <TabView>
        <TabPanel header="Users">
            <h2>Users</h2>
        </TabPanel>
        <TabPanel header="Food">
            <h2>Food</h2>
            <FoodInventory />
        </TabPanel>
    </TabView>
</template>

<script setup>

import TabPanel from 'primevue/tabpanel';
import TabView from 'primevue/tabview';

import FoodInventory from '../components/FoodInventory.vue';


</script>
