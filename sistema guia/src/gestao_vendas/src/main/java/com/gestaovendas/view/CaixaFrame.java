package com.gestaovendas.view;

import com.gestaovendas.controller.*;
import com.gestaovendas.model.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * Tela Principal do Caixa
 */
public class CaixaFrame extends JFrame {
    private Usuario usuarioLogado;
    private ControleGestorService controleGestorService;
    private VendasService vendasService;
    private CaixaService caixaService;
    
    public CaixaFrame(Usuario usuario, ControleGestorService controleGestorService) {
        this.usuarioLogado = usuario;
        this.controleGestorService = controleGestorService;
        this.vendasService = new VendasService();
        this.caixaService = new CaixaService(vendasService);
        
        initComponents();
        setupLayout();
        setupEvents();
    }
    
    private void initComponents() {
        setTitle("Sistema de Gestão de Vendas - Caixa: " + usuarioLogado.getNome());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Barra de menu (apenas Caixa)
        JMenuBar menuBar = new JMenuBar();
        
        // Menu Caixa
        JMenu menuCaixa = new JMenu("Caixa");
        JMenuItem itemLancamentoPagamentos = new JMenuItem("Lançamento de Pagamentos");
        JMenuItem itemOrdensPagemento = new JMenuItem("Ordens de Pagamento");
        menuCaixa.add(itemLancamentoPagamentos);
        menuCaixa.add(itemOrdensPagemento);
        
        // Menu Sistema
        JMenu menuSistema = new JMenu("Sistema");
        JMenuItem itemAlterarSenha = new JMenuItem("Alterar Senha");
        JMenuItem itemSair = new JMenuItem("Sair");
        menuSistema.add(itemAlterarSenha);
        menuSistema.addSeparator();
        menuSistema.add(itemSair);
        
        menuBar.add(menuCaixa);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(menuSistema);
        
        setJMenuBar(menuBar);
        
        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Painel de boas-vindas
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(new Color(248, 249, 250));
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblWelcome = new JLabel("Bem-vindo, " + usuarioLogado.getNome() + "!");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 28));
        lblWelcome.setForeground(new Color(52, 58, 64));
        gbc.gridx = 0; gbc.gridy = 0;
        welcomePanel.add(lblWelcome, gbc);
        
        JLabel lblRole = new JLabel("Operador de Caixa");
        lblRole.setFont(new Font("Arial", Font.PLAIN, 16));
        lblRole.setForeground(new Color(108, 117, 125));
        gbc.gridy = 1;
        welcomePanel.add(lblRole, gbc);
        
        JLabel lblInstructions = new JLabel("<html><center>Use o menu Caixa para processar pagamentos e gerenciar ordens.<br>Você pode processar pagamentos via Multi Redes, E-Mola, M-Pesa, Dinheiro, Cheque e Nota de Crédito.</center></html>");
        lblInstructions.setFont(new Font("Arial", Font.PLAIN, 14));
        lblInstructions.setForeground(new Color(108, 117, 125));
        gbc.gridy = 2;
        welcomePanel.add(lblInstructions, gbc);
        
        mainPanel.add(welcomePanel, BorderLayout.CENTER);
        
        // Barra de status
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        JLabel lblStatus = new JLabel("Sistema Online | Usuário: " + usuarioLogado.getId() + " | " + 
                                     java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        statusPanel.add(lblStatus);
        
        add(mainPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
    }
    
    private void setupEvents() {
        // Implementar eventos básicos
        JMenuItem itemSair = findMenuItem(getJMenuBar(), "Sair");
        if (itemSair != null) {
            itemSair.addActionListener(e -> {
                int opcao = JOptionPane.showConfirmDialog(this,
                    "Deseja realmente sair do sistema?", "Confirmar Saída",
                    JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION) {
                    dispose();
                    new LoginFrame().setVisible(true);
                }
            });
        }
    }
    
    private JMenuItem findMenuItem(MenuElement element, String text) {
        if (element instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) element;
            if (text.equals(item.getText())) {
                return item;
            }
        }
        
        MenuElement[] subElements = element.getSubElements();
        for (MenuElement subElement : subElements) {
            JMenuItem found = findMenuItem(subElement, text);
            if (found != null) {
                return found;
            }
        }
        
        return null;
    }
}

