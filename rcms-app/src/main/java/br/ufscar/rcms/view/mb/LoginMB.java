package br.ufscar.rcms.view.mb;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import br.ufscar.rcms.modelo.entidades.Usuario;

@RequestScoped
@ManagedBean(name = "loginMB")
public class LoginMB extends AbstractMB{
	
	
    /**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = 1L;


	private String userName;
    private String password;
    
     public String login() {
	    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(this.getUserName(), this.getPassword());
        SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.getContext().setAuthentication(token);
        return "consultaPesquisadores";
    }
     

    public String cancel() {
        return null;
    }

	@Override
	protected void limparDados() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void carregarDados() {
		// TODO Auto-generated method stub
		
	}
 
    public String getUserName() { return userName; }
 
    public void setUserName(String userName) { this.userName = userName; }
 
   
    public String getPassword() { return password; }
 
    public void setPassword(String password) { this.password = password; }


}
