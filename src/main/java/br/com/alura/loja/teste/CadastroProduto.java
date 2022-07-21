package main.java.br.com.alura.loja.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.java.br.com.alura.loja.modelo.Produto;

public class CadastroProduto {
	public static void main(String[] args) {
		
		Produto produto = new Produto();
		
		produto.setNome("mussarela quata");
		produto.setDescricao("Queijo mussarela quata");
		produto.setPreco(new BigDecimal("46.90"));
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("loja");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		
	}
}
