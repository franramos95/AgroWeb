package com.agroWeb.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.agroWeb.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.agroWeb.thymeleaf.processor.MenuAttributeTagProcessor;
import com.agroWeb.thymeleaf.processor.MessageElementTagProcessor;
import com.agroWeb.thymeleaf.processor.OrderElementTagProcessor;
import com.agroWeb.thymeleaf.processor.PaginationElementTagProcessor;

public class AgroWebDialect  extends AbstractProcessorDialect {
	public AgroWebDialect() {
		super("Franciele AgroWeb", "agroWeb", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new  MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}

