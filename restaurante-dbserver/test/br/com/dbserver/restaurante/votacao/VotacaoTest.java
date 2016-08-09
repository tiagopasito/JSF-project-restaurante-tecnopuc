package br.com.dbserver.restaurante.votacao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import br.com.dbserver.restaurante.modelo.Restaurante;
import br.com.dbserver.restaurante.modelo.Usuario;

public class VotacaoTest {

	Usuario usuario1;
	Usuario usuario2;
	Usuario usuario3;
	
	Restaurante restaurante1;
	Restaurante restaurante2;
	Restaurante restaurante3;
	
	LocalDate diaAtual;
	
	@Before
	public void criaUsuarios(){
		
		this.diaAtual = LocalDate.now();
		
		this.usuario1 = new Usuario("joao@dbserver.com", "joao");
		this.usuario2 = new Usuario("jose@dbserver.com", "jose");
		this.usuario3 = new Usuario("maria@dbserver.com", "maria");
		
		this.restaurante1 = new Restaurante("Panorama", 1l);
		this.restaurante2 = new Restaurante("Vitoria", 2l);
		this.restaurante1 = new Restaurante("Palatus", 3l);
	}
	
	@Test
	public void seNaoHouveVotoListaDeVotosEstaVazia(){
		
		Votacao.getlistaDeVotos().clear();
		
		assertEquals(0, Votacao.getlistaDeVotos().size());
	}
	
	@Test
	public void seHouveUmVotoListaDeVotosTemUmVoto(){		
		
		Votacao.getlistaDeVotos().clear();
		
		usuario1.vota(restaurante1);
		
		assertEquals(1, Votacao.getlistaDeVotos().size());
	}

	@Test
	public void seHouveDoisVotosListaDeVotosTemDoisVotos(){		

		Votacao.getlistaDeVotos().clear();
		
		usuario2.vota(restaurante1);
		usuario3.vota(restaurante1);
						
		assertEquals(2, Votacao.getlistaDeVotos().size());
	}
	
	@Test
	public void seUsuarioNaoVotouHojePodeVotarHoje(){
		
		Votacao.getlistaDeVotos().clear();
				
		assertEquals(false, this.usuario3.votouHoje());
	} 

	@Test
	public void seUsuarioJaVotouHojeNaoPodeMaisVotarHoje(){

		Votacao.getlistaDeVotos().clear();

		usuario1.vota(restaurante1);
		
		assertEquals(true, this.usuario1.votouHoje());
	}
	
	@Test
	public void seRestauranteFoiVotadoUmaVezEleTemUmVoto(){
	
		Votacao.getlistaDeVotos().clear();
		
		usuario1.vota(restaurante1);
		
		assertEquals(1, restaurante1.getVotos());
	}
	
	@Test
	public void seRestauranteFoiVotadoDuasVezesEleTemDoisVotos(){
	
		Votacao.getlistaDeVotos().clear();
		
		usuario1.vota(restaurante1);
		usuario2.vota(restaurante1);
		
		assertEquals(2, restaurante1.getVotos());
	}
}
