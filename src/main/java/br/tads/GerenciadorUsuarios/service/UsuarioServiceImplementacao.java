/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.GerenciadorUsuarios.service;

import br.tads.GerenciadorUsuarios.model.Papel;
import br.tads.GerenciadorUsuarios.model.Usuarios;
import br.tads.GerenciadorUsuarios.repository.PapelRepository;
import br.tads.GerenciadorUsuarios.repository.UsuariosRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author caiqu
 */
@Service
public class UsuarioServiceImplementacao implements UsuarioService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UsuariosRepository usuariosRepository;
    private PapelRepository papelRepository;

    @Override
    public List<Usuarios> getTodosUsuarios() {
        return usuariosRepository.findAll();
    }

    @Override
    public void salvarUsuario(Usuarios usuarios) {
        this.usuariosRepository.save(usuarios);
    }

    @Override
    public Usuarios buscarUsuarioPorId(int id) {
        Optional<Usuarios> auxiliar = usuariosRepository.findById(id);
        Usuarios usuarios = null;
        if (auxiliar.isPresent()) {
            usuarios = auxiliar.get();
        } else {
            throw new RuntimeException("Usuário não encontrado para o id informado :: " + id);
        }
        return usuarios;
    }

    @Override
    public void deleteUsuariosPorId(int id) {
        this.usuariosRepository.deleteById(id);
    }

    @Override
    public List<Papel> listTodosPapeis() {
        return papelRepository.findAll();
    }

    @Override
    public String buscarUsuarioPorUserName(String userName) {
        TypedQuery<String> jpqlQuery
                
                //SELECT ifnull(c.USER_NAME ,' ') FROM GERENCIADORUSER  c WHERE c.USER_NAME ='kkk'
                //SELECT USER_NAME  FROM GERENCIADORUSER WHERE USER_NAME  = 'Caiquemo';                
                = em.createQuery("SELECT c.userName FROM Usuarios c WHERE c.userName ="+"'"+userName+"'", String.class);
        return jpqlQuery.getSingleResult().toString();
    }
}
