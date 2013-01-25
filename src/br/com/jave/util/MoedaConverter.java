package br.com.jave.util;



import java.text.DecimalFormat;
import java.text.ParseException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;


public class MoedaConverter implements javax.faces.convert.Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        try {
            return NumberUtils.parseBigDecimal(s);
        } catch (ParseException e) {
            throw new ConverterException(new FacesMessage("Erro na conversão para BigDecimal"));
        }
    }
    
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return new DecimalFormat(NumberUtils.BIG_DECIMAL_FORMAT).format(o);
    }


}

