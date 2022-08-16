package main.java.br.com.alura.loja.teste;

import java.util.List;

import javax.persistence.EntityManager;

import main.java.br.com.alura.loja.dao.ClienteDAO;
import main.java.br.com.alura.loja.dao.PedidoDAO;
import main.java.br.com.alura.loja.dao.ProdutoDAO;
import main.java.br.com.alura.loja.modelo.Cliente;
import main.java.br.com.alura.loja.modelo.Pedido;
import main.java.br.com.alura.loja.modelo.PedidoItem;
import main.java.br.com.alura.loja.modelo.Produto;
import main.java.br.com.alura.loja.modelo.vo.RelatorioVendasVo;
import main.java.br.com.alura.loja.util.JPAUtil;

public class CadastrarPedido {

	public static void main(String[] args) {
		CadastroProduto.cadastrarProdutosCategorias();		
		
		EntityManager entityManager = JPAUtil.getEntityManager();

		PedidoDAO pedidoDao = criarpedidos(entityManager);
		
		List<RelatorioVendasVo> itens = pedidoDao.relatorioVendas();
		itens.forEach(System.out::println);
		
		Pedido pedido1 = pedidoDao.buscarPorIdComCliente(1l);
		entityManager.close();
		
		//testando join fetch
		System.out.println("O cliente do pedido 1 é: " + pedido1.getCliente().getNome());
		
	}

	private static PedidoDAO criarpedidos(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		Cliente julio = new Cliente("Julio Cesar", "22334455");
		Cliente flavia = new Cliente("Flavia", "33445566");
		ClienteDAO clienteDAO = new ClienteDAO(entityManager);		
		clienteDAO.cadastrar(julio);
		clienteDAO.cadastrar(flavia);

		Cliente julioSalvo = clienteDAO.buscaPorId(1l);
		Cliente flaviaSalva = clienteDAO.buscaPorId(2l);
		

		System.out.println("aqui vamos buscar os produtos");
		ProdutoDAO produtoDAO  = new ProdutoDAO(entityManager);
		Produto produto1 = produtoDAO.buscarPorId(1l);
		Produto produto2 = produtoDAO.buscarPorId(2l);
		Produto produto3 = produtoDAO.buscarPorId(3l);
		Produto produto4 = produtoDAO.buscarPorId(4l);
		
		Pedido pedidoJulio = new Pedido(julioSalvo);
		pedidoJulio.adicionarItem(new PedidoItem(10, pedidoJulio, produto1));
		pedidoJulio.adicionarItem(new PedidoItem(7, pedidoJulio, produto2));

		Pedido pedidoFlavia = new Pedido(flaviaSalva);
		pedidoFlavia.adicionarItem(new PedidoItem(3, pedidoFlavia, produto3));
		pedidoFlavia.adicionarItem(new PedidoItem(1, pedidoFlavia, produto4));
		
		PedidoDAO pedidoDao = new PedidoDAO(entityManager);
		pedidoDao.cadastrar(pedidoJulio);
		pedidoDao.cadastrar(pedidoFlavia);
		
		entityManager.getTransaction().commit();
		
		System.out.println("valor total em pedidos : " + pedidoDao.getValorTotal());
		return pedidoDao;
	}

}
