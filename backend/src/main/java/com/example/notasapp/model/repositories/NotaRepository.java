package com.example.notasapp.model.repositories;

import com.example.notasapp.model.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.notasapp.model.entity.Usuario;
import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByUsuario(Usuario usuario);
}
