package com.ordem.servicos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ordem.servicos.repository.ClienteRepository;
import com.ordem.servicos.repository.MotoboyRepository;

@Controller
public class indexController {
	
	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private MotoboyRepository mr;
	
	//GET
	@RequestMapping("/")
	public ModelAndView abrirIndex() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
		
	//POST
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView IndexController(@RequestParam("buscar") String buscar, @RequestParam("nome") String nome) {
		
		ModelAndView mv = new ModelAndView("index");
		String mensagem = "Resultados da busca por " + buscar;
		
		if(nome.equals("nomeCliente")) {
			mv.addObject("clientes", cr.findByNome(buscar));
			
		}else if(nome.equals("nomeMotoboy")) {
			mv.addObject("motoboys", mr.findByNome(buscar));
		}else {
			mv.addObject("clientes", cr.findByNome(buscar));
			mv.addObject("motoboys", mr.findByNome(buscar));
		}
		
		mv.addObject("mensagem", mensagem);
		
		return mv;
	}
}
