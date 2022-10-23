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
import com.ordem.servicos.models.Endereco;
import com.ordem.servicos.models.Servico;
import com.ordem.servicos.repository.ClienteRepository;
import com.ordem.servicos.repository.EnderecoRepository;
import com.ordem.servicos.repository.ServicoRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository cr;

	@Autowired
	private EnderecoRepository er;

	@Autowired
	private ServicoRepository sr;

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

	@RequestMapping("/clientes")
	public ModelAndView listaClientes() {
		ModelAndView mv = new ModelAndView("cliente/lista-cliente");
		Iterable<Cliente> clientes = cr.findAll();
		mv.addObject("clientes", clientes);
		return mv;
	}

	// GET que lista enderecos e detalhes dos clientes
	@RequestMapping("/detalhes-cliente/{id}")
	public ModelAndView detalhesCliente(@PathVariable("id") long id) {
		Cliente cliente = cr.findById(id);
		ModelAndView mv = new ModelAndView("cliente/detalhes-cliente");
		mv.addObject("clientes", cliente);

		// lista de endereços baseada no id do cliente
		Iterable<Endereco> enderecos = er.findByCliente(cliente);
		mv.addObject("enderecos", enderecos);

		// lista de servicos baseada no id do cliente
		Iterable<Servico> servicos = sr.findByCliente(cliente);
		mv.addObject("servicos", servicos);

		return mv;

	}

	// POST que adiciona endereco
	@RequestMapping(value = "/detalhes-cliente/{id}", method = RequestMethod.POST)
	public String detalhesClientePost(@PathVariable("id") long id, Endereco enderecos, Servico servicos,BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/detalhes-cliente/{id}";
		}

		if (er.findByCep(enderecos.getCep()) != null) {
			attributes.addFlashAttribute("mensagem_erro", "Cep duplicado");
			return "redirect:/detalhes-cliente/{id}";
		}

		Cliente cliente = cr.findById(id);
		enderecos.setCliente(cliente);
		er.save(enderecos);
		sr.save(servicos);
		attributes.addFlashAttribute("mensagem", "Endereço adicionado com sucesso");
		return "redirect:/detalhes-cliente/{id}";

	}

	// GET que deleta cliente
	@RequestMapping("/deletarCliente")
	public String deletarCliente(long id) {
		Cliente cliente = cr.findById(id);
		cr.delete(cliente);
		return "redirect:/clientes";

	}

	// Métodos que atualizam funcionário
	// GET que chama o FORM de edição do funcionário
	@RequestMapping("/editar-cliente")
	public ModelAndView editarCliente(long id) {
		Cliente cliente = cr.findById(id);
		ModelAndView mv = new ModelAndView("cliente/update-cliente");
		mv.addObject("cliente", cliente);
		return mv;
	}

	// POST que atualiza o cliente
	@RequestMapping(value = "/editar-cliente", method = RequestMethod.POST)
	public String updateCliente(@Validated Cliente cliente, BindingResult result, RedirectAttributes attributes) {

		cr.save(cliente);
		attributes.addFlashAttribute("success", "Cliente alterado com sucesso!");

		long idLong = cliente.getId();
		String id = "" + idLong;
		return "redirect:/detalhes-cliente/" + id;

	}

	// GET que deleta endereco
	@RequestMapping("/deletarEndereco")
	public String deletarEndereco(String cep) {
		Endereco endereco = er.findByCep(cep);

		Cliente cliente = endereco.getCliente();
		String codigo = "" + cliente.getId();

		er.delete(endereco);
		return "redirect:/detalhes-cliente/" + codigo;

	}
}
