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

public class SistemaFuncionarios extends JFrame {
    private List<Funcionario> funcionarios = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtNome, txtSalario, txtPercentual;
    private JComboBox<String> cbTipo;
    
    public SistemaFuncionarios() {
        setTitle("Sistema de Funcionários");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Painel de entrada
        JPanel panelEntrada = new JPanel(new GridLayout(4, 2));
        panelEntrada.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panelEntrada.add(txtNome);
        
        panelEntrada.add(new JLabel("Salário:"));
        txtSalario = new JTextField();
        panelEntrada.add(txtSalario);
        
        panelEntrada.add(new JLabel("Percentual (apenas para gerentes):"));
        txtPercentual = new JTextField();
        panelEntrada.add(txtPercentual);
        
        panelEntrada.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(new String[]{"Funcionário", "Gerente"});
        panelEntrada.add(cbTipo);
        
        // Painel de botões
        JPanel panelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnGravar = new JButton("Gravar");
        JButton btnCarregar = new JButton("Carregar");
        JButton btnCalcularFolha = new JButton("Calcular Folha Total");
        JButton btnAumentarSalario = new JButton("Aumentar Salário");
        
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnGravar);
        panelBotoes.add(btnCarregar);
        panelBotoes.add(btnCalcularFolha);
        panelBotoes.add(btnAumentarSalario);
        
        // Tabela
        tableModel = new DefaultTableModel(new String[]{"Tipo", "Nome", "Salário"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Adicionar componentes ao frame
        add(panelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        
        // Listeners
        btnAdicionar.addActionListener(e -> adicionarFuncionario());
        btnGravar.addActionListener(e -> gravarFuncionarios());
        btnCarregar.addActionListener(e -> carregarFuncionarios());
        btnCalcularFolha.addActionListener(e -> calcularFolhaTotal());
        btnAumentarSalario.addActionListener(e -> aumentarSalario());
        
        // Atualizar visibilidade do campo percentual
        cbTipo.addActionListener(e -> atualizarCampos());
        atualizarCampos();
    }
    
    private void atualizarCampos() {
        String tipo = (String) cbTipo.getSelectedItem();
        txtPercentual.setEnabled("Gerente".equals(tipo));
    }
    
    private void adicionarFuncionario() {
        try {
            String nome = txtNome.getText();
            double salario = Double.parseDouble(txtSalario.getText());
            String tipo = (String) cbTipo.getSelectedItem();
            
            Funcionario funcionario;
            if ("Gerente".equals(tipo)) {
                funcionario = new Gerente(nome, salario);
            } else {
                funcionario = new Funcionario(nome, salario);
            }
            
            funcionarios.add(funcionario);
            tableModel.addRow(new Object[]{tipo, nome, salario});
            
            // Limpar campos
            txtNome.setText("");
            txtSalario.setText("");
            txtPercentual.setText("");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salário deve ser um número!");
        }
    }
    
    private void gravarFuncionarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
             new FileOutputStream("funcionarios.dat"))) {
            oos.writeObject(funcionarios);
            JOptionPane.showMessageDialog(this, "Funcionários gravados com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao gravar: " + ex.getMessage());
        }
    }
    
    private void carregarFuncionarios() {
        try (ObjectInputStream ois = new ObjectInputStream(
             new FileInputStream("funcionarios.dat"))) {
            funcionarios = (List<Funcionario>) ois.readObject();
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Funcionários carregados com sucesso!");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage());
        }
    }
    
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (Funcionario funcionario : funcionarios) {
            String tipo = funcionario instanceof Gerente ? "Gerente" : "Funcionário";
            tableModel.addRow(new Object[]{tipo, funcionario.getNome(), funcionario.getSalario()});
        }
    }
    
    private void calcularFolhaTotal() {
        double total = somaSalariosRecursiva(funcionarios, 0);
        JOptionPane.showMessageDialog(this, "Folha salarial total: " + total);
    }
    
    private double somaSalariosRecursiva(List<Funcionario> funcionarios, int index) {
        if (index >= funcionarios.size()) return 0;
        return funcionarios.get(index).getSalario() + somaSalariosRecursiva(funcionarios, index + 1);
    }
    
    private void aumentarSalario() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um gerente na tabela!");
            return;
        }
        
        Funcionario funcionario = funcionarios.get(selectedRow);
        if (!(funcionario instanceof Gerente)) {
            JOptionPane.showMessageDialog(this, "Apenas gerentes podem ter aumento de salário!");
            return;
        }
        
        try {
            double percentual = Double.parseDouble(txtPercentual.getText());
            Gerente gerente = (Gerente) funcionario;
            gerente.aumentarSalario(percentual);
            
            // Atualizar tabela
            tableModel.setValueAt(gerente.getSalario(), selectedRow, 2);
            JOptionPane.showMessageDialog(this, "Salário aumentado com sucesso!");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Percentual deve ser um número!");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SistemaFuncionarios().setVisible(true);
        });
    }
}