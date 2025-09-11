package com.gestaovendas.controller;

import com.gestaovendas.model.*;
import com.gestaovendas.model.interfaces.Gerenciavel;
import com.gestaovendas.util.GeradorId;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe VendasService - Gerencia vendas, clientes e ordens
 */
public class VendasService implements Gerenciavel<Cliente> {
    private Map<String, Cliente> clientes;
    private Map<String, Ordem> ordens;
    private Vector<Cliente> clientesVector; // Usando Vector conforme solicitado
    private Vector<Ordem> ordensVector;
    private GeradorId geradorId;
    
    public VendasService() {
        this.clientes = new HashMap<>();
        this.ordens = new HashMap<>();
        this.clientesVector = new Vector<>();
        this.ordensVector = new Vector<>();
        this.geradorId = GeradorId.getInstance();
    }
    
    // Implementação da interface Gerenciavel para Clientes
    @Override
    public boolean criar(Cliente cliente) {
        if (cliente == null) return false;
        
        String tipo = cliente.getTipoCliente() == Cliente.TipoCliente.SINGULAR ? "CS" : "CE";
        String id = geradorId.gerarId(tipo);
        cliente.setId(id);
        
        clientes.put(id, cliente);
        clientesVector.add(cliente);
        return true;
    }
    
    @Override
    public Cliente buscar(String id) {
        return clientes.get(id);
    }
    
    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientesVector);
    }
    
    @Override
    public boolean atualizar(Cliente cliente) {
        if (cliente == null || cliente.getId() == null) return false;
        
        Cliente existente = clientes.get(cliente.getId());
        if (existente != null) {
            clientes.put(cliente.getId(), cliente);
            // Atualizar no Vector
            int index = clientesVector.indexOf(existente);
            if (index >= 0) {
                clientesVector.set(index, cliente);
            }
            return true;
        }
        return false;
    }
    
    @Override
    public boolean excluir(String id) {
        Cliente cliente = clientes.get(id);
        if (cliente != null) {
            cliente.setAtivo(false);
            clientesVector.remove(cliente);
            
            // Adicionar ID para reutilização
            String tipo = cliente.getTipoCliente() == Cliente.TipoCliente.SINGULAR ? "CS" : "CE";
            geradorId.adicionarIdParaReutilizacao(tipo, id);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean reintegrar(String id) {
        Cliente cliente = clientes.get(id);
        if (cliente != null && !cliente.isAtivo()) {
            cliente.setAtivo(true);
            clientesVector.add(cliente);
            
            // Remover ID da reutilização
            String tipo = cliente.getTipoCliente() == Cliente.TipoCliente.SINGULAR ? "CS" : "CE";
            geradorId.removerIdDeReutilizacao(tipo, id);
            return true;
        }
        return false;
    }
    
    // Métodos específicos para Clientes
    public List<Cliente> buscarClientesPorNome(String nome) {
        return clientesVector.stream()
                .filter(c -> c.isAtivo() && c.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public Cliente buscarClientePorNomeOuId(String termo) {
        // Buscar por ID primeiro
        Cliente cliente = buscar(termo);
        if (cliente != null && cliente.isAtivo()) {
            return cliente;
        }
        
        // Buscar por nome
        return clientesVector.stream()
                .filter(c -> c.isAtivo() && c.getNome().equalsIgnoreCase(termo))
                .findFirst()
                .orElse(null);
    }
    
    // Métodos para Ordens
    public boolean criarOrdem(Ordem ordem) {
        if (ordem == null) return false;
        
        String codigo = geradorId.gerarId("ORDEM");
        ordem.setCodigo(codigo);
        ordem.setId(codigo);
        
        ordens.put(codigo, ordem);
        ordensVector.add(ordem);
        return true;
    }
    
    public Ordem buscarOrdem(String codigo) {
        return ordens.get(codigo);
    }
    
    public List<Ordem> listarOrdens() {
        return new ArrayList<>(ordensVector);
    }
    
    public List<Ordem> buscarOrdensPorCodigo(String codigo) {
        return ordensVector.stream()
                .filter(o -> o.getCodigo().contains(codigo))
                .collect(Collectors.toList());
    }
    
    public boolean cancelarOrdem(String codigo) {
        Ordem ordem = ordens.get(codigo);
        if (ordem != null) {
            ordem.setStatus(Ordem.StatusOrdem.CANCELADO);
            ordem.setAtivo(false);
            return true;
        }
        return false;
    }
    
    public boolean gerarCotacao(String codigoOrdem) {
        Ordem ordem = ordens.get(codigoOrdem);
        if (ordem != null) {
            ordem.setTipo(Ordem.TipoOrdem.COTACAO);
            return true;
        }
        return false;
    }
    
    public boolean gerarOrdemPagamento(String codigoOrdem) {
        Ordem ordem = ordens.get(codigoOrdem);
        if (ordem != null && ordem.getTipo() == Ordem.TipoOrdem.VENDA) {
            ordem.setStatus(Ordem.StatusOrdem.PROCESSANDO);
            return true;
        }
        return false;
    }
    
    // Métodos para obter dados excluídos
    public List<Cliente> getClientesExcluidos() {
        return clientes.values().stream()
                .filter(c -> !c.isAtivo())
                .collect(Collectors.toList());
    }
    
    public List<Ordem> getOrdensExcluidas() {
        return ordens.values().stream()
                .filter(o -> !o.isAtivo())
                .collect(Collectors.toList());
    }
}

