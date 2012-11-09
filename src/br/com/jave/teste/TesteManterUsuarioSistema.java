package br.com.jave.teste;

import javax.persistence.NoResultException;

import br.com.java.modelo.UsuarioSistema;
import br.com.jave.dao.PessoaDaoImpl;
import br.com.jave.dao.UsuarioSistemaDaoImpl;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.fachada.UsuarioSistemaFachadaImpl;

public class TesteManterUsuarioSistema {
	
	UsuarioSistema usuarioSistema = new UsuarioSistema();
	UsuarioSistemaFachadaImpl UsuarioSistemaFac = new UsuarioSistemaFachadaImpl();
	static final long ID_PESSOA = 1;
	
	public static void main(String[] args) throws Exception {
		TesteManterUsuarioSistema teste = new TesteManterUsuarioSistema();
		
		teste.addNovo();
		//teste.atualizar();
		//teste.remover();
		//teste.listarTodos();
		//teste.pesquisarId();
		//teste.validarUsuario();
	}
	
	public void addNovo() throws Exception{
		usuarioSistema.setLogin("admin");
		usuarioSistema.setSenha("admin");
		usuarioSistema.setAtivo(true);
		usuarioSistema.setPessoa(new PessoaDaoImpl().pesquisarPorId(ID_PESSOA));
		UsuarioSistemaFac.gravar(usuarioSistema);
	}

	public void atualizar() throws Exception{
		long id = 1;
		
		usuarioSistema = new UsuarioSistemaDaoImpl().pesquisarPorId(id);		
				
		usuarioSistema.setLogin("paulo.gomes");
		usuarioSistema.setSenha("paulo");
		usuarioSistema.setAtivo(true);
		UsuarioSistemaFac.gravar(usuarioSistema);		
	}

	public void remover() throws ExclusaoNaoPermitidaException, Exception{
		new UsuarioSistemaDaoImpl().excluir(null);
	}
	
	public void listarTodos() throws Exception{
		for(UsuarioSistema usuSist : new UsuarioSistemaDaoImpl().listarTodos()){
			System.out.println(usuSist.getLogin());
		}
	}
	
	public void pesquisarId() throws NoResultException, Exception{
		long ID = 1;
		System.out.println(new UsuarioSistemaDaoImpl().pesquisarPorId(ID).getLogin());
	}

	public void validarUsuario(){
		if (new UsuarioSistemaDaoImpl().validarUsuario("paulo.gomes", "paulo")){
			System.out.println("Usuario autorizado");
		}else{
			System.out.println("Usuario não autorizado");
		}
	}
}
