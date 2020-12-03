/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.GerenciadorUsuarios.service;

import br.tads.GerenciadorUsuarios.model.Papel;
import br.tads.GerenciadorUsuarios.model.Usuarios;
import java.util.List;

/**
 *
 * @author caiqu
 */


public interface UsuarioService {
    List<Usuarios> getTodosUsuarios();   
    void salvarUsuario(Usuarios usuarios);
    List<Papel> listTodosPapeis();  
    Usuarios buscarUsuarioPorId(int id);
    void deleteUsuariosPorId(int id);
    String buscarUsuarioPorUserName(String userName);
}
