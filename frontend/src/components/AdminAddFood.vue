<template>
    <div>
        <div>
            <form class="col-lg-10 offset-lg-1">
                <div class="row g-3 justify-content-center">
                    <div class="col-md-auto">
                        <input id="username" v-model="state.name" placeholder="Name">
                    </div>
                </div>
                <div class="row g-3 justify-content-center">
                    <div class="col-md-auto">
                        <input id="password" v-model="state.protein" placeholder="Protein">
                    </div>
                </div>
                <div class="row g-3 justify-content-center">
                    <div class="col-md-auto">
                        <input id="height" v-model="state.fat" placeholder="Fat">
                    </div>
                </div>
                <div class="row g-3 justify-content-center">
                    <div class="col-md-auto">
                        <input id="weight" v-model="state.carbs" placeholder="Carbs">
                    </div>
                </div>
            </form>
            <Button label="Submit" @click="addFood($event)" />
        </div>
    </div>
</template>

<script setup>

import $ from 'jquery';
import Button from 'primevue/button';
import { inject, reactive } from 'vue';
import Food from '../global/Food.js';

const dialogRef = inject('dialogRef');
const global = inject('global')
const state = reactive({
    Food
});

async function addFood() {
    console.log(state)
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: 'http://localhost:8080/api/v1/Food/Food',
        method: 'post',
        data: JSON.stringify(state)
    }).done(() => {
        global.methods.loadFood();
        dialogRef.value.close();
    });
    
}

</script>

