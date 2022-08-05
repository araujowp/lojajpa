package main.java.br.com.alura.loja.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import main.java.br.com.alura.loja.dao.CategoriaDao;
import main.java.br.com.alura.loja.dao.ProdutoDAO;
import main.java.br.com.alura.loja.modelo.Categoria;
import main.java.br.com.alura.loja.modelo.Produto;
import main.java.br.com.alura.loja.util.JPAUtil;

public class CadastroProduto {
	public static void main(String[] args) {

		cadastrar();
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(entityManager);
		
		Produto produto  = produtoDao.buscarPorId(1l);
		System.out.println("busquei por id " + produto.getNome());
		
		List<Produto> produtos= produtoDao.buscarTodos();
		
		produtos.forEach(p -> System.out.println("encontrei : " + p.getNome()));
		
		produtos  = produtoDao.buscarPorNome("Mussarela quata"); 
		
		System.out.println("buscando por nome  temos " + produtos.size() + " produtos ");

		produtos  = produtoDao.buscarPorNomeCategoria("Laticinios"); 
		System.out.println("buscando por categoria temos " + produtos.size() + " produtos ");

		System.out.println("nosso preço é: " + produtoDao.buscarPreco("Mussarela quata"));
		
	}

	private static void cadastrar() {
		Categoria categoriaLaticinios = new Categoria("Laticinios");
		
		
		Produto produto = new Produto("Mussarela quata", //
				"Queijo mussarela quata", //
				categoriaLaticinios,//
				new BigDecimal("46.90"));

		EntityManager entityManager = JPAUtil.getEntityManager();

		CategoriaDao categoriaDao  = new CategoriaDao(entityManager);
		ProdutoDAO produtoDao = new ProdutoDAO(entityManager);

		entityManager.getTransaction().begin();
		
		categoriaDao.cadastrar(categoriaLaticinios);
		produtoDao.cadastrar(produto);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
