<script setup lang="ts">

import {useRouter} from "vue-router";
import {useMessageStore} from "@/stores/message";
import * as yup from "yup";
import {useField, useForm} from "vee-validate";
import apiClient from "@/services/AxiosClient";
import ValidatedInput from "@/components/ValidatedInput.vue";
import ImageUpload from "@/components/ImageUpload.vue";

const router = useRouter()
const messageStore = useMessageStore()
const validationSchema = yup.object({
  username: yup.string().required('Username is required.'),
  email: yup.string().required('Email is required.').email('Email has to be in correct format.'),
  password: yup
      .string()
      .required('Password is required')
      .min(4, 'Password must be at least 4 characters long'),
  fname: yup.string().required('First name is required.'),
  lname: yup.string().required('Last name is required.'),
  image: yup.string().required('Image is required.')
})
const { errors, handleSubmit } = useForm({
  validationSchema,
  initialValues: {
    username: '',
    password: '',
    email: '',
    fname: '',
    lname: '',
    image: ''
  }
})
const { value: username } = useField<string>('username')
const { value: password } = useField<string>('password')
const { value: email } = useField<string>('email')
const { value: fname } = useField<string>('fname')
const { value: lname } = useField<string>('lname')
const { value: image } = useField<string>('image')

const onSubmit = handleSubmit((values) => {
  apiClient
      .post('/api/v1/auth/add-admin', values)
      .then((res) => {
        messageStore.flashMessage('Successfully Added ' + username + " as an admin!")
        router.push({ name: 'dashboard' })
      })
      .catch((err) => {
        console.log(err)
        messageStore.flashMessage('Could not add, Username already exists.')
      })
})


</script>

<template>
  <main class="w-full px-4 sm:p-0 sm:w-2/3 flex flex-col items-center">
    <div class="bg-stone-800 w-full max-w-xl flex flex-col items-center p-4">
      <p class="text-2xl m-4">Add admin</p>
      <form @submit.prevent="onSubmit" class="grid grid-cols-2 gap-2 w-2/3">
        <ValidatedInput
            class="col-span-2"
            label="User name"
            v-model="username"
            :error="errors.username"
            :autocomplete="false"
        /><ValidatedInput
            class="col-span-2"
            label="Email"
            v-model="email"
            :error="errors.email"
            :autocomplete="false"
        />
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
        <ValidatedInput
            class="col-span-2"
            label="Password"
            v-model="password"
            type="password"
            :error="errors.password"
            :autocomplete="false"
        />

        <ImageUpload class="col-span-2" v-model="image" :error="errors.image" />
        <button type="submit" class="bg-emerald-500 px-2 py-1 place-self-center col-span-2">
          Submit
        </button>
      </form>
    </div>
  </main>

</template>

<style scoped>

</style>