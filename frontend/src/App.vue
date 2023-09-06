<template>
    <div>
    <nav>
      <div class="nav nav-tabs justify-content-end mb-4" role="tablist">
        <a v-if="isAdmin()" class="nav-item nav-link" data-toggle="tab" @click="doRoute('AdminPage')"
        role="tab">Admin</a>
        <a class="nav-item nav-link" data-toggle="tab" @click="doRoute('FoodJournalPage')"
        role="tab">Food</a>
        <a class="nav-item nav-link" data-toggle="tab" @click="console.log('exersice')"
        role="tab">Exercise</a>
        <a v-if="store.userState.person == null" class="nav-item nav-link" data-toggle="tab" @click="login()"
        role="tab">Log In</a>
        <a v-if="store.userState.person != null" class="nav-item nav-link" data-toggle="tab" @click="logout()"
        role="tab">Log Out</a>
      </div>
    </nav>
    <router-view/>
  </div>
</template>

<script setup>
import store from '@/store';
import { useDialog } from 'primevue/usedialog';
import { onMounted, provide } from 'vue';
import { useRouter } from 'vue-router';
import Login from './components/Login.vue';

const router = useRouter();
const dialog = useDialog();
provide('dialog', dialog);
provide('store', store);


onMounted(() => {
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
        onClose: () => {
          const clientDate = new Date().toLocaleString( 'sv', { timeZoneName: 'short' } ).slice(0,10)
          store.methods.isInFoodJournal(store.userState.person.id, clientDate)
          store.methods.loadFoodJournal(store.userState.person.id)
          if(isAdmin() == true) {
    
            router.push('/admin')
          }
          else {
            router.push('/profile');
          }
        }
    });
}

function logout() {

  store.methods.logout();

  router.push('/')
}

function isAdmin() {
  return store.userState.person !== null && store.userState.person.role === 'ADMIN'
}

function doRoute(whereTo) {
  switch (whereTo) {
    
    case 'HelloWorld':
      router.push('/hello');
      break;
    case 'ProfilePage':
      router.push('/profile')
      break;
    case 'AdminPage':
      router.push('/admin')
      break;
    case 'FoodJournalPage':
      router.push('/food')
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