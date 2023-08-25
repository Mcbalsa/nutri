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
        </form>
        <Button type="submit" @click="login($event)" label="Log in"></Button>
    </div>
</template>

<script setup>


import Button from 'primevue/button';
import { inject, reactive } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const state = reactive({
    username: "",
    password: ""
});

const global = inject('global');
const dialogRef = inject("dialogRef");

async function login(e) {
    let username = document.getElementById('username');
    let password = document.getElementById('password');
    
    const person = {
        'id' : "string",
        "username": state.username,
        "password": state.password,
        "height": 0,
        "weight": 0,
        "role": "USER"
        
    }
    e.preventDefault();
    await global.methods.login(person).catch(() => {
    });
    dialogRef.value.close();

}

</script>
