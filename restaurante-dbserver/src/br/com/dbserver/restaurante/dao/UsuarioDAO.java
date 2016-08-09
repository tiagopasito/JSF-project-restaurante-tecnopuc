package br.com.dbserver.restaurante.dao;

import java.util.List;

import br.com.dbserver.restaurante.modelo.Usuario;


public class UsuarioDAO {

	/**
	 * Retorna todos usuarios do banco
	 */
	public List<Usuario> getUsuarios(){
		
		return new DAO().listaDeUsuarios();
	}

	/**
	 * Existe o usuario no banco	 
	 */
	public boolean existe(Usuario usuario) {
						
		for(Usuario usuarioAtual : getUsuarios()){
			
			if(usuario.equals(usuarioAtual)){
				return true;				
			}
		}				
		return false;
	}
}
