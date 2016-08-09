package br.com.dbserver.restaurante.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.dbserver.restaurante.modelo.Usuario;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		
		FacesContext context = event.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		
		System.out.println(nomePagina);
		
		//se tentar acessar a pagina de login, nao precisa fazer nada
		if("/login.xhtml".equals(nomePagina)){
			return;
		}
		
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
					
		//se usuario logou, nao precisa fazer nada 
		if(usuarioLogado != null) {
			return;
		}
		
		//se usuario nao logou e tentar acessar outra pagina, redireciona para a pagina de login
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		
		String paginaLogin = "/login?faces-redirect=true";
		
		handler.handleNavigation(context, null, paginaLogin);
		
		context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent event) {

		System.out.println("FASE: " +event.getPhaseId());		
	}

	@Override
	public PhaseId getPhaseId() {

		return PhaseId.RESTORE_VIEW;
	}
}
