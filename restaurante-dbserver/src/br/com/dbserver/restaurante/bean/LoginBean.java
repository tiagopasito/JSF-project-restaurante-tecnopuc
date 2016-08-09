package br.com.dbserver.restaurante.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbserver.restaurante.dao.UsuarioDAO;
import br.com.dbserver.restaurante.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuaLogin() {
	    System.out.println("Fazendo login do usuário " + this.usuario.getEmail());

	    FacesContext context = FacesContext.getCurrentInstance();
	    
	    boolean existeUsuario = new UsuarioDAO().existe(this.usuario);
	    
	    if(existeUsuario) {
		    context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
	    	
	    	String paginaRestaurante = "restaurante?faces-redirect=true";		    
		    return paginaRestaurante;
	    }
	    	
	    context.getExternalContext().getFlash().setKeepMessages(true);

	    context.addMessage(null, new FacesMessage("Usuário não encontrado"));		
	     
	    String paginaDeLogin = "login?faces-redirect=true";	    
	    return paginaDeLogin;
	}

	public String deslogar(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		
		String paginaLogin = "login?faces-redirect=true";		
		return paginaLogin;
	}
}
