/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.GerenciadorUsuarios.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author caiqu
 */
@Entity
public class Papel implements Serializable {

    @Id
    private Integer id;
    
    @Column(unique = true)
    private String nome;

    @ManyToMany(mappedBy = "papel", fetch = FetchType.LAZY)
    private Set<Usuarios> usuarios;

    public Papel() {
    }

    public Papel(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Papel(String nome) {
        this.nome = nome;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

}
