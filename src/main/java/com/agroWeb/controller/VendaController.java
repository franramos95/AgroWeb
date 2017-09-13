package com.agroWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendas")
public class VendaController {
	
	@GetMapping("/novo")
	public String nova() {
		return "venda/CadastroVenda";
	}

}