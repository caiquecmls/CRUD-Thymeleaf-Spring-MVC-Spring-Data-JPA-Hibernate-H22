/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.GerenciadorUsuarios.controller;

import br.tads.GerenciadorUsuarios.model.Papel;
import br.tads.GerenciadorUsuarios.model.Usuarios;
import br.tads.GerenciadorUsuarios.service.PapelService;
import br.tads.GerenciadorUsuarios.service.UsuarioService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author caiqu
 */
@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PapelService papelService;

    @GetMapping("/user")
    public String ModelAndView(Model model) {
        model.addAttribute("listGerenciadorUser", usuarioService.getTodosUsuarios());
        
        papelService.inserirNoBanco();
        return "lista";
    }

    @PostMapping("/salvarUsuarioUpdate")
    public ModelAndView salvarUpdate(
            @ModelAttribute @Valid Usuarios usuarios,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (usuarios.getIdsPapeis() != null && !usuarios.getIdsPapeis().isEmpty()) {
            Set<Papel> categoriasSelecionadas = new HashSet<>();
            for (Integer idCat : usuarios.getIdsPapeis()) {
                Papel cat = papelService.buscarPapelPorId(idCat);
                categoriasSelecionadas.add(cat);
                cat.setUsuarios(new HashSet<>(Arrays.asList(usuarios)));

            }
            usuarios.setPapel(categoriasSelecionadas);
        }
        
        
        usuarioService.salvarUsuario(usuarios);
        redirectAttributes.addFlashAttribute("alertaInsert",
                "Usuário " + usuarios.getNomeCompleto() + " salvo com sucesso");
        return new ModelAndView("redirect:/usuarios/user");
    }

    @GetMapping("/novoUsuario")
    public ModelAndView adicionarNovo() {
        return new ModelAndView("/novoUsuario")
                .addObject("usuarios", new Usuarios());
    }

//    @PostMapping("/salvarUsuarioUpdate")
//    public String salvarUsu(@ModelAttribute("usuarios") Usuarios usuarios) {
//        usuarioService.salvarUsuario(usuarios);
//
//        return "redirect:/";
//    }

    @PostMapping("/salvarUsuario")
    public ModelAndView salvar(
            @ModelAttribute @Valid Usuarios usuarios,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (usuarios.getIdsPapeis() != null && !usuarios.getIdsPapeis().isEmpty()) {
            Set<Papel> categoriasSelecionadas = new HashSet<>();
            usuarios.getIdsPapeis().stream().map((idCat) -> papelService.buscarPapelPorId(idCat)).map((cat) -> {
                categoriasSelecionadas.add(cat);
                return cat;
            }).forEachOrdered((cat) -> {
                cat.setUsuarios(new HashSet<>(Arrays.asList(usuarios)));
            });
            usuarios.setPapel(categoriasSelecionadas);
        }
        
        List<Usuarios> user = new ArrayList<Usuarios>();
        
        
        
        user = usuarioService.getTodosUsuarios();

        boolean verificar = false;
        for (Usuarios usuarios2 : user) {
            if (usuarios2.getUserName().equals(usuarios.getUserName())) {
                verificar = true;
            }
        }

        if (user.isEmpty() == true) {
            usuarioService.salvarUsuario(usuarios);
            redirectAttributes.addFlashAttribute("alertaInsert",
                    "Usuário " + usuarios.getNomeCompleto() + " salvo com sucesso");
            return new ModelAndView("redirect:/usuarios/user");
        } else {
            for (Usuarios usuarios1 : user) {
                if (usuarios1.getUserName().equals(usuarios.getUserName()) || verificar != false) {
                    redirectAttributes.addFlashAttribute("alertaInsertErro",
                            "Usuário " + usuarios.getUserName() + " já existe, digite um UserName diferente para inserir");
                    return new ModelAndView("redirect:/usuarios/user");
                } else {
                    usuarioService.salvarUsuario(usuarios);
                    redirectAttributes.addFlashAttribute("alertaInsert",
                            "Usuário " + usuarios.getNomeCompleto() + " salvo com sucesso");
                    return new ModelAndView("redirect:/usuarios/user");
                }
            }
        }
        return new ModelAndView("redirect:/usuarios/user");
    }

//        redirectAttributes.addFlashAttribute("alertaInsertErro",
//                "Usuário " + usuarios.getUserName()+ " já existe, digite um UserName diferente para inserir");}
    @GetMapping("/updateUsuarios/{id}")
    public String updateUsuarios(@PathVariable(value = "id") int id, Model model
    ) {

        Usuarios usuarios = usuarioService.buscarUsuarioPorId(id);
        model.addAttribute("usuarios", usuarios);
        if (usuarios.getPapel() != null && !usuarios.getPapel().isEmpty()) {
            Set<Integer> idsCategorias = new HashSet<>();
            for (Papel cat : usuarios.getPapel()) {
                idsCategorias.add(cat.getId());
            }
            usuarios.setIdsPapeis(idsCategorias);
        }

        return "updateUsuarios";
    }


    @GetMapping("/deleteUsuarios/{id}")
    public String deleteUsuarios(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes
    ) {
        this.usuarioService.deleteUsuariosPorId(id);
        redirectAttributes.addFlashAttribute("alerta",
                "Usuário ID " + id + " removido com sucesso");
        return "redirect:/usuarios/user";
    }

    @ModelAttribute("papel")
    public List<Papel> listTodosPapeis() {
        return papelService.getTodosPapel();
    }
}
