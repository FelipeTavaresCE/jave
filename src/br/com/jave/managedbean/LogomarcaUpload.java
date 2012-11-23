package br.com.jave.managedbean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LogomarcaUpload {

	String file;
	
	public void upload() {
			System.out.println("Entrou na função");
			System.out.println(file);
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	
	
}