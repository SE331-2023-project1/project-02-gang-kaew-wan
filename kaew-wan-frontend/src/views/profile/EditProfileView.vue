<script setup lang="ts">
import { usePersonStore } from '@/stores/person'
import { storeToRefs } from 'pinia'
import * as yup from 'yup'
import { useField, useForm } from 'vee-validate'
import apiClient from '@/services/AxiosClient'
import { useMessageStore } from '@/stores/message'
import { useRouter } from 'vue-router'
import ValidatedInput from '@/components/ValidatedInput.vue'
import ImageUpload from '@/components/ImageUpload.vue'
import type { Person } from '@/types'
import CollapseCard from '@/components/CollapseCard.vue'
import { useAuthStore } from '@/stores/auth'

const store = usePersonStore()
const person = storeToRefs(store).person
const messageStore = useMessageStore()
const router = useRouter()
const personStore = usePersonStore()
const authStore = useAuthStore()

const validationSchema = yup.object({
  fname: yup.string().required('First name is required.'),
  lname: yup.string().required('Last name is required.'),
  image: yup.string().required('Image is required.')
})

const { errors, handleSubmit } = useForm({
  validationSchema,
  initialValues: {
    fname: person.value?.fname || '',
    lname: person.value?.lname || '',
    image: person.value?.image || ''
  }
})

const { value: fname } = useField<string>('fname')
const { value: lname } = useField<string>('lname')
const { value: image } = useField<string>('image')

const onSubmit = handleSubmit((values) => {
  apiClient
    .put(`/students/${person.value?.id}`, values)
    .then((res) => {
      messageStore.flashMessage('Successfully edited.')
      personStore.setPerson(res.data as Person)
      authStore.updatePerson(res.data as Person)
      router.push({ name: 'profile-detail', params: { id: res.data.id } })
    })
    .catch((err) => {
      messageStore.flashMessage('Could not register.')
    })
})
</script>

<template>
  <form @submit.prevent="onSubmit" class="flex flex-col gap-4">
    <CollapseCard :title="'Basic Information'" :active="true">
      <div class="p-4 grid grid-cols-4 gap-4 items-center">
        <ValidatedInput
          class="col-span-2"
          label="First name"
          v-model="fname"
          :error="errors.fname"
        />
        <ValidatedInput
          class="col-span-2"
          label="Last name"
          v-model="lname"
          :error="errors.lname"
        />

        <ImageUpload class="col-span-4" v-model="image" :error="errors.image" />
      </div>
    </CollapseCard>
    <button type="submit" class="bg-emerald-500 px-2 py-1 place-self-center col-span-2">
      Submit
    </button>
  </form>
</template>

<style scoped></style>
