/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas.controle;

import Controller.UsuarioService;
import Model.Usuario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author massas
 */
public class TelaGerenciarUsuario extends javax.swing.JFrame {
    
    private Usuario usuarioLogado;
    private UsuarioService usuarioService;
    private DefaultTableModel tableModel;

    /**
     * Creates new form TelaGerenciarUsuario
     */
    public TelaGerenciarUsuario(Usuario usuario, UsuarioService usuarioService) {
        this.usuarioLogado = usuario;
        this.usuarioService = usuarioService;
        initComponents();
        atualizarInformacoesUsuario();
        configurarTabela();
        carregarUsuarios();
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
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setText("Sistema Online | Usuario:");

        jLabel4.setText("Data:  | Hora: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(150, 150, 150))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Nome", "Tipo", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Cadastrar Usuário");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Editar Usuário");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Excluir Usuário");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Voltar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar:");

        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(39, 39, 39)
                        .addComponent(jButton2)
                        .addGap(39, 39, 39)
                        .addComponent(jButton3)
                        .addGap(39, 39, 39)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(56, 56, 56)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        cadastrarUsuario();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        editarUsuario();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        excluirUsuario();
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        TelaControleOpcoes telaControleOpcoes = new TelaControleOpcoes(usuarioLogado, usuarioService);
        telaControleOpcoes.setVisible(true);
        this.dispose();
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        buscarUsuario();
    }

    private void atualizarInformacoesUsuario() {
        jLabel1.setText("Sistema Online | Usuário: " + usuarioLogado.getNome() + " (" + usuarioLogado.getId() + ")");
    }

    private void configurarTabela() {
        String[] colunas = {"ID", "Nome", "Tipo", "Status"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int columnIndex) {
                return false;
            }
        };
        jTable1.setModel(tableModel);
    }

    private void carregarUsuarios() {
        tableModel.setRowCount(0);
        List<Usuario> usuarios = usuarioService.listarTodos();
        for (Usuario usuario : usuarios) {
            if (usuario.isAtivo()) {
                Object[] row = {
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getTipoUsuario(),
                    usuario.isAtivo() ? "Ativo" : "Inativo"
                };
                tableModel.addRow(row);
            }
        }
    }

    private void buscarUsuario() {
        String termo = jTextField1.getText().trim();
        
        if (termo.isEmpty()) {
            carregarUsuarios();
            return;
        }
        
        tableModel.setRowCount(0);
        
        Usuario usuario = usuarioService.buscar(termo);
        if (usuario != null && usuario.isAtivo()) {
            Object[] row = {
                usuario.getId(),
                usuario.getNome(),
                usuario.getTipoUsuario(),
                usuario.isAtivo() ? "Ativo" : "Inativo"
            };
            tableModel.addRow(row);
        } else {
            List<Usuario> usuarios = usuarioService.buscarPorNome(termo);
            for (Usuario u : usuarios) {
                if (u.isAtivo()) {
                    Object[] row = {
                        u.getId(),
                        u.getNome(),
                        u.getTipoUsuario(),
                        u.isAtivo() ? "Ativo" : "Inativo"
                    };
                    tableModel.addRow(row);
                }
            }
        }
        
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum usuário encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            carregarUsuarios();
        }
    }

    private void cadastrarUsuario() {
        javax.swing.JTextField idField = new javax.swing.JTextField();
        javax.swing.JTextField nomeField = new javax.swing.JTextField();
        javax.swing.JPasswordField senhaField = new javax.swing.JPasswordField();
        javax.swing.JComboBox<String> tipoCombo = new javax.swing.JComboBox<>(
            new String[]{"GERENTE", "VENDEDOR", "CAIXA"}
        );
        
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(5, 5, 5, 5);
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new javax.swing.JLabel("ID:"), gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new javax.swing.JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        panel.add(nomeField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new javax.swing.JLabel("Senha:"), gbc);
        gbc.gridx = 1;
        panel.add(senhaField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new javax.swing.JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        panel.add(tipoCombo, gbc);
        
        int option = JOptionPane.showConfirmDialog(this, panel, "Cadastrar Usuário", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText().trim();
            String nome = nomeField.getText().trim();
            String senha = new String(senhaField.getPassword());
            String tipoStr = (String) tipoCombo.getSelectedItem();
            
            if (id.isEmpty() || nome.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Usuario.TipoUsuario tipo = Usuario.TipoUsuario.valueOf(tipoStr);
            Usuario usuario = new Usuario(id, nome, senha, tipo);
            
            if (usuarioService.criar(usuario)) {
                usuarioService.registrarAcaoUsuario(usuario.getId(), "CADASTRO", 
                    "Usuário cadastrado por " + usuarioLogado.getNome());
                
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                carregarUsuarios();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário! ID já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarUsuario() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário na tabela!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String usuarioId = (String) tableModel.getValueAt(selectedRow, 0);
        Usuario usuario = usuarioService.buscar(usuarioId);
        
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        javax.swing.JTextField nomeField = new javax.swing.JTextField(usuario.getNome());
        javax.swing.JComboBox<String> tipoCombo = new javax.swing.JComboBox<>(
            new String[]{"GERENTE", "VENDEDOR", "CAIXA"}
        );
        tipoCombo.setSelectedItem(usuario.getTipoUsuario().toString());
        
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(5, 5, 5, 5);
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new javax.swing.JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        panel.add(nomeField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new javax.swing.JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        panel.add(tipoCombo, gbc);
        
        int option = JOptionPane.showConfirmDialog(this, panel, "Editar Usuário", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText().trim();
            String tipoStr = (String) tipoCombo.getSelectedItem();
            
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            usuario.setNome(nome);
            usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(tipoStr));
            
            if (usuarioService.atualizar(usuario)) {
                usuarioService.registrarAcaoUsuario(usuario.getId(), "EDICAO", 
                    "Usuário editado por " + usuarioLogado.getNome());
                
                JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!");
                carregarUsuarios();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void excluirUsuario() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário na tabela!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String usuarioId = (String) tableModel.getValueAt(selectedRow, 0);
        Usuario usuario = usuarioService.buscar(usuarioId);
        
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (usuario.getId().equals(usuarioLogado.getId())) {
            JOptionPane.showMessageDialog(this, "Você não pode excluir seu próprio usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(this, 
            "Tem certeza que deseja excluir o usuário " + usuario.getNome() + "?", 
            "Confirmar Exclusão", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            if (usuarioService.excluir(usuario.getId())) {
                usuarioService.registrarAcaoUsuario(usuario.getId(), "EXCLUSAO", 
                    "Usuário excluído por " + usuarioLogado.getNome());
                
                JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso!");
                carregarUsuarios();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration
}
