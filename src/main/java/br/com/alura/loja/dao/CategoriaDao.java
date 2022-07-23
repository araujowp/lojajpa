package main.java.br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import main.java.br.com.alura.loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager entityManager;

	public CategoriaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Categoria categoria) {
		entityManager.persist(categoria);
	}
	
}
