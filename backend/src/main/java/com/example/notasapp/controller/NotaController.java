package com.example.notasapp.controller;

import com.example.notasapp.model.entity.Nota;
import com.example.notasapp.model.entity.Usuario;
import com.example.notasapp.model.service.NotaService;
import com.example.notasapp.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
@CrossOrigin(origins = "*")
public class NotaController {

    @Autowired
    private NotaService notaService;
    @Autowired
    private UsuarioService usuarioService;

    private Usuario getUsuarioLogado() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioService.buscarPorEmail(email);
    }

    @PostMapping("/novo")
    public ResponseEntity<?> criarNota(@RequestBody Nota nota) {
        try {
            nota.setUsuario(getUsuarioLogado());
            return ResponseEntity.status(201).body(notaService.salvar(nota));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Nota>> listarNotas() {
        Usuario usuario = getUsuarioLogado();
        return ResponseEntity.ok(notaService.listarPorUsuario(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(notaService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarNota(@PathVariable Long id, @RequestBody Nota nota) {
        try {
            return ResponseEntity.ok(notaService.atualizar(id, nota));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> apagarNota(@PathVariable Long id) {
        try {
            notaService.excluir(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
