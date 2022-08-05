package main.java.br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import main.java.br.com.alura.loja.modelo.Cliente;

public class ClienteDAO {

	private EntityManager entityManager;

	public ClienteDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Cliente cliente) {
		entityManager.persist(cliente);
	}

	public Cliente buscaPorId(long id) {
		return entityManager.find(Cliente.class, id);
	}
	
}
