package com.alvorecer.venus.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.alvorecer.venus.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.alvorecer.venus.thymeleaf.processor.MenuAttributeTagProcessor;
import com.alvorecer.venus.thymeleaf.processor.MessageElementTagProcessor;
import com.alvorecer.venus.thymeleaf.processor.OrderElementTagProcessor;
import com.alvorecer.venus.thymeleaf.processor.PaginationElementTagProcessor;

public class AlvorecerDialect extends AbstractProcessorDialect{

	public AlvorecerDialect() {
		super("Alvorecer alvorecer", "alvorecer", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));

		
		return processadores;
	}	
	
	

}
