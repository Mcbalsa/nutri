<template>
    <div>
        <div id="leftBar">
            <img
                src="https://www.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640-600x600.png">
            <h5>Username: {{ store.userState.person.username }} </h5>
            <h5>Height: {{ store.userState.person.height }} in.</h5>
            <h5>Weight: {{ store.userState.person.weight }} lbs.</h5>
            <Button type="submit" label="Edit" @click="console.log('editing profile')" />
        </div>
        <!-- Async issues wih pulling food journal being undefined, placeholder for info -->
        <div id="macros" class="container text-center">
            <h3 id="title">Today's Macros</h3>
            <div id="holder" class="row g-3">
                <div class="col-auto">
                    <label for="cals">Calories</label>
                    <h4 id="cals">2350</h4>
                </div>
                <div class="col-auto">
                    <label for="fat">Fat</label>
                    <h4 id="fat">75g</h4>
                </div>
                <div class="col-auto">
                    <label for="protein">Protein</label>
                    <h4 id="protein">174g</h4>
                </div>
                <div class="col-auto">
                    <label for="carbs">Carbs</label>
                    <h4 id="carbs">50g</h4>
                </div>
            </div>
        </div>
        <!-- Dummy placeholder to represent newest posts from a forum -->
        <div id="posts">
            <div id="header">
                <h3>Newest posts</h3>
            </div>
            <div>
            <a href>New study finds the perfect amount of protein to consume</a>
            </div>
            <div>
            <a href>1 year progress pics</a>
            </div>
            <div>
            <a href>Sumo vs Conventional deadlift</a>
            </div>
            <div>
            <a href>Can't eat all this food</a>
            </div>
            <div>
            <a href>Zyzz tribute</a>
            </div>
            <div>
            <a href>Your gym playlist</a>
            </div>
            <div>
            <a href>Chicken breast tastes like cardboard</a>
            </div>
            <div>
            <a href>This cut is killing me</a>
            </div>
            <div>
            <a href>Your PR progress</a>
            </div>
            <div>
            <a href>We're all gonna make it</a>
            </div>
        </div>
    </div>
</template>

<script setup>


import { inject, onMounted } from 'vue';

const store = inject('store')


import Button from 'primevue/button';
import { inject, onMounted } from 'vue';
const store = inject('store')

onMounted(() => {

});



</script>

<style scoped>


#header {
    padding-top: 25px;
    padding-bottom: 25px;
    border-bottom-style: double;
    background-color:azure;
}

#posts {
    border-style: double;
    height: 500px;
    width: 300px;
    position: absolute;
    right: 100px;
    top: 150px;
}

img {
    height: 200px;
    width: 200px;
}

#leftBar {
    position: absolute;
    left: 100px;
    height: 400px;
    width: 300px;
    border-style: solid;
}

h5 {
    border-style: double;
}

#macros {
    position: relative;
    left: 500px;
    top: 300px;
    text-align: center;
}

.col-auto {
    border-style: solid;
}

#title {
    position: relative;
    right: 565px;
}

</style>
