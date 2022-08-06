package main.java.br.com.alura.loja.modelo.vo;

import java.time.LocalDate;

public class RelatorioVendasVo {

	private String nomeProduto;
	private long quantidadeVendida;
	private LocalDate dataUltimaVenda;
	
	public RelatorioVendasVo(String nomeProduto, long quantidadeVendida, LocalDate dataUltimaVenda) {
		super();
		this.nomeProduto = nomeProduto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataUltimaVenda = dataUltimaVenda;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public long getQuantidadeVendida() {
		return quantidadeVendida;
	}
	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}

	@Override
	public String toString() {
		return "RelatorioVendasVo [nomeProduto=" + nomeProduto + ", quantidadeVendida=" + quantidadeVendida
				+ ", dataUltimaVenda=" + dataUltimaVenda + "]";
	}


}

