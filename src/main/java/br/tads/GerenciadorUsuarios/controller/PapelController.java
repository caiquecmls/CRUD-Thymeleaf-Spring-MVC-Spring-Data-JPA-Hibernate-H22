/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.GerenciadorUsuarios.controller;

import br.tads.GerenciadorUsuarios.model.Papel;
import br.tads.GerenciadorUsuarios.service.PapelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author caiqu
 */
@Controller
public class PapelController {

    @Autowired
    private PapelService papelService;

    @GetMapping("/papel")
    public String ModelAndView(Model model) {
        model.addAttribute("listGerenciadorPapel", papelService.getTodosPapel());
        return "papel";
    }

    @GetMapping("/novoPapel")
    public String novoPapel(Model model) {
        Papel papel = new Papel();
        model.addAttribute("papel", papel);
        return "novoPapel";
    }

    @PostMapping("/salvarPapel")
    public String salvarPapel(@ModelAttribute("papel") Papel papel) {
        papelService.salvarPapel(papel);
        return "redirect:/papel";
    }

    @GetMapping("/deletePapel/{id}")
    public String deleteUsuarios(@PathVariable(value = "id") int id) {
        this.papelService.deletePapelPorId(id);
        return "redirect:/papel";
    }
}
