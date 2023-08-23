<template>
    <div>
        <h2>The profile page</h2>
    </div>
</template>

<script setup>
import global from '@/global';
import { onMounted, provide } from 'vue';

provide('global', global);


onMounted(() => {
    console.log(global.userState.person)
});

</script>

