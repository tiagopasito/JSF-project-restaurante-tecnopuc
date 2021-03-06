package br.com.dbserver.restaurante.bean;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dbserver.restaurante.dao.RestauranteDAO;
import br.com.dbserver.restaurante.modelo.Restaurante;
import br.com.dbserver.restaurante.modelo.Usuario;
import br.com.dbserver.restaurante.votacao.Votacao;

@ManagedBean							
@ViewScoped
public class RestauranteBean {

	private Restaurante restaurante = new Restaurante();

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
	/**
	 * Retorna todos restaurantes
	 */
	public List<Restaurante> getRestaurantes(){
		
		return new RestauranteDAO().getRestaurantes();
	}
	
	/**
	 * Usuario vota no restaurante
	 */
	public String votarNoRestaurante(){				
		
		//buscar restaurante por seu ID
		Restaurante restaurante = new RestauranteDAO().buscaPorId(this.restaurante.getId());
				
		FacesContext context = FacesContext.getCurrentInstance();		
		
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
	    ZoneId zone = ZoneId.of("Brazil/East");   
		
	    LocalTime now = LocalTime.now(zone);
	    
	    LocalTime horarioLimiteDeVoto = LocalTime.of(11, 59);

	    Restaurante restauranteVencedor = null;
	    
		if(now.isAfter(horarioLimiteDeVoto)){
			//11:59 usuario nao pode mais votar, deve ver qual restaurante eh o vencedor					
			restauranteVencedor = Votacao.verificaVencedor();			
		}
		else{
			usuarioLogado.vota(restaurante);
		}
		
		String paginaResultado = "resultado?faces-redirect=true";		    
	    return paginaResultado;
	}

	/**
	 * Vai pra pagina de resultado
	 */
	public String paginaResultado(){
		String paginaResultado = "resultado?faces-redirect=true";		    
	    return paginaResultado;		
	}
	
}
