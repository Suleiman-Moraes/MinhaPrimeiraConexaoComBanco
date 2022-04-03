package br.com.moraes;

import java.sql.SQLException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		ConvidadoDAO dao = new ConvidadoDAO();
		
		Convidado convidado = dao.inserir(new Convidado("João das Couve", 4));
		
		mostrar(dao.listar());
		
		convidado.setNome("Maria das Couve");
		dao.atualizar(convidado);
		
		mostrar(dao.listar());
		
		dao.apagar(convidado.getId());
		
		mostrar(dao.listar());
	}

	private static void mostrar(List<Convidado> convidados) throws SQLException {
        if(convidados != null) {
        	System.out.println("---------------------------------------------");
        	convidados.forEach(System.out::println);
        	System.out.println("---------------------------------------------\n\n");
        }
    }
}
