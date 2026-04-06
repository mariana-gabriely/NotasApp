package com.example.notasapp.model.service;

import com.example.notasapp.model.entity.Nota;
import com.example.notasapp.model.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.notasapp.model.entity.Usuario;
import java.util.List;

@Service
public class NotaServiceImp implements NotaService {

    @Autowired
    private NotaRepository repository;

    @Override
    public Nota salvar(Nota nota) {
        return repository.save(noteSetDefault(nota));
    }

    @Override
    public List<Nota> listarTodas() {
        return repository.findAll();
    }

    @Override
    public List<Nota> listarPorUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    @Override
    public Nota buscarPorId(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Nota não encontrada"));
    }

    @Override
    public Nota atualizar(Long id, Nota notaDetalhes) throws Exception {
        Nota nota = buscarPorId(id);
        nota.setTitulo(notaDetalhes.getTitulo());
        nota.setConteudo(notaDetalhes.getConteudo());
        nota.setCor(notaDetalhes.getCor());
        return repository.save(nota);
    }

    @Override
    public Boolean excluir(Long id) throws Exception {
        Nota nota = buscarPorId(id);
        repository.delete(nota);
        return true;
    }

    private Nota noteSetDefault(Nota nota) {
        if (nota.getCor() == null || nota.getCor().isEmpty()) {
            nota.setCor("#ffffff");
        }
        return nota;
    }
}
