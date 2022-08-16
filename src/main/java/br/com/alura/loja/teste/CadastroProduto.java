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

		cadastrarProdutosCategorias();
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(entityManager);

		Produto produto = produtoDao.buscarPorId(1l);
		System.out.println("busquei por id " + produto.getNome());

		List<Produto> produtos = produtoDao.buscarTodos();

		produtos.forEach(p -> System.out.println("encontrei : " + p.getNome()));

		produtos = produtoDao.buscarPorNome("Mussarela quata");

		System.out.println("buscando por nome  temos " + produtos.size() + " produtos ");

		produtos = produtoDao.buscarPorNomeCategoria("Laticinios");
		System.out.println("buscando por categoria temos " + produtos.size() + " produtos ");

		System.out.println("nosso preço é: " + produtoDao.buscarPreco("Mussarela quata"));

		produtos = produtoDao.buscaPorParametrosComCriteria("Mussarela quata", null, null);

		produtos = produtoDao.buscaPorParametrosComCriteria(null, new BigDecimal("10.50"), null);
		System.out.println("fim");
		
		
	}

	public static void cadastrarProdutosCategorias() {
		Categoria laticinios = new Categoria("Laticinios");
		Categoria brinquedos = new Categoria("Brinquedos");
		Categoria limpeza = new Categoria("Limpeza");

		Produto mussarela = new Produto("Mussarela quata", "Queijo mussarela quata", laticinios,  new BigDecimal("46.90"));
		Produto requeijao = new Produto("Requeijão quata", "Requeijão quata pote 200gr", laticinios, new BigDecimal("10.50"));
		Produto funkoPop = new Produto("Funko pop Homem Aranha", "Boneco Funko pop Homem Aranha", brinquedos, new BigDecimal("156.90"));
		Produto detergente = new Produto("Detergente Limpol", "Detergente Limpol 500ml", limpeza, new BigDecimal("3.90"));

		EntityManager entityManager = JPAUtil.getEntityManager();

		CategoriaDao categoriaDao = new CategoriaDao(entityManager);
		ProdutoDAO produtoDao = new ProdutoDAO(entityManager);

		entityManager.getTransaction().begin();

		categoriaDao.cadastrar(laticinios);
		categoriaDao.cadastrar(brinquedos);
		categoriaDao.cadastrar(limpeza);
		
		produtoDao.cadastrar(mussarela);
		produtoDao.cadastrar(requeijao);
		produtoDao.cadastrar(funkoPop);
		produtoDao.cadastrar(detergente);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
