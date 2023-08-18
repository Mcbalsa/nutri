<template>
    <div>
    <nav>
      <div class="nav nav-tabs justify-content-end mb-4" role="tablist">
        <a class="nav-item nav-link" data-toggle="tab" @click="console.log('food')"
        role="tab">Food</a>
        <a class="nav-item nav-link" data-toggle="tab" @click="console.log('exersice')"
        role="tab">Exercise</a>
        <a class="nav-item nav-link" data-toggle="tab" @click="console.log('logging in')"
        role="tab">Log In</a>
        
      </div>
    </nav>
    <router-view/>
  </div>
</template>


<script setup>
import { useRouter } from 'vue-router';


const router = useRouter();



function doRoute(whereTo) {
  switch (whereTo) {
    
    case 'HelloWorld':
      router.push('/hello');
      break;
      
    default:
      router.push('/');
  }
}


</script>




<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 40px;
}

a {
  cursor: pointer;
}

.p-dialog-title {
  margin: 0 auto;
}

.p-speeddial {
  position: relative !important;
}

.p-speeddial-button.p-button {
  margin-top: 5px;
  width: 50px !important;
  height: 50px !important;
}

#cart {
  width: 50%;
  margin: 0 auto;
}
</style>