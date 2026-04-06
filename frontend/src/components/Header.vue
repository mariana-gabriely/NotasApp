<template>
  <header class="header">
    <div class="header-content">
      <div class="header-left" @click="$router.push('/')">
        <svg width="24" height="24" viewBox="0 0 24 24" fill="#00a82d" xmlns="http://www.w3.org/2000/svg">
          <path d="M14 2H6C4.89 2 4 2.89 4 4V20C4 21.11 4.89 22 6 22H18C19.11 22 20 21.11 20 20V8L14 2ZM18 20H6V4H13V9H18V20Z"/>
        </svg>
        <span class="brand-name">NotesApp</span>
      </div>
      <nav class="nav-links">
        <router-link to="/">Início</router-link>
        <router-link to="/gerenciar">Criar Notas</router-link>
        <router-link to="/about">Sobre</router-link>
        <button class="btn-logout" @click="handleLogout">Sair</button>
      </nav>
    </div>
  </header>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const handleLogout = () => {
  localStorage.removeItem('token')
  router.push('/login')
}
</script>

<style scoped>
.header {
  background-color: white;
  border-bottom: 1px solid #e0e0e0;
  height: 60px;
  display: flex;
  align-items: center;
  position: sticky;
  top: 0;
  z-index: 1000;
}
.header-content {
  width: 100%;
  max-width: 1100px;
  margin: 0 auto;
  padding: 0 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.header-left {
  display: flex;
  align-items: center;
  gap: 10px;
  cursor: pointer;
}
.brand-name {
  font-size: 1.1rem;
  font-weight: 700;
  color: #37352f;
  letter-spacing: -0.02em;
}
.nav-links {
  display: flex;
  gap: 20px;
  align-items: center;
}
.nav-links a {
  text-decoration: none;
  color: #757575;
  font-size: 0.9rem;
  font-weight: 500;
  padding: 8px 12px;
  border-radius: 8px;
  transition: all 0.2s;
}
.nav-links a:hover {
  color: #37352f;
  background-color: var(--bg-secondary);
}
.nav-links a.router-link-active {
  color: #00a82d;
  font-weight: 600;
}
.btn-logout {
  background: none;
  border: 1px solid var(--border-color);
  color: #757575;
  font-size: 0.85rem;
  padding: 6px 12px;
  border-radius: 6px;
  font-weight: 500;
}
.btn-logout:hover {
  background-color: #fff1f0;
  color: #eb5757;
  border-color: #ffa39e;
}
</style>
