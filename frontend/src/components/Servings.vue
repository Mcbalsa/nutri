<template>
    <div>
        <form class="flex flex-column gap-2">>
            <span class="p-float-label">
                <InputText id="value" type="text" v-model="state.servings" />
                <label for="value">Servings</label>
            </span>
            <Button type="submit" label="Change" @click="go()" />
        </form>
    </div>
</template>

<script setup>

import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import { inject, onBeforeMount, reactive } from 'vue';

const dialogRef = inject('dialogRef');
const store = inject('store');
const state = reactive({
    servings: 0
});

function go() {
    console.log(state.servings)
}

onBeforeMount(() => {
    state.servings = dialogRef.value.data.servings

})



</script>
