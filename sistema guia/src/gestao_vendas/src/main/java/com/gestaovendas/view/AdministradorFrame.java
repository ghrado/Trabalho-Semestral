package com.gestaovendas.view;

import com.gestaovendas.controller.*;
import com.gestaovendas.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tela Principal do Administrador
 */
public class AdministradorFrame extends JFrame {
    private Usuario usuarioLogado;
    private ControleGestorService controleGestorService;
    private VendasService vendasService;
    private StockService stockService;
    private CaixaService caixaService;
    private RelatoriosService relatoriosService;
    
    public AdministradorFrame(Usuario usuario, ControleGestorService controleGestorService) {
        this.usuarioLogado = usuario;
        this.controleGestorService = controleGestorService;
        this.vendasService = new VendasService();
        this.stockService = new StockService();
        this.caixaService = new CaixaService(vendasService);
        this.relatoriosService = new RelatoriosService(vendasService, stockService, caixaService, controleGestorService);
        
        initComponents();
        setupLayout();
        setupEvents();
    }
    
    private void initComponents() {
        setTitle("Sistema de Gestão de Vendas - Administrador: " + usuarioLogado.getNome());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Barra de menu
        JMenuBar menuBar = new JMenuBar();
        
        // Menu Controle de Gestor
        JMenu menuControleGestor = new JMenu("Controle de Gestor");
        JMenuItem itemCadastrarUsuario = new JMenuItem("Cadastrar Usuário");
        JMenuItem itemGerenciarUsuarios = new JMenuItem("Gerenciar Usuários");
        JMenuItem itemDadosApagados = new JMenuItem("Dados Apagados");
        menuControleGestor.add(itemCadastrarUsuario);
        menuControleGestor.add(itemGerenciarUsuarios);
        menuControleGestor.add(itemDadosApagados);
        
        // Menu Vendas
        JMenu menuVendas = new JMenu("Vendas");
        JMenuItem itemCadastrarCliente = new JMenuItem("Cadastrar Cliente");
        JMenuItem itemGerenciarClientes = new JMenuItem("Gerenciar Clientes");
        JMenuItem itemCriarOrdem = new JMenuItem("Criar Ordem");
        JMenuItem itemGerenciarOrdens = new JMenuItem("Gerenciar Ordens");
        menuVendas.add(itemCadastrarCliente);
        menuVendas.add(itemGerenciarClientes);
        menuVendas.addSeparator();
        menuVendas.add(itemCriarOrdem);
        menuVendas.add(itemGerenciarOrdens);
        
        // Menu Stock
        JMenu menuStock = new JMenu("Stock");
        JMenuItem itemCadastrarProduto = new JMenuItem("Cadastrar Produto");
        JMenuItem itemGerenciarProdutos = new JMenuItem("Gerenciar Produtos");
        JMenuItem itemGestaoStock = new JMenuItem("Gestão de Stock");
        menuStock.add(itemCadastrarProduto);
        menuStock.add(itemGerenciarProdutos);
        menuStock.add(itemGestaoStock);
        
        // Menu Caixa
        JMenu menuCaixa = new JMenu("Caixa");
        JMenuItem itemLancamentoPagamentos = new JMenuItem("Lançamento de Pagamentos");
        JMenuItem itemOrdensPagemento = new JMenuItem("Ordens de Pagamento");
        JMenuItem itemFechoCaixa = new JMenuItem("Fecho de Caixa");
        menuCaixa.add(itemLancamentoPagamentos);
        menuCaixa.add(itemOrdensPagemento);
        menuCaixa.add(itemFechoCaixa);
        
        // Menu Relatórios
        JMenu menuRelatorios = new JMenu("Relatórios");
        JMenuItem itemRelatorioVendas = new JMenuItem("Relatório de Vendas");
        JMenuItem itemRelatorioClientes = new JMenuItem("Relatório de Clientes");
        JMenuItem itemRelatorioPagamentos = new JMenuItem("Relatório de Pagamentos");
        JMenuItem itemRelatorioConsolidado = new JMenuItem("Relatório Consolidado");
        menuRelatorios.add(itemRelatorioVendas);
        menuRelatorios.add(itemRelatorioClientes);
        menuRelatorios.add(itemRelatorioPagamentos);
        menuRelatorios.add(itemRelatorioConsolidado);
        
        // Menu Sistema
        JMenu menuSistema = new JMenu("Sistema");
        JMenuItem itemAlterarSenha = new JMenuItem("Alterar Senha");
        JMenuItem itemNotificacoes = new JMenuItem("Notificações");
        JMenuItem itemSair = new JMenuItem("Sair");
        menuSistema.add(itemAlterarSenha);
        menuSistema.add(itemNotificacoes);
        menuSistema.addSeparator();
        menuSistema.add(itemSair);
        
        menuBar.add(menuControleGestor);
        menuBar.add(menuVendas);
        menuBar.add(menuStock);
        menuBar.add(menuCaixa);
        menuBar.add(menuRelatorios);
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
        
        JLabel lblRole = new JLabel("Administrador do Sistema");
        lblRole.setFont(new Font("Arial", Font.PLAIN, 16));
        lblRole.setForeground(new Color(108, 117, 125));
        gbc.gridy = 1;
        welcomePanel.add(lblRole, gbc);
        
        JLabel lblInstructions = new JLabel("<html><center>Use o menu superior para navegar pelas funcionalidades do sistema.<br>Como administrador, você tem acesso completo a todas as funcionalidades.</center></html>");
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
        // Implementar eventos dos menus
        // Por brevidade, implementando apenas alguns eventos principais
        
        // Sair
        JMenuItem itemSair = getMenuItem("Sair");
        if (itemSair != null) {
            itemSair.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int opcao = JOptionPane.showConfirmDialog(AdministradorFrame.this,
                        "Deseja realmente sair do sistema?", "Confirmar Saída",
                        JOptionPane.YES_NO_OPTION);
                    if (opcao == JOptionPane.YES_OPTION) {
                        dispose();
                        new LoginFrame().setVisible(true);
                    }
                }
            });
        }
        
        // Alterar Senha
        JMenuItem itemAlterarSenha = getMenuItem("Alterar Senha");
        if (itemAlterarSenha != null) {
            itemAlterarSenha.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    alterarSenha();
                }
            });
        }
        
        // Notificações
        JMenuItem itemNotificacoes = getMenuItem("Notificações");
        if (itemNotificacoes != null) {
            itemNotificacoes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarNotificacoes();
                }
            });
        }
    }
    
    private JMenuItem getMenuItem(String text) {
        return findMenuItem(getJMenuBar(), text);
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
    
    private void alterarSenha() {
        JPasswordField senhaAtual = new JPasswordField();
        JPasswordField novaSenha = new JPasswordField();
        JPasswordField confirmaSenha = new JPasswordField();
        
        Object[] message = {
            "Senha Atual:", senhaAtual,
            "Nova Senha:", novaSenha,
            "Confirmar Nova Senha:", confirmaSenha
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Alterar Senha", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String senhaAtualStr = new String(senhaAtual.getPassword());
            String novaSenhaStr = new String(novaSenha.getPassword());
            String confirmaSenhaStr = new String(confirmaSenha.getPassword());
            
            if (usuarioLogado.autenticar(usuarioLogado.getUsuario(), senhaAtualStr)) {
                if (novaSenhaStr.equals(confirmaSenhaStr) && novaSenhaStr.length() >= 6) {
                    usuarioLogado.alterarSenha(novaSenhaStr);
                    JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Nova senha inválida ou não confere!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Senha atual incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void mostrarNotificacoes() {
        if (controleGestorService.temNotificacoes()) {
            StringBuilder sb = new StringBuilder("Notificações:\\n\\n");
            while (controleGestorService.temNotificacoes()) {
                sb.append("• ").append(controleGestorService.obterProximaNotificacao()).append("\\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Notificações", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Não há notificações.", "Notificações", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

