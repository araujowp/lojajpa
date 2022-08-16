package main.java.br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	
	public List<Produto> buscaPorParametrosComCriteria(String nome, BigDecimal preco, LocalDateTime dataCadastro){
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> from = criteriaQuery.from(Produto.class);
		Predicate filtros = criteriaBuilder.and();
		
		if(nome != null && !nome.trim().isEmpty()) {
			filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("nome"), nome));
		}
		
		if(preco != null) {
			filtros = criteriaBuilder.and(filtros,criteriaBuilder.equal(from.get("preco"), preco));
		}
		
		if(dataCadastro!= null) {
			filtros = criteriaBuilder.and(filtros,criteriaBuilder.equal(from.get("dataCadastro"), dataCadastro));
		}
		criteriaQuery.where(filtros);
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
}
