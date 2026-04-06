<template>
  <Header></Header>
  <div class="container">
    <div class="dashboard-header">
      <h1 class="page-title">Minhas Notas</h1>
      <button class="btn-primary" @click="$router.push('/gerenciar')">
        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
          <line x1="12" y1="5" x2="12" y2="19"></line>
          <line x1="5" y1="12" x2="19" y2="12"></line>
        </svg>
        <span>Nova nota</span>
      </button>
    </div>

    <div v-if="loading" class="loading-state">
      <p>Sincronizando notas...</p>
    </div>
    
    <div v-else class="notes-grid">
      <NotaItem 
        v-for="note in notes" 
        :key="note.id" 
        :note="note" 
        @edit="editNote"
        @delete="deleteNote"
      />
    </div>

    <div v-if="!loading && notes.length === 0" class="empty-state">
      <p>Nenhuma nota encontrada.</p>
      <button class="btn-text" @click="$router.push('/gerenciar')">Criar minha primeira nota</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import api from '@/api'
import NotaItem from '@/components/notas/NotaItem.vue'

const notes = ref([])
const loading = ref(true)
const router = useRouter()

const fetchNotes = async () => {
  try {
    const response = await api.get('/notas')
    notes.value = response.data
  } catch (error) {
    console.error('Erro ao buscar notas:', error)
  } finally {
    loading.value = false
  }
}

const deleteNote = async (id) => {
  if (confirm('Deseja excluir esta nota?')) {
    try {
      await api.delete(`/notas/apagar/${id}`)
      notes.value = notes.value.filter(n => n.id !== id)
    } catch (error) {
      alert('Erro ao excluir nota.')
    }
  }
}

const editNote = (id) => {
  router.push({ name: 'gerenciar', params: { id } })
}

onMounted(fetchNotes)
</script>

<style scoped>
.dashboard-header { display: flex; justify-content: space-between; align-items: center; margin: 40px 0; }
.page-title { font-size: 1.75rem; font-weight: 700; color: var(--text-main); margin: 0; }
.btn-primary { background: var(--primary-color); color: white; border: none; padding: 10px 20px; font-weight: 600; display: flex; align-items: center; gap: 10px; }
.btn-primary:hover { background: var(--primary-hover); }
.notes-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); gap: 20px; }
.loading-state, .empty-state { text-align: center; padding: 100px 0; color: var(--text-secondary); }
.btn-text { background: none; border: none; color: var(--primary-color); font-weight: 600; cursor: pointer; }
</style>
