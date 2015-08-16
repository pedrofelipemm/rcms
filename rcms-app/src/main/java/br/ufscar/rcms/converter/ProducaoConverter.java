package br.ufscar.rcms.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.servico.ProducaoService;

@ViewScoped
@ManagedBean(name = "producaoConverter")
public class ProducaoConverter implements Converter {

    @ManagedProperty("#{producaoService}")
    private ProducaoService producaoService;

    public ProducaoService getProducaoService() {
        return producaoService;
    }

    public void setProducaoService(ProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        
        if (value != null && value.trim().length() > 0) {
            try {
                return producaoService.buscarPorId(Long.parseLong(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error",
                        "Nenhuma Produção Válida"));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {

        if (object != null) {
            return String.valueOf(((Producao) object).getIdProducao());
        } else {
            return null;
        }
    }
}