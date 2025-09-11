package com.gestaovendas.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Ordem - Representa uma ordem de compra
 */
public class Ordem extends UniversalObject {
    private String codigo;
    private String clienteId;
    private String clienteNome;
    private String vendedorId;
    private String vendedorNome;
    private List<ItemOrdem> itens;
    private double valorTotal;
    private StatusOrdem status;
    private TipoOrdem tipo;
    
    public enum StatusOrdem {
        PENDENTE, PROCESSANDO, PAGO, CANCELADO
    }
    
    public enum TipoOrdem {
        VENDA, COTACAO
    }
    
    public Ordem() {
        super();
        this.itens = new ArrayList<>();
        this.status = StatusOrdem.PENDENTE;
        this.tipo = TipoOrdem.VENDA;
        this.valorTotal = 0.0;
    }
    
    public Ordem(String clienteId, String clienteNome, String vendedorId, String vendedorNome) {
        this();
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
        this.vendedorId = vendedorId;
        this.vendedorNome = vendedorNome;
    }
    
    /**
     * Adiciona um item à ordem
     */
    public void adicionarItem(ItemOrdem item) {
        itens.add(item);
        calcularTotal();
    }
    
    /**
     * Remove um item da ordem
     */
    public boolean removerItem(String produtoId) {
        boolean removido = itens.removeIf(item -> item.getProdutoId().equals(produtoId));
        if (removido) {
            calcularTotal();
        }
        return removido;
    }
    
    /**
     * Calcula o valor total da ordem
     */
    public void calcularTotal() {
        valorTotal = itens.stream()
                         .mapToDouble(ItemOrdem::getSubtotal)
                         .sum();
    }
    
    /**
     * Verifica se a ordem tem itens
     */
    public boolean temItens() {
        return !itens.isEmpty();
    }
    
    /**
     * Obtém a quantidade total de itens
     */
    public int getQuantidadeTotalItens() {
        return itens.stream()
                   .mapToInt(ItemOrdem::getQuantidade)
                   .sum();
    }
    
    // Getters e Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }
    
    public String getClienteNome() { return clienteNome; }
    public void setClienteNome(String clienteNome) { this.clienteNome = clienteNome; }
    
    public String getVendedorId() { return vendedorId; }
    public void setVendedorId(String vendedorId) { this.vendedorId = vendedorId; }
    
    public String getVendedorNome() { return vendedorNome; }
    public void setVendedorNome(String vendedorNome) { this.vendedorNome = vendedorNome; }
    
    public List<ItemOrdem> getItens() { return new ArrayList<>(itens); }
    public void setItens(List<ItemOrdem> itens) { 
        this.itens = new ArrayList<>(itens);
        calcularTotal();
    }
    
    public double getValorTotal() { return valorTotal; }
    
    public StatusOrdem getStatus() { return status; }
    public void setStatus(StatusOrdem status) { this.status = status; }
    
    public TipoOrdem getTipo() { return tipo; }
    public void setTipo(TipoOrdem tipo) { this.tipo = tipo; }
    
    @Override
    public String toString() {
        return "Ordem{" +
                "codigo='" + codigo + '\'' +
                ", clienteNome='" + clienteNome + '\'' +
                ", vendedorNome='" + vendedorNome + '\'' +
                ", valorTotal=" + valorTotal +
                ", status=" + status +
                ", tipo=" + tipo +
                ", itens=" + itens.size() +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ordem ordem = (Ordem) obj;
        return codigo != null ? codigo.equals(ordem.codigo) : ordem.codigo == null;
    }
    
    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }
}

