<script setup lang="ts">
import * as yup from "yup";
import {useField, useForm} from "vee-validate";
import apiClient from "@/services/AxiosClient";
import {useMessageStore} from "@/stores/message";
import {useRouter} from "vue-router";
import RegistryService from "@/services/RegistryService";
import {ref} from "vue";
import type {Advisor, Department} from "@/types";
import ImageUpload from "@/components/ImageUpload.vue";
import BaseSelect from "@/components/BaseSelect.vue";
import ValidatedInput from "@/components/ValidatedInput.vue";
import CollapseCard from "@/components/CollapseCard.vue";

const props = defineProps<{
  advisor: Advisor
}>()
const messageStore = useMessageStore()
const router = useRouter()

const validationSchema = yup.object({
  username: yup.string().nullable(),
  password: yup
      .string()
      .nullable()
      .min(4, 'Password must be at least 4 characters long'),
  email: yup.string().nullable().email('Email has to be in correct format.'),
  position: yup.string().required('Position is required.'),
  fname: yup.string().required('First name is required.'),
  lname: yup.string().required('Last name is required.'),
  image: yup.string().required('Image is required.'),
  departmentId: yup.number().required('Department is required.')
})
const {errors, handleSubmit} = useForm({
  validationSchema,
  initialValues: {
    username: null,
    password: null,
    email: null,
    position: props.advisor.position,
    fname: props.advisor.fname,
    lname: props.advisor.lname,
    departmentId: props.advisor.department.id,
    image: props.advisor.image
  }
})
const {value: username} = useField<string>('username')
const {value: password} = useField<string>('password')
const {value: email} = useField<string>('email')
const {value: position} = useField<string>('position')
const {value: fname} = useField<string>('fname')
const {value: lname} = useField<string>('lname')
const {value: image} = useField<string>('image')
const {value: departmentId} = useField<number>('departmentId')

const onSubmit = handleSubmit((values) => {
  apiClient
      .put(`/api/v1/auth/update-teacher/${props.advisor.id}`, values)
      .then(() => {
        messageStore.flashMessage('Successfully edited.')
        router.push({name: 'dashboard'})
      })
      .catch((err) => {
        console.log(err)
        messageStore.flashMessage('Could not edit, Username already exists.')
      })
})

const departmentOption = ref<Department[]>([])

RegistryService.getDepartments().then((res) => {
  departmentOption.value = res.data
})

</script>

<template>

  <div class="mx-auto mt-4">
    <form @submit.prevent="onSubmit">
      <CollapseCard :title="'Teacher Information'" :active="true">
        <ValidatedInput
            class="col-span-1"
            label="Position"
            v-model="position"
            :error="errors.position"
        />
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
        <BaseSelect
            class="col-span-5"
            label="Department"
            v-model="departmentId"
            :key-extractor="(x) => x.id"
            :value-extractor="(x) => x.id"
            :text-extractor="(x) => x.name"
            :error="errors.departmentId"
            :options="departmentOption"
        ></BaseSelect>
        <ImageUpload class="col-span-5" v-model="image" :error="errors.image"/>
      </CollapseCard>

      <CollapseCard class="my-3" :title="'Security Config'" >
        <ValidatedInput
            class="col-span-5"
            label="Username"
            v-model="username"
            :error="errors.username"
            :autocomplete="false"
        />
        <ValidatedInput
            class="col-span-5"
            label="Password"
            v-model="password"
            type="password"
            :error="errors.password"
            :autocomplete="false"
        />
        <ValidatedInput class="col-span-5" label="Email" v-model="email" :error="errors.email"/>
      </CollapseCard>

      <button type="submit" class="bg-emerald-500 px-2 py-1 place-self-center col-span-2 mt-5">
        Submit
      </button>

    </form>
  </div>

</template>

<style scoped>

</style>