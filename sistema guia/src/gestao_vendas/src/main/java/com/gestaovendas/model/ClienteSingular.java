package com.gestaovendas.model;

/**
 * Classe ClienteSingular - Herda de Cliente
 * Para clientes individuais
 */
public class ClienteSingular extends Cliente {
    private String bilheteIdentidade;
    
    public ClienteSingular() {
        super();
        this.tipoCliente = TipoCliente.SINGULAR;
    }
    
    public ClienteSingular(String nome, String bilheteIdentidade, String telefone, 
                          String email, String endereco) {
        super(nome, telefone, email, endereco, TipoCliente.SINGULAR);
        this.bilheteIdentidade = bilheteIdentidade;
    }
    
    // Getters e Setters
    public String getBilheteIdentidade() {
        return bilheteIdentidade;
    }
    
    public void setBilheteIdentidade(String bilheteIdentidade) {
        this.bilheteIdentidade = bilheteIdentidade;
    }
    
    @Override
    public String toString() {
        return "ClienteSingular{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", bilheteIdentidade='" + bilheteIdentidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}

