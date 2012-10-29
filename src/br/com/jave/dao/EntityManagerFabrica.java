package br.com.jave.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFabrica {
	
	public EntityManager obterEntityManager(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jave");
		return factory.createEntityManager(); 
	}

}
