<script setup lang="ts">
import {usePersonStore} from "@/stores/person";
import {storeToRefs} from "pinia";
import * as yup from "yup";
import {useField, useForm} from "vee-validate";
import apiClient from "@/services/AxiosClient";
import {useMessageStore} from "@/stores/message";
import {useRouter} from "vue-router";
import ValidatedInput from "@/components/ValidatedInput.vue";
import ImageUpload from "@/components/ImageUpload.vue";

const store = usePersonStore()
const person = storeToRefs(store).person
const messageStore = useMessageStore()
const router = useRouter()

const validationSchema = yup.object({
  fname: yup.string().required('First name is required.'),
  lname: yup.string().required('Last name is required.'),
  image: yup.string().required('Image is required.')
})

const {errors, handleSubmit} = useForm({
  validationSchema,
  initialValues: {
    fname: person.value?.fname || '',
    lname: person.value?.lname || '',
    image: person.value?.image || ''
  }
})

const {value: fname} = useField<string>('fname')
const {value: lname} = useField<string>('lname')
const {value: image} = useField<string>('image')

const onSubmit = handleSubmit((values) => {
  apiClient
      .put(`/students/${person.value?.id}`, values)
      .then(res => {
        messageStore.flashMessage('Successfully Registered.')
        router.push({name: 'profile-detail', params: {id: res.data.id}})
      })
      .catch((err) => {
        console.log(err)
        messageStore.flashMessage('Could not register, Student ID already exists.')
      })
})

</script>

<template>
  <form @submit.prevent="onSubmit" class="grid grid-cols-2 gap-2 w-2/3">
    <p class="col-span-2">Student Information</p>
    <ValidatedInput
        class="col-span-1"
        label="First name"
        v-model="fname"
        :error="errors.fname"
    />
    <ValidatedInput
        class="col-span-1"
        label="Last name"
        v-model="lname"
        :error="errors.lname"
    />

    <ImageUpload class="col-span-2" v-model="image" :error="errors.image"/>
    <button type="submit" class="bg-emerald-500 px-2 py-1 place-self-center col-span-2">
      Submit
    </button>
  </form>

</template>

<style scoped>

</style>