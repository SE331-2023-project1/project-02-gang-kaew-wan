<script setup lang="ts">

import {storeToRefs} from "pinia";
import {useAdvisorStore} from "@/stores/advisor";

const advisorStore = useAdvisorStore();
const advisor = storeToRefs(advisorStore).advisor

</script>

<template>
  <!--  <h1>WIP</h1>-->
  <div v-if="advisor">
    {{ advisor.fname}}
    {{ advisor.lname}}
    {{advisor.position}}

    <img :src="advisor.image" />
  </div>
  <!--  TODO: implement advisor -->
  <RouterView></RouterView>
</template>
