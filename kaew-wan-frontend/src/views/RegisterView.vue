<script setup lang="ts">
import { useAuthStore } from '@/stores/auth'
import { useMessageStore } from '@/stores/message'
import { useField, useForm } from 'vee-validate'
import { useRouter } from 'vue-router'
import * as yup from 'yup'
import ValidatedInput from '@/components/ValidatedInput.vue'
import apiClient from '@/services/AxiosClient'
import BaseSelect from '@/components/BaseSelect.vue'
import ImageUpload from '@/components/ImageUpload.vue'
import RegistryService from '@/services/RegistryService'
import { ref } from 'vue'
import type { Department } from '@/types'
const authStore = useAuthStore()
const router = useRouter()
const messageStore = useMessageStore()
const validationSchema = yup.object({
  studentId: yup.string().required('Student ID is required.').matches(/\d+/, 'Must be a number'),
  password: yup
    .string()
    .required('Password is required')
    .min(4, 'Password must be at least 4 characters long'),
  email: yup.string().required('Email is required.').email('Email has to be in correct format.'),
  fname: yup.string().required('First name is required.'),
  lname: yup.string().required('Last name is required.'),
  image: yup.string().required('Image is required.'),
  departmentId: yup.number().required('Department is required.')
})
const { errors, handleSubmit } = useForm({
  validationSchema,
  initialValues: {
    studentId: '',
    password: '',
    email: '',
    fname: '',
    lname: '',
    departmentId: null,
    image: ''
  }
})
const { value: studentId } = useField<string>('studentId')
const { value: password } = useField<string>('password')
const { value: email } = useField<string>('email')
const { value: fname } = useField<string>('fname')
const { value: lname } = useField<string>('lname')
const { value: image } = useField<string>('image')
const { value: departmentId } = useField<number>('departmentId')
const onSubmit = handleSubmit((values) => {
  apiClient
    .post('/api/v1/auth/register', values)
    .then((res) => {
      authStore.save(res.data.access_token, res.data.user)
      messageStore.flashMessage('Successfully Registered.')
      router.push({ name: 'dashboard' })
    })
    .catch((err) => {
      messageStore.flashMessage('Could not register, Student ID already exists.')
    })
})

const departmentOption = ref<Department[]>([])

const departments = RegistryService.getDepartments().then((res) => {
  departmentOption.value = res.data
})
</script>
<template>
  <main class="w-full px-4 sm:p-0 sm:w-2/3 flex flex-col items-center">
    <div class="bg-stone-800 w-full max-w-xl flex flex-col items-center p-4">
      <p class="text-2xl m-4">Register</p>
      <form @submit.prevent="onSubmit" class="grid grid-cols-2 gap-2 w-2/3">
        <ValidatedInput
          class="col-span-2"
          label="Student ID"
          v-model="studentId"
          :error="errors.studentId"
          :autocomplete="false"
        />
        <ValidatedInput
          class="col-span-2"
          label="Password"
          v-model="password"
          type="password"
          :error="errors.password"
          :autocomplete="false"
        />
        <ValidatedInput class="col-span-2" label="Email" v-model="email" :error="errors.email" />
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
        <BaseSelect
          class="col-span-2"
          label="Department"
          v-model="departmentId"
          :key-extractor="(x) => x.id"
          :value-extractor="(x) => x.id"
          :text-extractor="(x) => x.name"
          :error="errors.departmentId"
          :options="departmentOption"
        ></BaseSelect>
        <ImageUpload class="col-span-2" v-model="image" :error="errors.image" />
        <button type="submit" class="bg-emerald-500 px-2 py-1 place-self-center col-span-2">
          Submit
        </button>
      </form>
      <!-- <p>
        Not a member?
        <RouterLink :to="{ name: 'register' }" class="underline">Sign up here</RouterLink>
      </p> -->
    </div>
  </main>
</template>
