<template>
    <div>
    <nav>
      <div class="nav nav-tabs justify-content-end mb-4" role="tablist">
        <a class="nav-item nav-link" data-toggle="tab" @click="console.log('food')"
        role="tab">Food</a>
        <a class="nav-item nav-link" data-toggle="tab" @click="console.log('exersice')"
        role="tab">Exercise</a>
        <a v-if="global.userState.person == null" class="nav-item nav-link" data-toggle="tab" @click="login()"
        role="tab">Log In</a>
        <a v-if="global.userState.person != null" class="nav-item nav-link" data-toggle="tab" @click="logout()"
        role="tab">Log Out</a>
      </div>
    </nav>
    <router-view/>
  </div>
</template>

<script setup>
import global from '@/global';
import { useDialog } from 'primevue/usedialog';
import { onMounted, provide } from 'vue';
import { useRouter } from 'vue-router';
import Login from './components/Login.vue';

const router = useRouter();
const dialog = useDialog();
provide('dialog', dialog);
provide('global', global);


onMounted(() => {
  console.log(global.userState.person)
});

function login() {
  dialog.open(Login, {
        props: {
            header: 'Login',
            style: {
                width: '40vw',
            },
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            modal: true,
        },
        onClose: (options) => {
            const data = options.data;
            if (data) {
                toast.add({ severity: 'success', summary: 'Success Message', detail: 'Message Content', life: 3000 });
            }
        }
    });
}

function logout() {
  global.methods.logout();
  router.push('/')
}

function doRoute(whereTo) {
  switch (whereTo) {
    
    case 'HelloWorld':
      router.push('/hello');
      break;
    case 'ProfilePage':
      router.push('/profile')
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