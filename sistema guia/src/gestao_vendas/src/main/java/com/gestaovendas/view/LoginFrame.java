package com.gestaovendas.view;

import gestao_vendas.src.main.java.com.gestaovendas.view.VendedorFrame;
import com.gestaovendas.controller.ControleGestorService;
import com.gestaovendas.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tela de Login do Sistema
 */
public class LoginFrame extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JButton btnSair;
    private ControleGestorService controleGestorService;
    
    public LoginFrame() {
        this.controleGestorService = new ControleGestorService();
        initComponents();
        setupLayout();
        setupEvents();
    }
    
    private void initComponents() {
        setTitle("Sistema de Gestão de Vendas - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Componentes
        txtUsuario = new JTextField(20);
        txtSenha = new JPasswordField(20);
        btnLogin = new JButton("Entrar");
        btnSair = new JButton("Sair");
        
        // Configurações visuais
        btnLogin.setBackground(new Color(0, 123, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnSair.setBackground(new Color(220, 53, 69));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFont(new Font("Arial", Font.BOLD, 12));
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Painel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(248, 249, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Título
        JLabel lblTitulo = new JLabel("Sistema de Gestão de Vendas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(52, 58, 64));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(lblTitulo, gbc);
        
        // Subtítulo
        JLabel lblSubtitulo = new JLabel("Faça login para continuar");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubtitulo.setForeground(new Color(108, 117, 125));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        mainPanel.add(lblSubtitulo, gbc);
        
        // Espaçamento
        gbc.gridy = 2;
        mainPanel.add(Box.createVerticalStrut(20), gbc);
        
        // Campo usuário
        JLabel lblUsuario = new JLabel("Usuário ou ID:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblUsuario, gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtUsuario, gbc);
        
        // Campo senha
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblSenha, gbc);
        
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtSenha, gbc);
        
        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(248, 249, 250));
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnSair);
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Rodapé
        JLabel lblRodape = new JLabel("© 2024 Sistema de Gestão de Vendas", SwingConstants.CENTER);
        lblRodape.setFont(new Font("Arial", Font.PLAIN, 10));
        lblRodape.setForeground(new Color(108, 117, 125));
        lblRodape.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblRodape, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void setupEvents() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });
        
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Enter no campo senha faz login
        txtSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });
    }
    
    private void realizarLogin() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword());
        
        // Validações básicas
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe o usuário ou ID.", 
                                        "Erro", JOptionPane.ERROR_MESSAGE);
            txtUsuario.requestFocus();
            return;
        }
        
        if (senha.length() < 6) {
            JOptionPane.showMessageDialog(this, "A senha deve ter no mínimo 6 dígitos.", 
                                        "Erro", JOptionPane.ERROR_MESSAGE);
            txtSenha.requestFocus();
            return;
        }
        
        // Tentar autenticar
        Usuario usuarioAutenticado = controleGestorService.autenticar(usuario, senha);
        
        if (usuarioAutenticado != null) {
            // Login bem-sucedido
            abrirTelaPrincipal(usuarioAutenticado);
        } else {
            // Login falhou
            JOptionPane.showMessageDialog(this, 
                "Usuário ou senha incorretos. Após 3 tentativas a conta será bloqueada.", 
                "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
            txtSenha.setText("");
            txtSenha.requestFocus();
        }
    }
    
    private void abrirTelaPrincipal(Usuario usuario) {
        // Fechar tela de login
        this.dispose();
        
        // Abrir tela apropriada baseada no tipo de usuário
        switch (usuario.getTipoUsuario()) {
            case ADMINISTRADOR:
                new AdministradorFrame(usuario, controleGestorService).setVisible(true);
                break;
            case GERENTE:
                new GerenteFrame(usuario, controleGestorService).setVisible(true);
                break;
            case VENDEDOR:
                new VendedorFrame(usuario, controleGestorService).setVisible(true);
                break;
            case CAIXA:
                new CaixaFrame(usuario, controleGestorService).setVisible(true);
                break;
        }
    }
    
    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}

