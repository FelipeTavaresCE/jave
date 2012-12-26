package br.com.jave.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "simpleIndexConverter")
public class SimpleEntityConverter implements Converter {  
	  
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		System.out.println("valor do campo: " + value);
        if (value != null)
        	return this.getAttributesFrom(component).get(value);  
        return null;  
	}  
  
	public String getAsString(FacesContext ctx, UIComponent component, Object value){  
        if (value != null && !"".equals(value)) {  
            BaseEntity entity = (BaseEntity) value;  
            //adiciona item como atributo do componente
            this.addAttribute(component, entity);   
            Long codigo = entity.getId();
            if (codigo != null) {  
                return String.valueOf(codigo);  
            }  
        }  
        return (String) value;  
    }  
  
    protected void addAttribute(UIComponent component, BaseEntity baseEntity) {  
    	//codigo da entidade como chave neste caso
    	String key = baseEntity.getId().toString();  
        this.getAttributesFrom(component).put(key, baseEntity);  
    }  
  
    protected Map<String, Object> getAttributesFrom(UIComponent component) {  
        return component.getAttributes();  
    }  
  
} 