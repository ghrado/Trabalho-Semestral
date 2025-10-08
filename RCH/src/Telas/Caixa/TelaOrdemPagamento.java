/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas.caixa;

import Controller.CaixaService;
import Controller.VendasService;
import Model.Ordem;
import Model.Usuario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author massas
 */
public class TelaOrdemPagamento extends javax.swing.JFrame {
    
    private Usuario usuarioLogado;
    private CaixaService caixaService;
    private VendasService vendasService;
    private DefaultTableModel tableModel;

    /**
     * Creates new form TelaOrdemPagamento
     */
    public TelaOrdemPagamento(Usuario usuario, CaixaService caixaService, VendasService vendasService) {
        this.usuarioLogado = usuario;
        this.caixaService = caixaService;
        this.vendasService = vendasService;
        initComponents();
        atualizarInformacoesUsuario();
        configurarTabela();
        carregarOrdensNaoProcessadas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Código", "Cliente", "Data", "Total", "Status"
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

        jButton1.setText("Processar Pagamento");
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

        jLabel2.setText("Buscar Ordem:");

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        processarPagamento();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        TelaCaixaOpcoes telaCaixaOpcoes = new TelaCaixaOpcoes(usuarioLogado, caixaService, vendasService);
        telaCaixaOpcoes.setVisible(true);
        this.dispose();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        buscarOrdem();
    }

    private void atualizarInformacoesUsuario() {
        jLabel1.setText("Sistema Online | Usuário: " + usuarioLogado.getNome() + " (" + usuarioLogado.getId() + ")");
    }

    private void configurarTabela() {
        String[] colunas = {"Código", "Cliente", "Data", "Total (MT)", "Status"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(tableModel);
    }

    private void carregarOrdensNaoProcessadas() {
        tableModel.setRowCount(0);
        List<Ordem> ordens = vendasService.getOrdensNaoProcessadas();
        
        for (Ordem ordem : ordens) {
            Object[] row = {
                ordem.getCodigo(),
                ordem.getClienteNome(),
                ordem.getDataCriacao().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                String.format("%.2f", ordem.getValorTotal()),
                ordem.getStatus()
            };
            tableModel.addRow(row);
        }
    }

    private void buscarOrdem() {
        String codigo = jTextField1.getText().trim();
        
        if (codigo.isEmpty()) {
            carregarOrdensNaoProcessadas();
            return;
        }
        
        Ordem ordem = vendasService.buscarOrdem(codigo);
        
        if (ordem != null) {
            tableModel.setRowCount(0);
            Object[] row = {
                ordem.getCodigo(),
                ordem.getClienteNome(),
                ordem.getDataCriacao().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                String.format("%.2f", ordem.getValorTotal()),
                ordem.getStatus()
            };
            tableModel.addRow(row);
        } else {
            JOptionPane.showMessageDialog(this, "Ordem não encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void processarPagamento() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma ordem na tabela!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String codigoOrdem = (String) tableModel.getValueAt(selectedRow, 0);
        Ordem ordem = vendasService.buscarOrdem(codigoOrdem);
        
        if (ordem == null) {
            JOptionPane.showMessageDialog(this, "Ordem não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (ordem.getStatus() == Ordem.StatusOrdem.PAGO) {
            JOptionPane.showMessageDialog(this, "Esta ordem já foi paga!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Abrir tela de lançamento de pagamento
        TelaLancamentoPagamento telaLancamentoPagamento = new TelaLancamentoPagamento(usuarioLogado, caixaService, vendasService, ordem);
        telaLancamentoPagamento.setVisible(true);
        this.dispose();
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration
}
