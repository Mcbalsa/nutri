<template>
    <div>
        <form>
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input id="username" class="form-control" v-model="state.username" placeholder="Username">

            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" v-model="state.password" placeholder="Password">
            </div>
        </form>
        <div>
            <Button type="submit" @click="login($event)" label="Log in"></Button>
        </div>
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

const store = inject('store');
const dialogRef = inject("dialogRef");

async function login(e) {
    e.preventDefault();
    await store.methods.login(state.username, state.password).catch(() => {
    });
    dialogRef.value.close();
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
p.error {
    color: red
}

.divider {
    width: 100%;
    height: 20px;
    border-bottom: 1px solid black;
    text-align: center;
    margin-top: 30px;
    margin-bottom: 30px;
}

.divider-title {
    font-size: 30px;
    background-color: #FFF;
}
</style>