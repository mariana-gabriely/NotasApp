package com.example.notasapp.model.service;

import com.example.notasapp.model.entity.Usuario;
import com.example.notasapp.model.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.notasapp.model.entity.Papel;
import com.example.notasapp.model.repositories.PapelRepository;
import java.util.ArrayList;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PapelRepository papelRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean criarUsuario(Usuario usuario) {
        try {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

            if (usuario.getPapeis() == null || usuario.getPapeis().isEmpty()) {
                List<Papel> papeis = new ArrayList<>();
                papelRepository.findById(1L).ifPresent(papeis::add);
                usuario.setPapeis(papeis);
            }

            usuarioRepository.save(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Boolean deletarUsuarioPorId(Long id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
