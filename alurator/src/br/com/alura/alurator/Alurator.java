package br.com.alura.alurator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import br.com.alura.alurator.protocolo.Request;
import br.com.alura.alurator.reflexao.Reflexao;

public class Alurator {	
	
	private String pacoteBase;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;

	}

	public Object executa(String url) {
		// TODO - processa a requisicao executando o metodo
		// da classe em questao
		
		// produto lista
		
		Request request = new Request(url);
		
		String nomeControle = request.getNomeControle();
		String nomeMetodo = request.getNomeMetodo();
		Map<String, Object> params = request.getQueryParams();
		
		Object retorno = new Reflexao()
				.refleteClasse(pacoteBase + nomeControle)
				.criaInstancia()
				.getMetodo(nomeMetodo, params)
				.comTratamentoDeExcecao((metodo, e) -> {
                    System.out.println("Erro no método " + metodo.getName() + " da classe " + metodo.getDeclaringClass().getName() + ".\n\n");
                    throw new RuntimeException("ERRO!");
                })
				.invoca();
		
		
		
		System.out.println(retorno);
		
		return retorno;
			
		
	}
}
