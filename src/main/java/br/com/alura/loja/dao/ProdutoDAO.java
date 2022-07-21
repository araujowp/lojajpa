package main.java.br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import main.java.br.com.alura.loja.modelo.Produto;

public class ProdutoDAO {

	private EntityManager entityManager;
	
	public ProdutoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Produto produto) {
		entityManager.persist(produto);
	}
	
	
}
