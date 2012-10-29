package br.com.jave.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.BasicConfigurator;

public class TesteGravaPessoa {
	
	public static void main(String[] args) {
		//BasicConfigurator.configure();
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jave");
		EntityManager manager = factory.createEntityManager();	
	}

}
