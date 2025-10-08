/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas.caixa;

import Controller.CaixaService;
import Model.Pagamento;
import Model.Usuario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 *
 * @author massas
 */
public class TelaFechoCaixa extends javax.swing.JFrame {
    
    private Usuario usuarioLogado;
    private CaixaService caixaService;
    private DefaultTableModel tableModel;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    /**
     * Creates new form TelaFechoCaixa
     */
    public TelaFechoCaixa(Usuario usuario, CaixaService caixaService) {
        this.usuarioLogado = usuario;
        this.caixaService = caixaService;
        this.dataInicio = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        this.dataFim = LocalDateTime.now();
        initComponents();
        atualizarInformacoesUsuario();
        configurarTabela();
        gerarRelatorio();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setText("Sistema Online | Usuario:");

        jLabel4.setText("Data:  | Hora: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(106, 106, 106))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel2.setText("Fecho de Caixa");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Ordem", "Tipo", "Valor", "Data/Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel3.setText("Resumo do Dia");

        jLabel5.setText("Total Dinheiro:");

        jLabel6.setText("Total Cartão:");

        jLabel7.setText("Total Transferência:");

        jLabel8.setText("0.00 MT");

        jLabel9.setText("0.00 MT");

        jLabel10.setText("0.00 MT");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel11.setText("TOTAL GERAL: 0.00 MT");

        jButton1.setText("Atualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)))
                    .addComponent(jLabel11)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(20, 20, 20)
                        .addComponent(jButton2)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel11)
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        gerarRelatorio();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        TelaCaixaOpcoes telaCaixaOpcoes = new TelaCaixaOpcoes(usuarioLogado, caixaService, null);
        telaCaixaOpcoes.setVisible(true);
        this.dispose();
    }

    private void atualizarInformacoesUsuario() {
        jLabel1.setText("Sistema Online | Usuário: " + usuarioLogado.getNome() + " (" + usuarioLogado.getId() + ")");
    }

    private void configurarTabela() {
        String[] colunas = {"ID", "Ordem", "Tipo", "Valor (MT)", "Data/Hora"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(tableModel);
    }

    private void gerarRelatorio() {
        Map<String, Object> relatorio = caixaService.gerarRelatorioCaixa(dataInicio, dataFim);
        
        double totalDinheiro = (double) relatorio.get("totalDinheiro");
        double totalCartao = (double) relatorio.get("totalCartao");
        double totalTransferencia = (double) relatorio.get("totalTransferencia");
        double totalGeral = (double) relatorio.get("totalGeral");
        
        jLabel8.setText(String.format("%.2f MT", totalDinheiro));
        jLabel9.setText(String.format("%.2f MT", totalCartao));
        jLabel10.setText(String.format("%.2f MT", totalTransferencia));
        jLabel11.setText(String.format("TOTAL GERAL: %.2f MT", totalGeral));
        
        @SuppressWarnings("unchecked")
        List<Pagamento> pagamentos = (List<Pagamento>) relatorio.get("pagamentos");
        
        tableModel.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        for (Pagamento pagamento : pagamentos) {
            Object[] row = {
                pagamento.getId(),
                pagamento.getOrdemCodigo(),
                pagamento.getTipoPagamento(),
                String.format("%.2f", pagamento.getValorTotal()),
                pagamento.getDataPagamento().format(formatter)
            };
            tableModel.addRow(row);
        }
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration
}
