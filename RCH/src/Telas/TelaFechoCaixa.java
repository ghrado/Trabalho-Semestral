package Telas;

import Controller.CaixaService;
import Model.Usuario;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TelaFechoCaixa extends JFrame {
    private Usuario usuarioLogado;
    private CaixaService caixaService;
    
    private JTextArea txtResumo;
    private JButton btnGerarFecho;
    private JButton btnImprimir;
    private JButton btnVoltar;
    
    public TelaFechoCaixa(Usuario usuario, CaixaService caixaService) {
        this.usuarioLogado = usuario;
        this.caixaService = caixaService;
        
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Sistema de Gestão de Vendas - Fecho de Caixa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 600));
        setLayout(new BorderLayout());
        
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitulo = new JLabel("Fecho de Caixa - " + LocalDate.now());
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelTop.add(lblTitulo);
        
        txtResumo = new JTextArea();
        txtResumo.setEditable(false);
        txtResumo.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtResumo);
        
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnGerarFecho = new JButton("Gerar Fecho");
        btnGerarFecho.addActionListener(evt -> gerarFecho());
        
        btnImprimir = new JButton("Imprimir");
        btnImprimir.setEnabled(false);
        btnImprimir.addActionListener(evt -> imprimir());
        
        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(evt -> voltar());
        
        panelBotoes.add(btnGerarFecho);
        panelBotoes.add(btnImprimir);
        panelBotoes.add(btnVoltar);
        
        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void gerarFecho() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Deseja gerar o fecho de caixa?\nEsta operação irá zerar as entradas do dia.", 
            "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String resumo = caixaService.gerarFechoCaixa(LocalDate.now(), usuarioLogado);
            txtResumo.setText(resumo);
            btnImprimir.setEnabled(true);
            
            JOptionPane.showMessageDialog(this, 
                "Fecho de caixa gerado com sucesso!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void imprimir() {
        JOptionPane.showMessageDialog(this, 
            "Funcionalidade de impressão será implementada!", 
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void voltar() {
        dispose();
    }
}
