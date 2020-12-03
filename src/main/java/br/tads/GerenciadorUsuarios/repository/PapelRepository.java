/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.GerenciadorUsuarios.repository;

import br.tads.GerenciadorUsuarios.model.Papel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author caiqu
 */
@Repository
public interface PapelRepository extends JpaRepository<Papel, Integer> {
    
}
