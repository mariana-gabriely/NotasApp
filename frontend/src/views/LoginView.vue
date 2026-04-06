<template>
  <div class="login-container">
    <div class="login-box">
      <div class="brand">
        <svg width="40" height="40" viewBox="0 0 24 24" fill="#00a82d" xmlns="http://www.w3.org/2000/svg">
          <path d="M14 2H6C4.89 2 4 2.89 4 4V20C4 21.11 4.89 22 6 22H18C19.11 22 20 21.11 20 20V8L14 2ZM18 20H6V4H13V9H18V20Z"/>
        </svg>
        <h1>{{ isRegistering ? 'Criar conta no NotesApp' : 'Entrar no NotesApp' }}</h1>
      </div>

      <form @submit.prevent="handleSubmit">
        <div v-if="isRegistering" class="form-group">
          <label>Nome completo</label>
          <input v-model="formData.nome" type="text" placeholder="Seu nome" required />
        </div>

        <div class="form-group">
          <label>Endereço de e-mail</label>
          <input v-model="formData.email" type="email" placeholder="email@exemplo.com" required />
        </div>

        <div class="form-group">
          <label>Senha</label>
          <input v-model="formData.senha" type="password" placeholder="••••••••" required />
        </div>

        <button type="submit" class="btn-submit" :disabled="loading">
          {{ loading ? 'Aguarde...' : (isRegistering ? 'Criar conta' : 'Continuar') }}
        </button>
      </form>

      <div class="toggle-auth">
        <p>
          {{ isRegistering ? 'Já tem uma conta?' : 'Novo por aqui?' }}
          <button class="btn-link" @click="isRegistering = !isRegistering">
            {{ isRegistering ? 'Fazer login' : 'Criar uma conta' }}
          </button>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import api from '@/api'

const router = useRouter()
const isRegistering = ref(false)
const loading = ref(false)

const formData = reactive({
  nome: '',
  email: '',
  senha: ''
})

const handleSubmit = async () => {
  loading.value = true
  try {
    if (isRegistering.value) {
      await api.post('/usuarios/novo', {
        nome: formData.nome,
        email: formData.email,
        senha: formData.senha,
        papeis: []
      })
      alert('Conta criada com sucesso! Agora faça login.')
      isRegistering.value = false
    } else {
      const credentials = btoa(`${formData.email}:${formData.senha}`)
      localStorage.setItem('token', credentials)
      await api.get('/notas')
      router.push('/')
    }
  } catch (error) {
    alert('Erro: Verifique suas credenciais ou conexão.')
    localStorage.removeItem('token')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: var(--bg-secondary);
}
.login-box {
  background: white;
  padding: 40px;
  border-radius: 12px;
  box-shadow: var(--shadow-md);
  width: 100%;
  max-width: 400px;
  text-align: center;
}
.brand { margin-bottom: 30px; }
.brand h1 { font-size: 1.5rem; font-weight: 700; margin-top: 15px; color: var(--text-main); }
.form-group { text-align: left; margin-bottom: 20px; }
.form-group label { display: block; font-size: 0.85rem; font-weight: 600; margin-bottom: 8px; color: var(--text-secondary); text-transform: uppercase; }
input { width: 100%; padding: 12px; border: 1px solid var(--border-color); border-radius: 8px; outline: none; transition: border-color 0.2s; }
input:focus { border-color: var(--primary-color); }
.btn-submit { width: 100%; background: var(--primary-color); color: white; border: none; padding: 12px; font-weight: 700; border-radius: 8px; margin-top: 10px; }
.btn-submit:hover { background: var(--primary-hover); }
.toggle-auth { margin-top: 25px; font-size: 0.9rem; color: var(--text-secondary); }
.btn-link { background: none; border: none; color: var(--primary-color); font-weight: 600; padding: 0 5px; text-decoration: underline; }
</style>
