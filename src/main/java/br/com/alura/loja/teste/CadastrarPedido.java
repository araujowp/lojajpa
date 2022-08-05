package main.java.br.com.alura.loja.teste;

import javax.persistence.EntityManager;

import main.java.br.com.alura.loja.dao.ClienteDAO;
import main.java.br.com.alura.loja.dao.PedidoDAO;
import main.java.br.com.alura.loja.dao.ProdutoDAO;
import main.java.br.com.alura.loja.modelo.Cliente;
import main.java.br.com.alura.loja.modelo.Pedido;
import main.java.br.com.alura.loja.modelo.PedidoItem;
import main.java.br.com.alura.loja.modelo.Produto;
import main.java.br.com.alura.loja.util.JPAUtil;

public class CadastrarPedido {

	public static void main(String[] args) {
		CadastroProduto.cadastrar();		
		
		EntityManager entityManager = JPAUtil.getEntityManager();

		System.out.println("Cadastrar o cliente");
		
		entityManager.getTransaction().begin();
		Cliente cliente = new Cliente("Julio Cesar", "22334455");
		ClienteDAO clienteDAO = new ClienteDAO(entityManager);		
		clienteDAO.cadastrar(cliente);

		Cliente clienteNovo = clienteDAO.buscaPorId(1l);
		

		System.out.println("aqui vamos buscar os produtos");
		ProdutoDAO produtoDAO  = new ProdutoDAO(entityManager);
		Produto produto = produtoDAO.buscarPorId(1l);
		
		Pedido pedido = new Pedido(clienteNovo);
		pedido.adicionarItem(new PedidoItem(10, pedido, produto));
		
		PedidoDAO pedidoDao = new PedidoDAO(entityManager);
		pedidoDao.cadastrar(pedido);
		entityManager.getTransaction().commit();
		
		
	}

}
