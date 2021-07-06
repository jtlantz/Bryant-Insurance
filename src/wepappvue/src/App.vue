<template>
    <v-app id="app">
        <template v-if="!$route.path.includes('login')">
        <v-app-bar
            app
            clipped-left
        >
            <v-toolbar color="indigo darken-4">
                <v-toolbar-title class="text-uppercase white--text">
                    <span class="font-weight-light">Bryant</span>
                    <span> Insurance</span>
                </v-toolbar-title>
            </v-toolbar>
        </v-app-bar>
        <v-navigation-drawer
            app
            color="#09151E"
            permanent
            expand-on-hover
            fixed
            height="92.5vh"
            mini-variant-width="20vh"
            width="40vh"
            dark
            clipped
        >
            <v-list
                dense
                nav
                class="py-0"
            >
                <v-list-item two-line :class="miniVariant && 'px-10'">
                    <v-list-item-avatar size="100">
                        <img
                            src="https://scontent.fkdt2-1.fna.fbcdn.net/v/t1.6435-9/131494959_2150874375044351_1395866012002412665_n.jpg?_nc_cat=108&ccb=1-3&_nc_sid=09cbfe&_nc_ohc=KIVQoj6KZuEAX9_Nqbf&tn=z_i1IRYF_DIjOQlW&_nc_ht=scontent.fkdt2-1.fna&oh=c702aa2a579380a6f87be0c7d66a89bf&oe=60E66660">
                    </v-list-item-avatar>

                    <v-list-item-content>
                        <v-list-item-title class="font-weight-medium white--text">Firstname Lastname</v-list-item-title>
                        <v-list-item-subtitle class="font-weight-medium white--text">ROLES</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
                <v-divider></v-divider>
            </v-list>
            <v-list dense>
                <v-list-item
                    v-for="item in items"
                    :key="item.title"
                    link
                >
                    <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>

                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
            <template v-slot:append>
                <div class="pa-2">
                    <v-btn block @click="drawer = false;" to="/login">
                        Logout
                    </v-btn>
                </div>
            </template>
        </v-navigation-drawer>

        <v-main>
            <v-container fluid>
                <v-card-title>
                    <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="Search"
                        single-line
                        hide-details
                    ></v-text-field>
                </v-card-title>
                <v-data-table
                    :headers="headers"
                    :items="entries"
                    :search="search"
                ></v-data-table>
            </v-container>
        </v-main>

        <v-footer>
            <!-- -->
        </v-footer>
        </template>
        <v-content>
            <keep-alive :include="['Login']">
                <router-view></router-view>
            </keep-alive>
        </v-content>
    </v-app>
</template>


<script>
import { mdiHomeCircle } from '@mdi/js';
export default {

    name: 'App',

    data: () => ({
        items: [
            {title: 'Home', icon: mdiHomeCircle},
            {title: 'Edit User', icon: 'mdi-wrench'},
            {title: 'Add User', icon: 'mdi-account-multiple-plus'},
            {title: 'Add Carrier', icon: 'mdi-file-document-outline'},
        ],
        //

        search: '',
        headers: [
            {
                text: 'Name',
                align: 'start',
                filterable: true,
                value: 'name',
            },
            {text: 'Carrier', value: 'carrier'},
            {text: 'Phone Number', value: 'phone_number'},
            {text: 'Date Sold', value: 'sell_date'},
        ],
        entries: [
            {
                name: 'John Smith',
                carrier: 'Progressive',
                phone_number: '888-555-1234',
                sell_date: '12/21/2020',
            },
            {
                name: 'Jane Doe',
                carrier: 'Safeway',
                phone_number: '888-555-8135',
                sell_date: '6/2/2021',
            },
            {
                name: 'Bob Randall',
                carrier: 'Progressive',
                phone_number: '888-555-4731',
                sell_date: '6/8/2020',
            },
            {
                name: 'Seth Michaels',
                carrier: 'Allstate',
                phone_number: '888-555-9876',
                sell_date: '5/19/2021',
            },
            {
                name: 'Stephanie Harris',
                carrier: 'Progressive',
                phone_number: '888-555-1478',
                sell_date: '11/15/2020',
            },
            {
                name: 'Roger Dodger',
                carrier: 'Safeway',
                phone_number: '888-555-6699',
                sell_date: '9/22/2020',
            },
            {
                name: 'Candice Brown',
                carrier: 'Geico',
                phone_number: '888-555-5731',
                sell_date: '1/30/2021',
            },
        ],
    })
};
</script>
