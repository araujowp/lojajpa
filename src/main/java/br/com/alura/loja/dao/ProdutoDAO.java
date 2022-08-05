package main.java.br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

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
	
	public Produto buscarPorId(long id) {
		return entityManager.find(Produto.class, id);
	}

	public List<Produto> buscarTodos(){
		String jpql = "select p from Produto p";
		return entityManager.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome){
		String jpql = "select p from Produto p where p.nome = :nome";
		return entityManager.createQuery(jpql, Produto.class)
				.setParameter("nome",nome)
				.getResultList();
	}

	public List<Produto> buscarPorNomeCategoria(String nomeCategoria) {
		String jpql = "select p from Produto p where p.categoria.nome = :nome";
		return entityManager.createQuery(jpql, Produto.class)
				.setParameter("nome",nomeCategoria)
				.getResultList();
	}

	public BigDecimal buscarPreco(String nome) {
		String jpql = "select p.preco from Produto p where p.nome = ?1";
		return entityManager.createQuery(jpql, BigDecimal.class)
				.setParameter(1,nome)
				.getSingleResult();
	}
}
