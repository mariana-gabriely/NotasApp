<template>
  <div class="note-card" :style="{ borderLeft: `4px solid ${note.cor || '#e0e0e0'}` }">
    <div class="card-body" @click="$emit('edit', note.id)">
      <h3 v-if="note.titulo" class="card-title">{{ note.titulo }}</h3>
      <p class="card-text">{{ note.conteudo }}</p>
    </div>
    <div class="card-footer">
      <div class="actions">
        <button class="action-btn" @click.stop="$emit('edit', note.id)" title="Editar">
          <IconEdit />
        </button>
        <button class="action-btn delete" @click.stop="$emit('delete', note.id)" title="Excluir">
          <IconDelete />
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import IconEdit from '@/components/icons/IconEdit.vue'
import IconDelete from '@/components/icons/IconDelete.vue'

defineProps({ note: Object })
defineEmits(['edit', 'delete'])
</script>

<style scoped>
.note-card {
  background: white;
  border: 1px solid var(--border-color);
  border-radius: 6px;
  padding: 16px;
  display: flex;
  flex-direction: column;
  transition: all 0.2s ease;
  box-shadow: var(--shadow-sm);
  min-height: 140px;
}
.note-card:hover { box-shadow: var(--shadow-md); transform: translateY(-2px); }
.card-body { cursor: pointer; flex-grow: 1; }
.card-title { margin: 0 0 8px 0; font-size: 1rem; font-weight: 600; color: var(--text-main); }
.card-text { margin: 0; font-size: 0.875rem; color: var(--text-secondary); line-height: 1.5; white-space: pre-wrap; overflow: hidden; display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical; }
.card-footer { margin-top: 16px; border-top: 1px solid #f0f0f0; padding-top: 12px; }
.actions { display: flex; justify-content: flex-end; gap: 8px; }
.action-btn { background: transparent; border: none; padding: 6px; color: var(--text-secondary); display: flex; align-items: center; justify-content: center; border-radius: 8px; }
.action-btn:hover { background: var(--bg-secondary); color: var(--text-main); }
.action-btn.delete:hover { color: #eb5757; background: #fff1f0; }
</style>
