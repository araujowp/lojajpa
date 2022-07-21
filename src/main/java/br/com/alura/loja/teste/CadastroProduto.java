package main.java.br.com.alura.loja.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import main.java.br.com.alura.loja.dao.ProdutoDAO;
import main.java.br.com.alura.loja.modelo.Categoria;
import main.java.br.com.alura.loja.modelo.Produto;
import main.java.br.com.alura.loja.util.JPAUtil;

public class CadastroProduto {
	public static void main(String[] args) {

		Produto produto = new Produto("mussarela quata", //
				"Queijo mussarela quata", //
				Categoria.LATICINIOS,//
				new BigDecimal("46.90"));

		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(entityManager);

		entityManager.getTransaction().begin();
		produtoDao.cadastrar(produto);
		entityManager.getTransaction().commit();
		entityManager.close();

	}
}
