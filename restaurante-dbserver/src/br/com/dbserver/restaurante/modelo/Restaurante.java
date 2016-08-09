package br.com.dbserver.restaurante.modelo;

public class Restaurante {

	private Long id = 0l;
	private String nome;
	private int votos = 0;
	
	public Restaurante() {}
	
	public Restaurante(String nome, long id) {
		this.nome = nome;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;	
	}	
	
	public String getNome() {
		return nome;
	}

	public int getVotos() {
		return votos;
	}

	public void recebeVoto() {
		this.setVotos(this.getVotos() + 1);
	}

	public void setVotos(int voto) {
		this.votos = voto;
	}

	/**
	 * Se restaurantes tem nomes iguais, sao iguais
	 */
	@Override
	public boolean equals(Object obj){

		if(obj == null){
			return false;
		}
		
		boolean nomesIguais = this.getNome().equals( ((Restaurante)obj).getNome() );		
		
		if(nomesIguais){
			return true;	
		}
		
		return false;
	}
}