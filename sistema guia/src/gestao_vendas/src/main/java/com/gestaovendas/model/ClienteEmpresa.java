package com.gestaovendas.model;

/**
 * Classe ClienteEmpresa - Herda de Cliente
 * Para clientes empresariais
 */
public class ClienteEmpresa extends Cliente {
    private String nuit; // Número Único de Identificação Tributária
    
    public ClienteEmpresa() {
        super();
        this.tipoCliente = TipoCliente.EMPRESA;
    }
    
    public ClienteEmpresa(String nome, String nuit, String telefone, 
                         String email, String endereco) {
        super(nome, telefone, email, endereco, TipoCliente.EMPRESA);
        this.nuit = nuit;
    }
    
    // Getters e Setters
    public String getNuit() {
        return nuit;
    }
    
    public void setNuit(String nuit) {
        this.nuit = nuit;
    }
    
    @Override
    public String toString() {
        return "ClienteEmpresa{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", nuit='" + nuit + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}

