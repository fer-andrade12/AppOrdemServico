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

import com.ordem.servicos.models.Motoboy;
import com.ordem.servicos.repository.MotoboyRepository;

@Controller
public class MotoboyController {

	@Autowired
	private MotoboyRepository mr;
	
	// get formulario motoboy
	@RequestMapping("/cadastrarMotoboy")
	public String form() {
		return "motoboy/form-motoboy";
	}
	
	// post cadastra motoboy
	@RequestMapping(value = "cadastrarMotoboy", method = RequestMethod.POST)
	public String form(@Validated Motoboy motoboy, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarMotoboy";
		}

		mr.save(motoboy);
		attributes.addFlashAttribute("mensagem", "Motoboy cadastrado com sucesso!");
		return "redirect:/cadastrarMotoboy";
	}

	// lista motoboys
	@RequestMapping("/motoboys")
	public ModelAndView listaMotoboys() {
		ModelAndView mv = new ModelAndView("motoboy/lista-motoboy");
		Iterable<Motoboy> motoboys = mr.findAll();
		mv.addObject("motoboys", motoboys);
		return mv;
	}
	
	// detalhe motoboy
	@RequestMapping("/detalhes-motoboy/{id}")
	public ModelAndView detalhesMotoboy(@PathVariable("id") long id) {
		Motoboy motoboy = mr.findById(id);
		ModelAndView mv = new ModelAndView("motoboy/detalhes-motoboy");
		mv.addObject("motoboy", motoboy);
		return mv;
	}
	
	// get edita motoboy
	@RequestMapping("/editar-motoboy")
	public ModelAndView editarMotoboy(long id) {
		Motoboy motoboy = mr.findById(id);
		ModelAndView mv = new ModelAndView("motoboy/update-motoboy");
		mv.addObject("motoboy", motoboy);
		return mv;
	}
	
	// POST do FORM que atualiza a motoboy 
	@RequestMapping(value = "/editar-motoboy", method = RequestMethod.POST)
	public String updateMotoboy(@Validated Motoboy motoboy, BindingResult result, RedirectAttributes attributes) {
		mr.save(motoboy);
		attributes.addFlashAttribute("success", "Motoboy alterado com sucesso!");
		
		Long idLong =  motoboy.getId();
		String id = "" + idLong;
		return "redirect:/detalhes-motoboy/" + id;
	}
	
	// GET que deleta a motoboy
	@RequestMapping("/deletarMotoboy")
	public String deletarMotoboy(long id) {
		Motoboy motoboy = mr.findById(id);
		mr.delete(motoboy);
		return "redirect:/motoboys";
	}
	
}
