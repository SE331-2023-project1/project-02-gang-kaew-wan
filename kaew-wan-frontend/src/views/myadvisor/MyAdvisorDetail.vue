<script setup lang="ts">



import type {Advisor} from "@/types";

defineProps<{
  advisor: Advisor
}>()

</script>

<template>
  <!--<h1>This is profile view</h1>-->
  <div v-if="advisor" class="mx-5 my-5">
    <div class="flow-root w-5/6 mx-auto">
      <div class="mb-5 h-64 w-64 mx-auto">
        <img :src="advisor.image" class="p-2 h-64 w-64" />
      </div>

      <dl class="-my-3 divide-y divide-gray-100 text-sm">
        <div class="grid grid-cols-1 gap-1 py-3 sm:grid-cols-3 sm:gap-4">
          <dt class="text-white font-medium text-gray-900">First name</dt>
          <dd class="text-gray-100 sm:col-span-2">
            {{ advisor.fname }}
          </dd>
        </div>

        <div class="grid grid-cols-1 gap-1 py-3 sm:grid-cols-3 sm:gap-4">
          <dt class="text-white font-medium text-gray-900">Last name</dt>
          <dd class="text-gray-100 sm:col-span-2">
            {{ advisor.lname }}
          </dd>
        </div>

        <div class="grid grid-cols-1 gap-1 py-3 sm:grid-cols-3 sm:gap-4">
          <dt class="text-white font-medium text-gray-900">Department</dt>
          <dd class="text-gray-100 sm:col-span-2">
            {{ advisor.department.name }}
          </dd>
        </div>

        <div class="grid grid-cols-1 gap-1 py-3 sm:grid-cols-3 sm:gap-4">
          <dt class="text-white font-medium text-gray-900">Bio</dt>
          <dd class="text-gray-100 sm:col-span-2">
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Et facilis debitis explicabo
            doloremque impedit nesciunt dolorem facere, dolor quasi veritatis quia fugit aperiam
            aspernatur neque molestiae labore aliquam soluta architecto?
          </dd>
        </div>
      </dl>
    </div>
  </div>
</template>

<style scoped></style>
