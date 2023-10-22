<script setup lang="ts">
import type { Comment, OutgoingComment } from '@/types'
import ReactionPanel from '@/components/ReactionPanel.vue'
import VueMarkdown from 'vue-markdown-render'
import { computed, ref } from 'vue'
import apiClient from '@/services/AxiosClient'
import { useAuthStore } from '@/stores/auth'
import { useMessageStore } from '@/stores/message'

const authStore = useAuthStore()
const openReply = ref<boolean>(false)
const props = defineProps<{
  comment: Comment
  comments: Comment[]
}>()
const hideReplies = ref<boolean>(props.comment.message === "This comment has been deleted")
const emit = defineEmits(['replied'])
const outgoingComment = ref<OutgoingComment>({
  message: '',
  sender: { id: authStore.currentPersonID },
  receiver: { id: props.comment.sender.id }
})

const subcomments = computed(() => {
  return props.comments.filter((x) => x.parent?.id === props.comment.id)
})

function submitComment() {
  apiClient
    .post(`/comments/reply/${props.comment.id}`, outgoingComment.value)
    .then(() => {
      useMessageStore().flashMessage('Replied!')
      emit('replied')
      openReply.value = false
    })
    .catch(() => {
      useMessageStore().flashMessage('Failed to reply.')
    })
}

function deleteComment() {
  apiClient
    .delete(`/comments/${props.comment.id}`)
    .then(() => {
      useMessageStore().flashMessage('Deleted!')
      emit('replied')
    })
    .catch(() => {
      useMessageStore().flashMessage('Failed to delete.')
    })
}
</script>

<template>
  <div class="flex w-full gap-2">
    <div class="flex flex-col gap-2">
      <img
        :src="comment.sender.image"
        class="aspect-square w-8 h-8 rounded-full object-cover mt-2"
      />
      <div class="h-full flex-grow flex justify-center w-full cursor-pointer group" @click="hideReplies = !hideReplies">
        <div class="h-full border-l border-stone-600 group-hover:border-stone-400" :class="{'!border-blue-600': hideReplies}"/>
      </div>
    </div>
    <div class="flex flex-col w-full gap-2 font-sans">
      <div class="p-2 w-full flex flex-col gap-2">
        <div class="flex flex-row items-center gap-2">
          <p class="font-bold">{{ comment.sender.fname }} {{ comment.sender.lname }}</p>
          <p v-if="comment.parent === null" class="font-bold">
            > {{ comment.receiver.fname }} {{ comment.receiver.lname }}
          </p>
        </div>
        <VueMarkdown
          :source="comment.message"
          :class="{
            'opacity-30 italic': comment.message === 'This comment has been deleted'
          }"
        />
        <div class="flex gap-2 items-center">
          <button
            @click="openReply = !openReply"
            class="font-semibold opacity-50 hover:opacity-100"
          >
            Reply
          </button>
          <button
            @click="deleteComment()"
            class="font-semibold opacity-50 hover:opacity-100"
            v-if="
              comment.sender.id === authStore.currentPersonID &&
              comment.message !== 'This comment has been deleted'
            "
          >
            Delete
          </button>
          <hr class="border-0 border-b border-stone-600 w-2" />
          <ReactionPanel :reactable="comment" :key="comment.id"></ReactionPanel>
        </div>
        <div
          v-if="openReply"
          class="flex flex-col w-full rounded-md border border-stone-700 min-h-[8rem]"
        >
          <textarea
            v-model="outgoingComment.message"
            class="flex-1 resize-none border-0 bg-transparent rounded-t-md placeholder:text-stone-600"
            placeholder="Comment here..."
          />
          <div class="flex justify-end px-2 py-1 bg-stone-800">
            <button
              class="font-semibold text-sm px-2 py-1 rounded-full bg-stone-300 text-black"
              @click="submitComment()"
            >
              Reply
            </button>
          </div>
        </div>
      </div>
      <div class="flex flex-col w-full gap-4" v-if="subcomments && subcomments.length > 0 && !hideReplies">
        <CommentCard
          :comment="subcomment"
          :comments="comments"
          v-for="subcomment in subcomments"
          @replied="emit('replied')"
        />
      </div>
    </div>
  </div>
</template>
