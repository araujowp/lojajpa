package main.java.br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import main.java.br.com.alura.loja.modelo.Pedido;

public class PedidoDAO {

	private EntityManager entityManager;

	public PedidoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Pedido pedido) {
		entityManager.persist(pedido);
	}
	
}
