/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroPessoas extends JFrame {
    private List<Pessoa> pessoas = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtNome, txtIdade, txtMatricula;
    private JComboBox<String> cbTipo;
    
    public CadastroPessoas() {
        setTitle("Cadastro de Pessoas e Alunos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Painel de entrada
        JPanel panelEntrada = new JPanel(new GridLayout(4, 2));
        panelEntrada.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panelEntrada.add(txtNome);
        
        panelEntrada.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        panelEntrada.add(txtIdade);
        
        panelEntrada.add(new JLabel("Matrícula (apenas para alunos):"));
        txtMatricula = new JTextField();
        panelEntrada.add(txtMatricula);
        
        panelEntrada.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(new String[]{"Pessoa", "Aluno"});
        panelEntrada.add(cbTipo);
        
        // Painel de botões
        JPanel panelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnGravar = new JButton("Gravar");
        JButton btnCarregar = new JButton("Carregar");
        
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnGravar);
        panelBotoes.add(btnCarregar);
        
        // Tabela
        tableModel = new DefaultTableModel(new String[]{"Tipo", "Nome", "Idade", "Matrícula"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Adicionar componentes ao frame
        add(panelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        
        // Listeners
        btnAdicionar.addActionListener(e -> adicionarPessoa());
        btnGravar.addActionListener(e -> gravarPessoas());
        btnCarregar.addActionListener(e -> carregarPessoas());
        
        // Atualizar visibilidade do campo matrícula
        cbTipo.addActionListener(e -> atualizarCampos());
        atualizarCampos();
    }
    
    private void atualizarCampos() {
        String tipo = (String) cbTipo.getSelectedItem();
        txtMatricula.setEnabled("Aluno".equals(tipo));
    }
    
    private void adicionarPessoa() {
        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            String tipo = (String) cbTipo.getSelectedItem();
            
            Pessoa pessoa;
            if ("Aluno".equals(tipo)) {
                String matricula = txtMatricula.getText();
                if (matricula.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Matrícula é obrigatória para alunos!");
                    return;
                }
                pessoa = new Aluno(nome, idade, matricula);
                tableModel.addRow(new Object[]{"Aluno", nome, idade, matricula});
            } else {
                pessoa = new Pessoa(nome, idade);
                tableModel.addRow(new Object[]{"Pessoa", nome, idade, "N/A"});
            }
            
            pessoas.add(pessoa);
            
            // Limpar campos
            txtNome.setText("");
            txtIdade.setText("");
            txtMatricula.setText("");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade deve ser um número!");
        }
    }
    
    private void gravarPessoas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
             new FileOutputStream("pessoas.dat"))) {
            oos.writeObject(pessoas);
            JOptionPane.showMessageDialog(this, "Dados gravados com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao gravar: " + ex.getMessage());
        }
    }
    
    private void carregarPessoas() {
        try (ObjectInputStream ois = new ObjectInputStream(
             new FileInputStream("pessoas.dat"))) {
            pessoas = (List<Pessoa>) ois.readObject();
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Dados carregados com sucesso!");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage());
        }
    }
    
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Aluno) {
                Aluno aluno = (Aluno) pessoa;
                tableModel.addRow(new Object[]{"Aluno", aluno.getNome(), aluno.getIdade(), aluno.getMatricula()});
            } else {
                tableModel.addRow(new Object[]{"Pessoa", pessoa.getNome(), pessoa.getIdade(), "N/A"});
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CadastroPessoas().setVisible(true);
        });
    }
}