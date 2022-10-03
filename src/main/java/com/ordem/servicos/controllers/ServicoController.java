package com.ordem.servicos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ordem.servicos.models.Servico;
import com.ordem.servicos.repository.ClienteRepository;
import com.ordem.servicos.repository.MotoboyRepository;
import com.ordem.servicos.repository.ServicoRepository;

@Controller
public class ServicoController {

	@Autowired
	private ServicoRepository sr;
	
	@Autowired
	private ClienteRepository cr;

	@Autowired
	private MotoboyRepository mr;
	
	@RequestMapping("/cadastraServico")
	public String form() {
		return "servico/form-servico";
	}

	@RequestMapping(value = "/cadastraServico", method = RequestMethod.POST)
	public String form(@Validated Servico servico, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastraServico";
		}

		sr.save(servico);
		attributes.addFlashAttribute("mensagem", "Ordem cadastrado com sucesso!");
		return "redirect:/cadastraServico";
	}
	
	

	// POST
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("nome") String nome) {

		ModelAndView mv = new ModelAndView("index");
		String mensagem = "Resultados da busca por " + buscar;

		if (nome.equals("nomecliente")) {
			mv.addObject("clientes", cr.findByNome(buscar));

		} else if (nome.equals("nomemotoboy")) {
			mv.addObject("motoboys", mr.findByNome(buscar));

		}else {
			mv.addObject("clientes", cr.findByNome(buscar));
			mv.addObject("motoboy", mr.findByNome(buscar));
		}

		mv.addObject("mensagem", mensagem);

		return mv;
	}
}
