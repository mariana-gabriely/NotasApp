<template>
  <Header></Header>
  <div class="container editor-container">
    <div class="editor-header">
      <button class="btn-back" @click="$router.push('/')">
        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <path d="M19 12H5M12 19l-7-7 7-7"/>
        </svg>
        <span>Voltar</span>
      </button>
      <div class="header-actions">
        <input type="color" v-model="note.cor" class="color-input" title="Cor da nota" />
        <button class="btn-save" @click="saveNote" :disabled="saving">
          {{ saving ? 'Salvando...' : 'Concluir' }}
        </button>
      </div>
    </div>

    <div class="editor-paper">
      <input v-model="note.titulo" type="text" placeholder="Sem título" class="input-title" />
      <textarea v-model="note.conteudo" placeholder="Comece a escrever..." class="input-content" rows="15"></textarea>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRouter } from 'vue-router'
import api from '@/api'

const props = defineProps(['id'])
const router = useRouter()
const saving = ref(false)

const note = ref({ titulo: '', conteudo: '', cor: '#e0e0e0' })
const isEditing = computed(() => !!props.id)

const fetchNote = async () => {
  if (isEditing.value) {
    try {
      const response = await api.get(`/notas/${props.id}`)
      note.value = response.data
    } catch (error) {
      router.push('/')
    }
  }
}

const saveNote = async () => {
  if (!note.value.titulo && !note.value.conteudo) return
  saving.value = true
  try {
    if (isEditing.value) {
      await api.put(`/notas/atualizar/${props.id}`, note.value)
    } else {
      await api.post('/notas/novo', note.value)
    }
    router.push('/')
  } catch (error) {
    alert('Erro ao salvar nota.')
  } finally {
    saving.value = false
  }
}

onMounted(fetchNote)
</script>

<style scoped>
.editor-container { max-width: 800px; padding-top: 40px; }
.editor-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 40px; }
.btn-back { background: none; border: none; display: flex; align-items: center; gap: 8px; color: var(--text-secondary); cursor: pointer; border-radius: 8px; padding: 8px; transition: background 0.2s; }
.btn-back:hover { background: var(--bg-secondary); }
.header-actions { display: flex; align-items: center; gap: 16px; }
.color-input { width: 32px; height: 32px; border: none; cursor: pointer; border-radius: 50%; }
.btn-save { background: var(--primary-color); color: white; border: none; padding: 8px 20px; font-weight: 600; cursor: pointer; border-radius: 8px; }
.input-title { width: 100%; border: none; outline: none; font-size: 2.5rem; font-weight: 700; margin-bottom: 20px; }
.input-content { width: 100%; border: none; outline: none; font-size: 1.15rem; line-height: 1.6; resize: none; }
</style>
