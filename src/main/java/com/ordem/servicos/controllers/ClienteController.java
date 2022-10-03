package com.ordem.servicos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ordem.servicos.models.Cliente;
import com.ordem.servicos.repository.ClienteRepository;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository cr;
	
	@RequestMapping("/cadastrarCliente")
	public String form() {
		return "cliente/form-cliente";
	}
	
	@RequestMapping(value = "cadastrarCliente", method = RequestMethod.POST)
	public String form(@Validated Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarCliente";
		}

		cr.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
		return "redirect:/cadastrarCliente";
	}
	
	// lista clientes
		@RequestMapping("/clientes")
		public ModelAndView listaClientes() {
			ModelAndView mv = new ModelAndView("cliente/lista-cliente");
			Iterable<Cliente> clientes = cr.findAll();
			mv.addObject("clientes", clientes);
			return mv;
		}
		
		// detalhe cliente
		@RequestMapping("/detalhes-cliente/{id}")
		public ModelAndView detalhesCliente(@PathVariable("id") long id) {
			Cliente cliente = cr.findById(id);
			ModelAndView mv = new ModelAndView("cliente/detalhes-cliente");
			mv.addObject("cliente", cliente);
			return mv;
		}
		
		// get edita cliente
		@RequestMapping("/editar-cliente")
		public ModelAndView editarCliente(long id) {
			Cliente cliente= cr.findById(id);
			ModelAndView mv = new ModelAndView("cliente/update-cliente");
			mv.addObject("cliente", cliente);
			return mv;
		}
		
		// POST do FORM que atualiza a cliente 
		@RequestMapping(value = "/editar-cliente", method = RequestMethod.POST)
		public String updateCliente(@Validated Cliente cliente, BindingResult result, RedirectAttributes attributes) {
			cr.save(cliente);
			attributes.addFlashAttribute("success", "Cliente alterado com sucesso!");
			
			Long idLong =  cliente.getId();
			String id = "" + idLong;
			return "redirect:/detalhes-cliente/" + id;
		}
		
		// GET que deleta a cliente
		@RequestMapping("/deletarCliente")
		public String deletarCliente(long id) {
			Cliente cliente = cr.findById(id);
			cr.delete(cliente);
			return "redirect:/clientes";
		}
}
