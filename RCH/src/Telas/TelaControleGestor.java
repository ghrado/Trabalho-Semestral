package Telas;

import Controller.ControleGestorService;
import Model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaControleGestor extends JFrame {
    private Usuario usuarioLogado;
    private ControleGestorService controleGestorService;
    
    private JTable tblUsuarios;
    private JButton btnCadastrar;
    private JButton btnEditar;
    private JButton btnDesativar;
    private JButton btnReintegrar;
    private JButton btnVoltar;
    
    public TelaControleGestor(Usuario usuario, ControleGestorService controleGestorService) {
        this.usuarioLogado = usuario;
        this.controleGestorService = controleGestorService;
        
        initComponents();
        carregarUsuarios();
    }
    
    private void initComponents() {
        setTitle("Sistema de Gestão de Vendas - Controle de Gestor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        setLayout(new BorderLayout());
        
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitulo = new JLabel("Gestão de Usuários");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelTop.add(lblTitulo);
        
        tblUsuarios = new JTable(new DefaultTableModel(
            new Object[]{"ID", "Nome", "Tipo", "Usuário", "Status"}, 0
        ));
        JScrollPane scrollPane = new JScrollPane(tblUsuarios);
        
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnCadastrar = new JButton("Cadastrar Usuário");
        btnEditar = new JButton("Editar");
        btnDesativar = new JButton("Desativar");
        btnReintegrar = new JButton("Reintegrar");
        btnVoltar = new JButton("Voltar");
        
        btnCadastrar.addActionListener(evt -> cadastrarUsuario());
        btnEditar.addActionListener(evt -> editarUsuario());
        btnDesativar.addActionListener(evt -> desativarUsuario());
        btnReintegrar.addActionListener(evt -> reintegrarUsuario());
        btnVoltar.addActionListener(evt -> voltar());
        
        panelBotoes.add(btnCadastrar);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnDesativar);
        panelBotoes.add(btnReintegrar);
        panelBotoes.add(btnVoltar);
        
        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void carregarUsuarios() {
        DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
        model.setRowCount(0);
        
        for (Usuario u : controleGestorService.listarUsuarios()) {
            model.addRow(new Object[]{
                u.getId(),
                u.getNome(),
                u.getClass().getSimpleName(),
                u.getUsuario(),
                u.isAtivo() ? "Ativo" : "Inativo"
            });
        }
    }
    
    private void cadastrarUsuario() {
        String[] tipos = {"Gerente", "Vendedor", "Caixa"};
        String tipo = (String) JOptionPane.showInputDialog(this, 
            "Selecione o tipo de usuário:", 
            "Tipo de Usuário", 
            JOptionPane.QUESTION_MESSAGE, 
            null, tipos, tipos[0]);
        
        if (tipo == null) return;
        
        JTextField txtNome = new JTextField(20);
        JTextField txtEndereco = new JTextField(20);
        JTextField txtBI = new JTextField(20);
        JTextField txtTelefone = new JTextField(20);
        JTextField txtEmail = new JTextField(20);
        
        Object[] message = {
            "Nome:", txtNome,
            "Endereço:", txtEndereco,
            "BI:", txtBI,
            "Telefone:", txtTelefone,
            "Email:", txtEmail
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Cadastrar " + tipo, JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String nome = txtNome.getText().trim();
            String endereco = txtEndereco.getText().trim();
            String bi = txtBI.getText().trim();
            String telefone = txtTelefone.getText().trim();
            String email = txtEmail.getText().trim();
            
            if (nome.isEmpty() || endereco.isEmpty() || bi.isEmpty() || telefone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Usuario novoUsuario = null;
            switch (tipo) {
                case "Gerente":
                    novoUsuario = controleGestorService.cadastrarGerente(nome, endereco, bi, telefone, email);
                    break;
                case "Vendedor":
                    novoUsuario = controleGestorService.cadastrarVendedor(nome, endereco, bi, telefone, email);
                    break;
                case "Caixa":
                    novoUsuario = controleGestorService.cadastrarCaixa(nome, endereco, bi, telefone, email);
                    break;
            }
            
            if (novoUsuario != null) {
                JOptionPane.showMessageDialog(this, 
                    "Usuário cadastrado com sucesso!\n" +
                    "ID: " + novoUsuario.getId() + "\n" +
                    "Usuário: " + novoUsuario.getUsuario() + "\n" +
                    "Senha padrão: 000000", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                carregarUsuarios();
            }
        }
    }
    
    private void editarUsuario() {
        int row = tblUsuarios.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = (String) tblUsuarios.getValueAt(row, 0);
        Usuario usuario = controleGestorService.buscarUsuarioPorId(id);
        
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JTextField txtNome = new JTextField(usuario.getNome(), 20);
        JTextField txtEndereco = new JTextField(usuario.getEndereco(), 20);
        JTextField txtTelefone = new JTextField(usuario.getTelefone(), 20);
        JTextField txtEmail = new JTextField(usuario.getEmail(), 20);
        
        Object[] message = {
            "Nome:", txtNome,
            "Endereço:", txtEndereco,
            "Telefone:", txtTelefone,
            "Email:", txtEmail
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Editar Usuário", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            usuario.setNome(txtNome.getText().trim());
            usuario.setEndereco(txtEndereco.getText().trim());
            usuario.setTelefone(txtTelefone.getText().trim());
            usuario.setEmail(txtEmail.getText().trim());
            
            JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carregarUsuarios();
        }
    }
    
    private void desativarUsuario() {
        int row = tblUsuarios.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = (String) tblUsuarios.getValueAt(row, 0);
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Deseja desativar este usuário?", 
            "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            controleGestorService.desativarUsuario(id);
            JOptionPane.showMessageDialog(this, "Usuário desativado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carregarUsuarios();
        }
    }
    
    private void reintegrarUsuario() {
        int row = tblUsuarios.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = (String) tblUsuarios.getValueAt(row, 0);
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Deseja reintegrar este usuário?", 
            "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            controleGestorService.reintegrarUsuario(id);
            JOptionPane.showMessageDialog(this, "Usuário reintegrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carregarUsuarios();
        }
    }
    
    private void voltar() {
        dispose();
    }
}
