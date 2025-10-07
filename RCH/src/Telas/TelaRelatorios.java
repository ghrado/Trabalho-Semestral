package Telas;

import Controller.RelatoriosService;
import Model.Usuario;
import javax.swing.*;
import java.awt.*;

public class TelaRelatorios extends JFrame {
    private Usuario usuarioLogado;
    private RelatoriosService relatoriosService;
    
    public TelaRelatorios(Usuario usuario, RelatoriosService relatoriosService) {
        this.usuarioLogado = usuario;
        this.relatoriosService = relatoriosService;
        
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Sistema de Gestão de Vendas - Relatórios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitulo = new JLabel("Relatórios do Sistema");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelTop.add(lblTitulo);
        
        JPanel panelCenter = new JPanel(new GridLayout(4, 2, 15, 15));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JButton btnVendasDiarias = new JButton("Vendas Diárias");
        btnVendasDiarias.addActionListener(evt -> gerarRelatorioVendasDiarias());
        
        JButton btnVendasSemanais = new JButton("Vendas Semanais");
        btnVendasSemanais.addActionListener(evt -> gerarRelatorioVendasSemanais());
        
        JButton btnVendasMensais = new JButton("Vendas Mensais");
        btnVendasMensais.addActionListener(evt -> gerarRelatorioVendasMensais());
        
        JButton btnClientesMaisCompras = new JButton("Clientes com Mais Compras");
        btnClientesMaisCompras.addActionListener(evt -> gerarRelatorioClientesMaisCompras());
        
        JButton btnVendedoresMaisVendas = new JButton("Vendedores com Mais Vendas");
        btnVendedoresMaisVendas.addActionListener(evt -> gerarRelatorioVendedoresMaisVendas());
        
        JButton btnPagamentosPorForma = new JButton("Pagamentos por Forma");
        btnPagamentosPorForma.addActionListener(evt -> gerarRelatorioPagamentosPorForma());
        
        JButton btnProdutosMaisVendidos = new JButton("Produtos Mais Vendidos");
        btnProdutosMaisVendidos.addActionListener(evt -> gerarRelatorioProdutosMaisVendidos());
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(evt -> voltar());
        
        panelCenter.add(btnVendasDiarias);
        panelCenter.add(btnVendasSemanais);
        panelCenter.add(btnVendasMensais);
        panelCenter.add(btnClientesMaisCompras);
        panelCenter.add(btnVendedoresMaisVendas);
        panelCenter.add(btnPagamentosPorForma);
        panelCenter.add(btnProdutosMaisVendidos);
        panelCenter.add(btnVoltar);
        
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void gerarRelatorioVendasDiarias() {
        String relatorio = relatoriosService.gerarRelatorioVendasDiarias();
        mostrarRelatorio("Vendas Diárias", relatorio);
    }
    
    private void gerarRelatorioVendasSemanais() {
        String relatorio = relatoriosService.gerarRelatorioVendasSemanais();
        mostrarRelatorio("Vendas Semanais", relatorio);
    }
    
    private void gerarRelatorioVendasMensais() {
        String relatorio = relatoriosService.gerarRelatorioVendasMensais();
        mostrarRelatorio("Vendas Mensais", relatorio);
    }
    
    private void gerarRelatorioClientesMaisCompras() {
        String relatorio = relatoriosService.gerarRelatorioClientesMaisCompras();
        mostrarRelatorio("Clientes com Mais Compras", relatorio);
    }
    
    private void gerarRelatorioVendedoresMaisVendas() {
        String relatorio = relatoriosService.gerarRelatorioVendedoresMaisVendas();
        mostrarRelatorio("Vendedores com Mais Vendas", relatorio);
    }
    
    private void gerarRelatorioPagamentosPorForma() {
        String relatorio = relatoriosService.gerarRelatorioPagamentosPorForma();
        mostrarRelatorio("Pagamentos por Forma", relatorio);
    }
    
    private void gerarRelatorioProdutosMaisVendidos() {
        String relatorio = relatoriosService.gerarRelatorioProdutosMaisVendidos();
        mostrarRelatorio("Produtos Mais Vendidos", relatorio);
    }
    
    private void mostrarRelatorio(String titulo, String conteudo) {
        JDialog dialog = new JDialog(this, titulo, true);
        dialog.setPreferredSize(new Dimension(700, 500));
        dialog.setLayout(new BorderLayout());
        
        JTextArea textArea = new JTextArea(conteudo);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnImprimir = new JButton("Imprimir PDF");
        btnImprimir.addActionListener(evt -> {
            JOptionPane.showMessageDialog(dialog, "Funcionalidade de impressão será implementada!", "Info", JOptionPane.INFORMATION_MESSAGE);
        });
        
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(evt -> dialog.dispose());
        
        panelBotoes.add(btnImprimir);
        panelBotoes.add(btnFechar);
        
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(panelBotoes, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void voltar() {
        dispose();
    }
}
