package Telas;

import Controller.CaixaService;
import Controller.ControleGestorService;
import Controller.RelatoriosService;
import Controller.StockService;
import Controller.VendasService;
import Model.Usuario;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import Telas.vendas.TelaVendas;
import Telas.Caixa.TelaCaixaOpcoes;
import Telas.Relatorios.TelaRelatorios;
import Telas.ControleGestor.TelaControleGestor;
import Telas.GestaoStock.TelaGestaoStock;
import Telas.FechoCaixa.TelaFechoCaixa;

public class TelaAdministrador extends javax.swing.JFrame {
    private Usuario usuarioLogado;
    private ControleGestorService controleGestorService;
    private VendasService vendasService;
    private StockService stockService;
    private CaixaService caixaService;
    private RelatoriosService relatoriosService;
    
    public TelaAdministrador(Usuario usuario, ControleGestorService controleGestorService) {
        this.usuarioLogado = usuario;
        this.controleGestorService = controleGestorService;
        this.vendasService = new VendasService();
        this.stockService = new StockService();
        this.caixaService = new CaixaService(vendasService);
        this.relatoriosService = new RelatoriosService(vendasService, stockService, caixaService, controleGestorService);
        
        initComponents();
        atualizarInformacoesUsuario();
    }
    
    private void atualizarInformacoesUsuario() {
        jLabel1.setText("Sistema Online | Usuário: " + usuarioLogado.getNome() + " (" + usuarioLogado.getId() + ")");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        salesBtn = new javax.swing.JButton();
        managerBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        stockBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        casherBtn = new javax.swing.JButton();
        reportsBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Gestão de Vendas - Administrador");
        setPreferredSize(new java.awt.Dimension(700, 550));

        jDesktopPane2.setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Sistema Online | Usuário: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Sales");

        salesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/IconsDB/icons8-sale-64.png"))); // NOI18N
        salesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesBtnActionPerformed(evt);
            }
        });

        managerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/IconsDB/icons8-accounting-64.png"))); // NOI18N
        managerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managerBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Manager");

        stockBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/IconsDB/icons8-produto-64.png"))); // NOI18N
        stockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Stock");

        casherBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/IconsDB/icons8-safe-in-64.png"))); // NOI18N

        reportsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/IconsDB/icons8-circle-chart-64.png"))); // NOI18N

        jLabel8.setText("Casher");

        jLabel9.setText("Reports");

        jDesktopPane2.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(salesBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(managerBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(stockBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(casherBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(reportsBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(salesBtn)
                        .addGap(93, 93, 93)
                        .addComponent(managerBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addComponent(stockBtn)
                        .addGap(95, 95, 95))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel7)
                        .addGap(122, 122, 122)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(110, 110, 110)))
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(casherBtn)
                        .addGap(93, 93, 93)
                        .addComponent(reportsBtn)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(120, 120, 120)
                        .addComponent(jLabel9)
                        .addGap(95, 95, 95))))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap(266, Short.MAX_VALUE)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(salesBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(managerBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(stockBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(reportsBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(casherBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9)
                        .addComponent(jLabel3)))
                .addGap(202, 202, 202)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void salesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesBtnActionPerformed
        TelaVendas telaVendas = new TelaVendas(usuarioLogado, vendasService, stockService);
        telaVendas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_salesBtnActionPerformed

    private void managerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managerBtnActionPerformed
        TelaControleGestor telaControleGestor = new TelaControleGestor(usuarioLogado, controleGestorService);
        telaControleGestor.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_managerBtnActionPerformed

    private void stockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockBtnActionPerformed
        TelaGestaoStock telaStock = new TelaGestaoStock(usuarioLogado, stockService);
        telaStock.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_stockBtnActionPerformed
    
    private void casherBtnActionPerformed(java.awt.event.ActionEvent evt) {
        Telas.Caixa.TelaCaixaOpcoes telaCaixaOpcoes = new Telas.Caixa.TelaCaixaOpcoes(usuarioLogado, caixaService, vendasService);
        telaCaixaOpcoes.setVisible(true);
        this.dispose();
    }
    
    private void reportsBtnActionPerformed(java.awt.event.ActionEvent evt) {
        TelaRelatorios telaRelatorios = new TelaRelatorios(usuarioLogado, relatoriosService);
        telaRelatorios.setVisible(true);
        this.dispose();
    }
    
    private void fechoCaixaBtnActionPerformed(java.awt.event.ActionEvent evt) {
        TelaFechoCaixa telaFechoCaixa = new TelaFechoCaixa(usuarioLogado, caixaService);
        telaFechoCaixa.setVisible(true);
        this.dispose();
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
            StringBuilder sb = new StringBuilder("Notificações:\n\n");
            while (controleGestorService.temNotificacoes()) {
                sb.append("• ").append(controleGestorService.obterProximaNotificacao()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Notificações", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Não há notificações.", "Notificações", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton casherBtn;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton managerBtn;
    private javax.swing.JButton reportsBtn;
    private javax.swing.JButton salesBtn;
    private javax.swing.JButton stockBtn;
    // End of variables declaration//GEN-END:variables
}
