package br.com.jave.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataHoraUtil {

	private static SimpleDateFormat formatoDaData     = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat formatoDaDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public static Date criarData(String dataDigitada) throws ParseException {
		return formatoDaData.parse(dataDigitada);
	}
	
	public static Date criarDataHora(String dataHoraDigitada) throws ParseException {
		return formatoDaDataHora.parse(dataHoraDigitada);
	}

	public static String formatarDataPtBR(Date data){
		return formatoDaData.format(data);
	}

	public static String formatarDatahoraPtBR(Date dataHora){
		return formatoDaDataHora.format(dataHora);
	}
	
}
