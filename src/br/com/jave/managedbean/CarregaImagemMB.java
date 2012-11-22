package br.com.jave.managedbean;

import java.io.ByteArrayInputStream;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.jave.dao.PessoaDaoImpl;
import br.com.jave.modelo.Pessoa;
@ManagedBean
public class CarregaImagemMB {

    private StreamedContent fotoGerada;  
      
    public CarregaImagemMB() {  
        try {
        	
//    		Pessoa pessoa = new PessoaDaoImpl().pesquisarPorId((long)2);    		
//    		ByteArrayInputStream inputStream = new ByteArrayInputStream(pessoa.getFoto());    		
//            fotoGerada = new DefaultStreamedContent(inputStream, "image/jpeg");            
        } catch (Exception e) {  
            e.printStackTrace();
            System.out.println(e.getMessage());
        }  
    }  
      
    public StreamedContent getFotoGerada() {  
        return fotoGerada;  
    }  

}
