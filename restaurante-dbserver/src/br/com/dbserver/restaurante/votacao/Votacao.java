package br.com.dbserver.restaurante.votacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.dbserver.restaurante.modelo.Restaurante;
import br.com.dbserver.restaurante.modelo.Usuario;

/**
 * Classe que grava o usuario, o dia em que votou e o restaurante votado
 */
public class Votacao {

	private LocalDate dia;
	private Usuario usuario;
	private Restaurante restaurante;
	
	private static List<Votacao> listaDeVotos = new ArrayList<Votacao>();
	
	private static List<Restaurante> vencedores = new ArrayList<Restaurante>();
	
	public LocalDate getDia() {
		return this.dia;
	}
	
	/**
	 * Cria o voto 
	 */
	public Votacao(Usuario usuario, LocalDate dia, Restaurante restaurante){
		this.usuario = usuario;
		this.dia = dia;
		this.restaurante = restaurante;
	}
	
	public void setDia(LocalDate dia) {
		this.dia = dia;	
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static List<Votacao> getlistaDeVotos() {
		return listaDeVotos;
	}

	public static void setlistaDeVotos(Votacao voto) {
		Votacao.listaDeVotos.add(voto);
	}

	public static List<Restaurante> getVencedores() {
		return vencedores;
	}

	public static void setVencedores(Restaurante vencedor) {
		Votacao.vencedores.add(vencedor);
	}
	
	public Restaurante getRestaurante() {
		return this.restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		
		this.restaurante = restaurante;
	}

	public static Restaurante verificaVencedor(){
		
		List<Votacao> listaDeVotos =  Votacao.getlistaDeVotos();										

		//nao ha restaurantes concorrendo
		if(listaDeVotos.size() == 0){
			return null;
		}

		//inicia com primeiro restaurante da lista
		Restaurante restauranteComMaisVotos = listaDeVotos.get(0).getRestaurante();

		if(listaDeVotos.size() > 1){			
			
			for(int i = 0; i < listaDeVotos.size(); i++){			
				
				Restaurante proximoRestaurante = listaDeVotos.get(i).getRestaurante();
				
				if(restauranteComMaisVotos.getVotos() < proximoRestaurante.getVotos()){
					
					restauranteComMaisVotos = proximoRestaurante;
				}
			}
			return restauranteComMaisVotos;
		}
		//soh tem 1 restaurante concorrente
		return restauranteComMaisVotos;
	}
}