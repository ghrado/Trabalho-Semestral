package gestao_vendas.src.main.java.com.gestaovendas.view;

import com.gestaovendas.controller.*;
import com.gestaovendas.model.Usuario;
import com.gestaovendas.view.LoginFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Tela Principal do Vendedor
 */
public class VendedorFrame extends JFrame {
    private Usuario usuarioLogado;
    private ControleGestorService controleGestorService;
    private VendasService vendasService;
    
    public VendedorFrame(Usuario usuario, ControleGestorService controleGestorService) {
        this.usuarioLogado = usuario;
        this.controleGestorService = controleGestorService;
        this.vendasService = new VendasService();
        
        initComponents();
        setupLayout();
        setupEvents();
    }
    
    private void initComponents() {
        setTitle("Sistema de Gestão de Vendas - Vendedor: " + usuarioLogado.getNome());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Barra de menu (apenas Vendas)
        JMenuBar menuBar = new JMenuBar();
        
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
        
        // Menu Sistema
        JMenu menuSistema = new JMenu("Sistema");
        JMenuItem itemAlterarSenha = new JMenuItem("Alterar Senha");
        JMenuItem itemSair = new JMenuItem("Sair");
        menuSistema.add(itemAlterarSenha);
        menuSistema.addSeparator();
        menuSistema.add(itemSair);
        
        menuBar.add(menuVendas);
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
        
        JLabel lblRole = new JLabel("Vendedor");
        lblRole.setFont(new Font("Arial", Font.PLAIN, 16));
        lblRole.setForeground(new Color(108, 117, 125));
        gbc.gridy = 1;
        welcomePanel.add(lblRole, gbc);
        
        JLabel lblInstructions = new JLabel("<html><center>Use o menu Vendas para gerenciar clientes e criar ordens.<br>Você receberá alertas quando produtos estiverem com stock baixo.</center></html>");
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
    
     public static void main(String[] args) {
        // TODO code application logic here
    }
}

