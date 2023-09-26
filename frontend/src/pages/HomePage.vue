<template>
    <div>
        <Toast />
        <Panel header="Header">
            <p class="m-0">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
                ea commodo
                consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id
                est laborum.
            </p>
        </Panel>
        <Button label="Create Account" @click="createAccount" />

        <DynamicDialog />
    </div>
</template>

<script setup>

import Button from 'primevue/button';
import DynamicDialog from 'primevue/dynamicdialog';
import Panel from 'primevue/panel';
import Toast from 'primevue/toast';
import { useDialog } from 'primevue/usedialog';
import { provide } from 'vue';
import SignUp from '../components/SignUp.vue';

const dialog = useDialog();
provide('dialog', dialog);



    function createAccount() {
        dialog.open(SignUp, {
            props: {
                header: 'Create Account',
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


</script>
