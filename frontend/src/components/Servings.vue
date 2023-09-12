<template>
    <div>
        <form>
            <div class="mb-3">
                <label for="username" class="form-label">Serving Size</label>
                <input id="username" class="form-control" v-model="state.servings" placeholder="Username">
            </div>
            <div class="mb-3">
                <Button id="update" label="Change serving size" @click="setServing()" />
            </div>
        </form>
    </div>
</template>

<script setup>

import Button from 'primevue/button';
import { inject, reactive } from 'vue';

const dialogRef = inject('dialogRef');
const state = reactive({
    servings: 1.0
})

function setServing() {
    dialogRef.value.close(state.servings)
}



</script>

