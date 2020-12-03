/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.GerenciadorUsuarios.service;

import br.tads.GerenciadorUsuarios.model.Papel;
import br.tads.GerenciadorUsuarios.repository.PapelRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author caiqu
 */
@Service
public class PapelServiceImplementacao implements PapelService {

    @Autowired
    private PapelRepository papelRepository;

    private Map<Integer, Papel> mapItens;

    private int sequenciaId = 0;

    public PapelServiceImplementacao() {
        mapItens = new ConcurrentHashMap<>();
        mapItens.put(++sequenciaId, new Papel(sequenciaId, "ADMIN "));
        mapItens.put(++sequenciaId, new Papel(sequenciaId, "PAPEL1 "));
        mapItens.put(++sequenciaId, new Papel(sequenciaId, "PAPEL2 "));
        mapItens.put(++sequenciaId, new Papel(sequenciaId, "PAPEL3 "));       
    }
    
    
    @Override
    public  void inserirNoBanco(){
        List<Papel> papeis = new ArrayList<Papel>();
        papeis = getTodos();

        if (papeis.isEmpty()) {
            Papel papel = new Papel(1, "ADMIN");
            Papel papel1 = new Papel(2, "PAPEL1");
            Papel papel2 = new Papel(3, "PAPEL2");
            Papel papel3 = new Papel(4, "PAPEL3");
            this.papelRepository.save(papel);
            this.papelRepository.save(papel1);
            this.papelRepository.save(papel2);
            this.papelRepository.save(papel3);
        }
    }
    
    @Override
    public List<Papel> getTodosPapel() {
        return new ArrayList<>(mapItens.values());
    }
    
    public List<Papel> getTodos() {
        return new ArrayList<>(this.papelRepository.findAll());
    }
    
    @Override
    public void salvarPapel(Papel papel) {
        this.papelRepository.save(papel);
    }

    @Override
    public Papel buscarPapelPorId(int id) {
        Optional<Papel> auxiliar = papelRepository.findById(id);
        Papel usuarios = null;
        if (auxiliar.isPresent()) {
            usuarios = auxiliar.get();
        } else {
            throw new RuntimeException("Usuário não encontrado para o id informado :: " + id);
        }
        return usuarios;
    }

    @Override
    public void deletePapelPorId(int id) {
        this.papelRepository.deleteById(id);
    }

}
