package br.com.jave.util;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.primefaces.model.DefaultStreamedContent;

public class UploadDeImagem {
	
	public static DefaultStreamedContent gerarApresentacaoTela(byte[] imagemBytes){
		ByteArrayInputStream inputStream = new ByteArrayInputStream(imagemBytes);    		
		return new DefaultStreamedContent(inputStream, "image/jpeg");		
	}
	
	public static void gravarImagemNoDico(byte[] imagemBytes, String localDeGravacao) throws IOException{
		FileOutputStream fileOuputStream;
		fileOuputStream = new FileOutputStream(localDeGravacao);
		fileOuputStream.write(imagemBytes);
		fileOuputStream.close();		
	}

}
