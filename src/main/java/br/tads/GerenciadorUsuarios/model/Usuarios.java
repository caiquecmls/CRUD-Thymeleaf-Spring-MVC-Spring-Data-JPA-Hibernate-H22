/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.GerenciadorUsuarios.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author caiqu
 */
@Entity
@Table(name = "gerenciadoruser")

public class Usuarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotEmpty
    @Column(unique = true)
    private String userName;

    @Column(name = "nomeCompleto")
    private String nomeCompleto;

    @Column(name = "senha")
    private String senha;

    @Column(name = "status")
    private String status;

    @Column(name = "dataHora")
    private LocalDateTime dataHora;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USUARIOS_PAPEL",
            joinColumns = @JoinColumn(name = "ID_USUARIOS"),
            inverseJoinColumns = @JoinColumn(name = "ID_PAPEL")
    )

    private Set<Papel> papel;

    public Usuarios() {
    }

    public Usuarios(int id, String userName, String nomeCompleto, String senha, String status, LocalDateTime dataHora, Set<Papel> papel) {
        this.id = id;
        this.userName = userName;
        this.nomeCompleto = nomeCompleto;
        this.senha = senha;
        this.status = status;
        this.dataHora = dataHora;
        this.papel = papel;
    }

    public Usuarios(Set<Papel> papel) {
        this.papel = papel;
    }
    
    

    private transient Set<Integer> idsPapeis;

    public Set<Papel> getPapel() {
        return papel;
    }

    public void setPapel(Set<Papel> papel) {
        this.papel = papel;
    }

    public Set<Integer> getIdsPapeis() {
        return idsPapeis;
    }

    public void setIdsPapeis(Set<Integer> idsPapeis) {
        this.idsPapeis = idsPapeis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = (LocalDateTime.now());
    }
}
