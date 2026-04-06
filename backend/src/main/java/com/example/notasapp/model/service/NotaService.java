package com.example.notasapp.model.service;

import com.example.notasapp.model.entity.Nota;
import com.example.notasapp.model.entity.Usuario;
import java.util.List;

public interface NotaService {
    Nota salvar(Nota nota);
    List<Nota> listarTodas();
    List<Nota> listarPorUsuario(Usuario usuario);
    Nota buscarPorId(Long id) throws Exception;
    Nota atualizar(Long id, Nota nota) throws Exception;
    Boolean excluir(Long id) throws Exception;
}
