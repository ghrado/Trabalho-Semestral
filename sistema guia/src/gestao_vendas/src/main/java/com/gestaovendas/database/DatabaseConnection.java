package com.gestaovendas.database;

import java.sql.*;
import java.util.Properties;

/**
 * Classe para gerenciar conexões com o banco de dados MySQL
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/gestao_vendas";
    private static final String USERNAME = "root";
    private static final String PASSWORD = ""; // Configurar conforme necessário
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private static DatabaseConnection instance;
    private Connection connection;
    
    private DatabaseConnection() {
        try {
            Class.forName(DRIVER);
            Properties props = new Properties();
            props.setProperty("user", USERNAME);
            props.setProperty("password", PASSWORD);
            props.setProperty("useSSL", "false");
            props.setProperty("allowPublicKeyRetrieval", "true");
            props.setProperty("serverTimezone", "UTC");
            
            this.connection = DriverManager.getConnection(URL, props);
            System.out.println("Conexão com banco de dados estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
    
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                instance = new DatabaseConnection();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar conexão: " + e.getMessage());
        }
        return connection;
    }
    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão com banco de dados fechada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
    
    /**
     * Executa um script SQL para criar as tabelas do sistema
     */
    public boolean criarTabelas() {
        String[] scripts = {
            // Tabela de usuários
            """
            CREATE TABLE IF NOT EXISTS usuarios (
                id VARCHAR(10) PRIMARY KEY,
                nome VARCHAR(100) NOT NULL,
                endereco VARCHAR(200),
                bilhete_identidade VARCHAR(20),
                telefone VARCHAR(20),
                email VARCHAR(100),
                usuario VARCHAR(50) UNIQUE,
                senha VARCHAR(100),
                tipo_usuario ENUM('ADMINISTRADOR', 'GERENTE', 'VENDEDOR', 'CAIXA'),
                conta_bloqueada BOOLEAN DEFAULT FALSE,
                tentativas_login INT DEFAULT 0,
                ativo BOOLEAN DEFAULT TRUE,
                data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
            )
            """,
            
            // Tabela de clientes
            """
            CREATE TABLE IF NOT EXISTS clientes (
                id VARCHAR(10) PRIMARY KEY,
                nome VARCHAR(100) NOT NULL,
                telefone VARCHAR(20),
                email VARCHAR(100),
                endereco VARCHAR(200),
                tipo_cliente ENUM('SINGULAR', 'EMPRESA'),
                bilhete_identidade VARCHAR(20),
                nuit VARCHAR(20),
                ativo BOOLEAN DEFAULT TRUE,
                data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
            )
            """,
            
            // Tabela de produtos
            """
            CREATE TABLE IF NOT EXISTS produtos (
                id VARCHAR(36) PRIMARY KEY,
                codigo VARCHAR(50) UNIQUE NOT NULL,
                descricao VARCHAR(200) NOT NULL,
                preco DECIMAL(10,2) NOT NULL,
                quantidade_stock INT DEFAULT 0,
                stock_minimo INT DEFAULT 10,
                stock_maximo INT DEFAULT 1000,
                categoria VARCHAR(50),
                unidade_medida VARCHAR(20),
                ativo BOOLEAN DEFAULT TRUE,
                data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
            )
            """,
            
            // Tabela de ordens
            """
            CREATE TABLE IF NOT EXISTS ordens (
                id VARCHAR(10) PRIMARY KEY,
                codigo VARCHAR(10) UNIQUE NOT NULL,
                cliente_id VARCHAR(10),
                cliente_nome VARCHAR(100),
                vendedor_id VARCHAR(10),
                vendedor_nome VARCHAR(100),
                valor_total DECIMAL(10,2) DEFAULT 0,
                status ENUM('PENDENTE', 'PROCESSANDO', 'PAGO', 'CANCELADO') DEFAULT 'PENDENTE',
                tipo ENUM('VENDA', 'COTACAO') DEFAULT 'VENDA',
                ativo BOOLEAN DEFAULT TRUE,
                data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                FOREIGN KEY (cliente_id) REFERENCES clientes(id),
                FOREIGN KEY (vendedor_id) REFERENCES usuarios(id)
            )
            """,
            
            // Tabela de itens da ordem
            """
            CREATE TABLE IF NOT EXISTS itens_ordem (
                id VARCHAR(36) PRIMARY KEY,
                ordem_id VARCHAR(10),
                produto_id VARCHAR(36),
                produto_codigo VARCHAR(50),
                produto_descricao VARCHAR(200),
                quantidade INT NOT NULL,
                preco_unitario DECIMAL(10,2) NOT NULL,
                subtotal DECIMAL(10,2) NOT NULL,
                ativo BOOLEAN DEFAULT TRUE,
                data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (ordem_id) REFERENCES ordens(id),
                FOREIGN KEY (produto_id) REFERENCES produtos(id)
            )
            """,
            
            // Tabela de faturas
            """
            CREATE TABLE IF NOT EXISTS faturas (
                id VARCHAR(36) PRIMARY KEY,
                codigo VARCHAR(12) UNIQUE NOT NULL,
                ordem_id VARCHAR(10),
                vendedor_id VARCHAR(10),
                vendedor_nome VARCHAR(100),
                caixa_id VARCHAR(10),
                caixa_nome VARCHAR(100),
                cliente_id VARCHAR(10),
                cliente_nome VARCHAR(100),
                valor_total DECIMAL(10,2) NOT NULL,
                tipo_pagamento ENUM('MULTI_REDES', 'EMOLA', 'MPESA', 'DINHEIRO', 'CHEQUE', 'NOTA_CREDITO'),
                data_processamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                ativo BOOLEAN DEFAULT TRUE,
                data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (ordem_id) REFERENCES ordens(id),
                FOREIGN KEY (vendedor_id) REFERENCES usuarios(id),
                FOREIGN KEY (caixa_id) REFERENCES usuarios(id),
                FOREIGN KEY (cliente_id) REFERENCES clientes(id)
            )
            """
        };
        
        try {
            Connection conn = getConnection();
            if (conn != null) {
                for (String script : scripts) {
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute(script);
                    }
                }
                System.out.println("Tabelas criadas com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
        return false;
    }
    
    /**
     * Insere o usuário administrador padrão
     */
    public boolean inserirAdministradorPadrao() {
        String sql = """
            INSERT IGNORE INTO usuarios (id, nome, endereco, bilhete_identidade, telefone, email, 
                                       usuario, senha, tipo_usuario, ativo) 
            VALUES ('Admin', 'Administrador', 'Sistema', '000000000', 'admin@sistema.com', 
                   'admin@sistema.com', 'admin', '000000', 'ADMINISTRADOR', TRUE)
        """;
        
        try {
            Connection conn = getConnection();
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    int result = stmt.executeUpdate();
                    if (result > 0) {
                        System.out.println("Administrador padrão criado com sucesso!");
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir administrador padrão: " + e.getMessage());
        }
        return false;
    }
    
    /**
     * Inicializa o banco de dados
     */
    public boolean inicializarBancoDados() {
        return criarTabelas() && inserirAdministradorPadrao();
    }
}

