package main.java.br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import main.java.br.com.alura.loja.modelo.Pedido;
import main.java.br.com.alura.loja.modelo.vo.RelatorioVendasVo;

public class PedidoDAO {

	private EntityManager entityManager;

	public PedidoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Pedido pedido) {
		entityManager.persist(pedido);
	}

	public BigDecimal getValorTotal() {
		String jpql  = "SELECT SUM(p.valorTotal) FROM Pedido p ";
		return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	//Vo value object -- normalmente parar classes que são apenas para representar registros 
	public List<RelatorioVendasVo> relatorioVendas(){
		String jpql = "SELECT new main.java.br.com.alura.loja.modelo.vo.RelatorioVendasVo("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.dataInclusao) ) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.pedidoItens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		return entityManager.createQuery(jpql, RelatorioVendasVo.class)
				.getResultList();
	}
	
	public Pedido buscarPorIdComCliente(long id) {
		return entityManager.createQuery("select p from Pedido p join fetch p.cliente where p.id = :id",Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	
}
