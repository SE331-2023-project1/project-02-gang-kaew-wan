<script setup lang="ts">
import { useAuthStore } from '@/stores/auth'
import { useMessageStore } from '@/stores/message'
import { useField, useForm } from 'vee-validate'
import { useRouter } from 'vue-router'
import * as yup from 'yup'
import ValidatedInput from '@/components/ValidatedInput.vue'
const authStore = useAuthStore()
const router = useRouter()
const messageStore = useMessageStore()
const validationSchema = yup.object({
  username: yup.string().required('Username is required'),
  password: yup
    .string()
    .required('Password is required')
    .min(4, 'Password must be at least 4 characters long')
})
const { errors, handleSubmit } = useForm({
  validationSchema,
  initialValues: {
    username: '',
    password: ''
  }
})
const { value: username } = useField<string>('username')
const { value: password } = useField<string>('password')
const onSubmit = handleSubmit((values) => {
  authStore
    .login(values.username, values.password)
    .then((_) => {
      messageStore.flashMessage('Hoorays! Successfully logged in')
      router.push({ name: 'dashboard' })
    })
    .catch(() => {
      messageStore.flashMessage('Could not login')
    })
})
</script>
<template>
  <main class="w-full px-4 sm:p-0 sm:w-2/3 flex flex-col items-center">
    <div class="bg-stone-800 w-full max-w-xl flex flex-col items-center p-4">
      <p class="text-2xl m-4">Sign in to your account.</p>
      <form @submit.prevent="onSubmit" class="flex flex-col gap-2 w-2/3">
        <ValidatedInput label="Username" v-model="username" :error="errors.username" />
        <ValidatedInput
          label="Password"
          v-model="password"
          type="password"
          :error="errors.password"
        />
        <button type="submit" class="bg-emerald-500 px-2 py-1 place-self-center">Submit</button>
      </form>
      <!-- <p>
        Not a member?
        <RouterLink :to="{ name: 'register' }" class="underline">Sign up here</RouterLink>
      </p> -->
    </div>
  </main>
</template>
