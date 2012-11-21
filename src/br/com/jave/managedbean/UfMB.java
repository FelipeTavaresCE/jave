package br.com.jave.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Controller;

import br.com.java.modelo.Uf;
import br.com.jave.dao.GenericDao;
import br.com.jave.dao.UfDaoImpl;

@SuppressWarnings("serial")
@Controller
@ManagedBean
@RequestScoped
public class UfMB implements Serializable{

	Uf uf = new Uf();
	
	public UfMB(){}

	public void salvar(){
		GenericDao<Uf> ufDao = new UfDaoImpl();
		try {
			ufDao.gravar(uf);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}	

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}
}