<template>
    <div>
        <div id="leftBar">
            <img
                src="https://steamuserimages-a.akamaihd.net/ugc/1645466345445047424/689B870C8AD7CF3811D218B7B582693BB2EDF038/?imw=512&&ima=fit&impolicy=Letterbox&imcolor=%23000000&letterbox=false">
            <h5>Username: {{ store.userState.person.username }} </h5>
            <h5>Height: {{ store.userState.person.height }} in.</h5>
            <h5>Weight: {{ store.userState.person.weight }} lbs.</h5>
        </div>
    </div>
</template>

<script setup>

import { inject, onMounted } from 'vue';

const store = inject('store')


onMounted(() => {

});

</script>

<style scoped>
    img {
        height: 200px;
        width: 200px;
    }

    #leftBar{
        position: absolute;
        left: 100px;
        height: 400px;
        widows: 300px;
        border-style: solid;
    }
    h5 {
        border-style: double;
    }
</style>
