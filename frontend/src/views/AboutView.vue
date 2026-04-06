<template>
  <Header></Header>
  <div class="container about-container">
    <div class="about-card">
      <h1 class="about-title">Sobre o NotesApp</h1>
      <p class="about-intro">Um gerenciador de notas minimalista e profissional inspirado no Notion e Evernote.</p>
      
      <div class="content-section">
        <h3 class="section-title">Arquitetura do Sistema</h3>
        <p>Este sistema foi desenvolvido seguindo rigorosos padrões de engenharia de software:</p>
        <ul class="standard-list">
          <li>
            <strong>Camada de Persistência:</strong> Utilizando Spring Data JPA com banco de dados H2 para armazenamento eficiente e real.
          </li>
          <li>
            <strong>Lógica de Negócio:</strong> Centralizada em Services no backend, garantindo separação de responsabilidades.
          </li>
          <li>
            <strong>Interface SPA:</strong> Desenvolvida em Vue.js 3 com Vue Router para uma experiência fluida de Single Page Application.
          </li>
          <li>
            <strong>Comunicação REST:</strong> Integração assíncrona via Axios para todas as operações de CRUD.
          </li>
        </ul>
      </div>

      <div class="footer-actions">
        <button class="btn-primary" @click="$router.push('/')">Voltar para Notas</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.about-container { max-width: 800px; padding: 60px 20px; }
.about-card { background: white; border: 1px solid var(--border-color); padding: 50px; border-radius: 8px; box-shadow: var(--shadow-sm); }
.about-title { font-size: 2.2rem; font-weight: 700; color: var(--text-main); margin-bottom: 20px; letter-spacing: -0.04em; }
.about-intro { font-size: 1.1rem; color: var(--text-secondary); line-height: 1.5; margin-bottom: 40px; }
.content-section { margin-bottom: 40px; text-align: left; }
.section-title { font-size: 1rem; font-weight: 600; color: var(--text-main); margin-bottom: 15px; border-bottom: 1px solid #f0f0f0; padding-bottom: 8px; text-transform: uppercase; letter-spacing: 0.05em; }
.standard-list { padding-left: 20px; list-style-type: square; color: var(--text-main); }
.standard-list li { margin-bottom: 12px; line-height: 1.6; font-size: 0.95rem; }
.footer-actions { margin-top: 40px; text-align: center; }
.btn-primary { background: var(--primary-color); color: white; border: none; padding: 12px 30px; font-weight: 600; border-radius: 8px; }
.btn-primary:hover { background: var(--primary-hover); }
</style>
