package br.com.jave.managedbean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.RandomAccessFile;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.java.modelo.Pessoa;
import br.com.jave.dao.PessoaDaoImpl;
@ManagedBean
public class CarregaImagemMB {

    private StreamedContent fotoGerada;  
      
    public CarregaImagemMB() {  
        try {
        	
//    		RandomAccessFile arquivoEntrada = new RandomAccessFile("/home/desenv/livre/carro.jpg", "r");
//    		byte[] arquivoFoto = new byte[(int)arquivoEntrada.length()];
//    		arquivoEntrada.read(arquivoFoto);
//    		arquivoEntrada.close();
//    		File arquivoEntrada__ = new File("/home/desenv/livre/carro.jpg");            
//    		ByteArrayInputStream is = new ByteArrayInputStream(arquivoFoto);
//    		fotoGerada = new DefaultStreamedContent(is, "image/jpeg");
    		//--------------------------------------------------------------------
    		
    		Pessoa pessoa = new PessoaDaoImpl().pesquisarPorId((long)2);
    		
    		ByteArrayInputStream inputStream = new ByteArrayInputStream(pessoa.getFoto());
    		
            fotoGerada = new DefaultStreamedContent(inputStream, "image/jpeg");
            
        } catch (Exception e) {  
            e.printStackTrace();
            System.out.println(e.getMessage());
        }  
    }  
      
    public StreamedContent getFotoGerada() {  
        return fotoGerada;  
    }  

//	RandomAccessFile arquivoEntrada = new RandomAccessFile("/home/desenv/livre/carro.jpg", "r");
//	byte[] arquivoFoto = new byte[(int)arquivoEntrada.length()];
//	arquivoEntrada.read(arquivoFoto);
//	arquivoEntrada.close();
//
//	File arquivoEntrada__ = new File("/home/desenv/livre/carro.jpg");
//  barcode = new DefaultStreamedContent(new FileInputStream(arquivoEntrada__), "image/jpeg");
    
    
}
