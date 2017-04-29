package com.agroWeb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.agroWeb.service.CadastroUsuarioService;

@Configuration
@ComponentScan(basePackageClasses = CadastroUsuarioService.class)
public class ServiceConfig {


}
