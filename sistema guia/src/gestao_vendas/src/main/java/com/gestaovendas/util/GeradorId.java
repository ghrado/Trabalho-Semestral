package com.gestaovendas.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Classe utilitária para geração de IDs automáticos
 * Utiliza Stack para armazenar IDs reutilizáveis
 */
public class GeradorId {
    private static GeradorId instance;
    private Map<String, Integer> contadores;
    private Map<String, Stack<String>> idsReutilizaveis;
    
    private GeradorId() {
        contadores = new HashMap<>();
        idsReutilizaveis = new HashMap<>();
        inicializarContadores();
    }
    
    public static synchronized GeradorId getInstance() {
        if (instance == null) {
            instance = new GeradorId();
        }
        return instance;
    }
    
    private void inicializarContadores() {
        contadores.put("CS", 1); // Cliente Singular
        contadores.put("CE", 1); // Cliente Empresa
        contadores.put("GE", 1); // Gerente
        contadores.put("CX", 1); // Caixa
        contadores.put("VN", 1); // Vendedor
        contadores.put("ORDEM", 1); // Ordem
        
        // Inicializar stacks para IDs reutilizáveis
        idsReutilizaveis.put("CS", new Stack<>());
        idsReutilizaveis.put("CE", new Stack<>());
        idsReutilizaveis.put("GE", new Stack<>());
        idsReutilizaveis.put("CX", new Stack<>());
        idsReutilizaveis.put("VN", new Stack<>());
        idsReutilizaveis.put("ORDEM", new Stack<>());
    }
    
    /**
     * Gera um novo ID para o tipo especificado
     */
    public synchronized String gerarId(String tipo) {
        Stack<String> stackReutilizaveis = idsReutilizaveis.get(tipo);
        
        // Se há IDs reutilizáveis, usar o último da pilha
        if (stackReutilizaveis != null && !stackReutilizaveis.isEmpty()) {
            return stackReutilizaveis.pop();
        }
        
        // Caso contrário, gerar novo ID
        Integer contador = contadores.get(tipo);
        if (contador == null) {
            contador = 1;
        }
        
        String novoId;
        if (tipo.equals("ORDEM")) {
            novoId = String.format("%04d", contador);
        } else {
            novoId = tipo + String.format("%04d", contador);
        }
        
        contadores.put(tipo, contador + 1);
        return novoId;
    }
    
    /**
     * Adiciona um ID para reutilização (quando um objeto é excluído)
     */
    public synchronized void adicionarIdParaReutilizacao(String tipo, String id) {
        Stack<String> stackReutilizaveis = idsReutilizaveis.get(tipo);
        if (stackReutilizaveis != null && !stackReutilizaveis.contains(id)) {
            stackReutilizaveis.push(id);
        }
    }
    
    /**
     * Remove um ID da lista de reutilizáveis (quando um objeto é reintegrado)
     */
    public synchronized void removerIdDeReutilizacao(String tipo, String id) {
        Stack<String> stackReutilizaveis = idsReutilizaveis.get(tipo);
        if (stackReutilizaveis != null) {
            stackReutilizaveis.remove(id);
        }
    }
    
    /**
     * Método recursivo para validar formato de ID
     */
    public boolean validarFormatoId(String id, String tipo) {
        return validarFormatoIdRecursivo(id, tipo, 0);
    }
    
    private boolean validarFormatoIdRecursivo(String id, String tipo, int posicao) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        
        if (tipo.equals("ORDEM")) {
            // Para ordens, deve ter exatamente 4 dígitos
            if (id.length() != 4) return false;
            if (posicao >= id.length()) return true;
            
            char c = id.charAt(posicao);
            if (!Character.isDigit(c)) return false;
            
            return validarFormatoIdRecursivo(id, tipo, posicao + 1);
        } else {
            // Para outros tipos, deve começar com o prefixo + 4 dígitos
            String prefixoEsperado = tipo;
            if (id.length() != prefixoEsperado.length() + 4) return false;
            
            if (posicao < prefixoEsperado.length()) {
                if (id.charAt(posicao) != prefixoEsperado.charAt(posicao)) return false;
                return validarFormatoIdRecursivo(id, tipo, posicao + 1);
            } else if (posicao < id.length()) {
                char c = id.charAt(posicao);
                if (!Character.isDigit(c)) return false;
                return validarFormatoIdRecursivo(id, tipo, posicao + 1);
            }
            
            return true;
        }
    }
}

