package br.ufscar.rcms.view.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "imageCacheMB")
public class ImageCacheMB extends AbstractMB {

    private static final long serialVersionUID = -8022956061914668709L;

    private byte[] fotoPesquisador;

    public byte[] getFotoPesquisador() {
        byte[] fotoPesquisador = (byte[]) getFlashObject(FLASH_KEY_FOTO_PESQUISADOR);
        if (fotoPesquisador != null) {
            this.fotoPesquisador = fotoPesquisador;
        }
        return this.fotoPesquisador;
    }

}