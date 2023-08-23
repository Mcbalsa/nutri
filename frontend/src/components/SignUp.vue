<template>
    <div>
        <form class="col-lg-10 offset-lg-1">
            <div class="row g-3 justify-content-center">
                <div class="col-md-auto">
                    <input id="username" v-model="state.username" placeholder="Username">
                </div>
            </div>
            <div class="row g-3 justify-content-center">
                <div class="col-md-auto">
                    <input id="password" v-model="state.password" placeholder="Password">
                </div>
            </div>
            <div class="row g-3 justify-content-center">
                <div class="col-md-auto">
                    <input id="height" v-model="state.height" placeholder="height in inches">
                </div>
            </div>
            <div class="row g-3 justify-content-center">
                <div class="col-md-auto">
                    <input id="weight" v-model="state.weight" placeholder="weight in pounds">
                </div>
            </div>
        </form>
        <Button label="Submit" @click="createAccount($event)" />
    </div>
</template>

<script setup>
import $ from 'jquery';
import Button from 'primevue/button';
import { reactive } from 'vue';

import { inject } from "vue";

const dialogRef = inject("dialogRef");

const state = reactive({
    "username": "",
    "password": "",
    "height": 0,
    "weight": 0,
    "role": ""
})

async function createAccount(e) {
    let username = document.getElementById('username');
    let password = document.getElementById('password');
    let height = document.getElementById("height");
    let weight = document.getElementById('weight')

    if (state.username === 'admin') {
        state.role = 'ADMIN'
    } else {
        state.role = "USER"
    }

    e.preventDefault();
    const person = {
        "username": state.username,
        "password": state.password,
        "height": state.height,
        "weight": state.weight,
        "role": state.role
    }

    $.ajax(
        {
            headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
            url: 'http://localhost:8080/api/v1/Person/Person',
            type: 'post',
            data: JSON.stringify(person),
            success: () => {
                console.log("Added")
                state.username = "";
                state.password = "";
                state.height = 0;
                state.weight = 0;
                dialogRef.value.close();
            },
            error: (jqXHR) => {
                if (jqXHR.status == 400)
                    $("#msg2").show().delay(5000).fadeOut();
            }
        }
    );
    $("#msg3").show().delay(6000).fadeOut();
    return;
}
</script>

