package com.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bookstore.service.DbService;

@Configuration
public class ServiceConfig {
	
	@Autowired
    DbService  dbService;
	
	@Bean      // intaciando sempre a startar a aplicacao o metodo dpara preencgher o bancp.
	public void instanciaBancoDados() {
		this.dbService.instanciandoBancoDados();
	}

}
