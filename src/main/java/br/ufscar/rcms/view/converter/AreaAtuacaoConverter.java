package br.ufscar.rcms.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;

@FacesConverter(value = "areaAtuacaoConverter", forClass = AreaAtuacao.class)
public class AreaAtuacaoConverter  implements Converter{	
	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (AreaAtuacao) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof AreaAtuacao) {
        	AreaAtuacao entity= (AreaAtuacao) value;
            if (entity != null && entity instanceof AreaAtuacao && entity.getIdAreaAtuacao() != null) {
                uiComponent.getAttributes().put( entity.getIdAreaAtuacao().toString(), entity);
                return entity.getIdAreaAtuacao().toString();
            }
        }
        return "";
    }

}
