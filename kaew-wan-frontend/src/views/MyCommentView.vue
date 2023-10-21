<script setup lang="ts">

import {useCommentStore} from "@/stores/comment";
import {storeToRefs} from "pinia";

const commentStore = useCommentStore()
const comments = storeToRefs(commentStore).comments

</script>

<template>

</template>

<style scoped>

</style>