<script setup lang="ts">

import {useStudentStore} from "@/stores/student";
import {storeToRefs} from "pinia";

const studentStore = useStudentStore();
const student = storeToRefs(studentStore).student

</script>

<template>
<!--  <h1>WIP</h1>-->
  <div v-if="student">
    {{student.studentId}}
    {{ student.fname}}  {{student.lname}}
  </div>
  <!--  TODO: implement student -->
  <RouterView></RouterView>
</template>
