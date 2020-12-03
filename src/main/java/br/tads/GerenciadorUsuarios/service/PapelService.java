/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.GerenciadorUsuarios.service;

import br.tads.GerenciadorUsuarios.model.Papel;
import java.util.List;

/**
 *
 * @author caiqu
 */
public interface PapelService {

    List<Papel> getTodosPapel();

    void salvarPapel(Papel papel);

    Papel buscarPapelPorId(int id);

    void deletePapelPorId(int id);

    public void inserirNoBanco();
}
