<template>
    <div>
        <form>
            <div class="row g-3">
                <div class="col">
                    <label for="email" class="form-label">Email</label>
                    <input id="email" class="form-control" v-model="state.email" placeholder="Email">
                </div>
                <div class="col">
                    <label for="username" class="form-label">Username</label>
                    <input id="username" class="form-control" v-model="state.username" placeholder="Username">
                </div>
            </div>
            <div class="row g-3">
                <div class="col">
                    <label for="password" class="form-label">Password</label>
                    <input id="password" type="password" class="form-control" v-model="state.password"
                        placeholder="Password">
                </div>
                <div class="col">
                    <label for="confirmPass" class="form-label">Confirm Password</label>
                    <input id="confirmPass" type="password" class="form-control" v-model="state.confirmPass"
                        placeholder="Password">
                </div>
            </div>
            <div class="row g-3">
                <div class="col">
                    <label for="height" class="form-label">Height in inches</label>
                    <input type="height" class="form-control" v-model="state.height" placeholder="Height">
                </div>
                <div class="col">
                    <label for="weight" class="form-label">Weight in pountds</label>
                    <input type="weight" class="form-control" v-model="state.weight" placeholder="weight">
                </div>
            </div>
        </form>
        <Button label="Submit" @click="createAccount($event)" />
    </div>
</template>

<script setup>
import $ from 'jquery';
import Button from 'primevue/button';
import { useToast } from 'primevue/usetoast';
import { inject, reactive } from 'vue';

const dialogRef = inject("dialogRef");
const toast = useToast();
const store = inject('store');

const state = reactive({
    "email": "",
    "username": "",
    "password": "",
    "confirmPass": "",
    "height": 0,
    "weight": 0,
    "role": ""
})

function validatePassword() {
    if (state.confirmPass === state.password) {
        return true;
    } else {
        return false;
    }
}



async function createAccount(e) {

    const validPassword = validatePassword()
    if (!validPassword) {
        toast.add({ severity: 'error', summary: 'Password error', detail: 'Password does not match confirmation password', life: 3000 });
    }
    else {
        console.log("is valid")
            if (state.username === 'admin') {
                state.role = 'ADMIN'
            } else {
                state.role = "USER"
            }

            e.preventDefault();
            const person = {
                "email": state.email,
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
                        state.username = "";
                        state.password = "";
                        state.height = 0;
                        state.weight = 0;
                        dialogRef.value.close();
                    },
                    error: (jqXHR) => {
                        // if (jqXHR.status == 400)

                    }
                }
            );

            return;
    }
}
</script>

