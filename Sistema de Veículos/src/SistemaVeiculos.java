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

public class SistemaVeiculos extends JFrame {
    private List<Veiculo> veiculos = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtMarca, txtModelo;
    private JComboBox<String> cbTipo;
    private JTextArea txtArea;
    
    public SistemaVeiculos() {
        setTitle("Sistema de Veículos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Painel de entrada
        JPanel panelEntrada = new JPanel(new GridLayout(3, 2));
        panelEntrada.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        panelEntrada.add(txtMarca);
        
        panelEntrada.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        panelEntrada.add(txtModelo);
        
        panelEntrada.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(new String[]{"Carro", "Bicicleta"});
        panelEntrada.add(cbTipo);
        
        // Painel de botões
        JPanel panelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnGravar = new JButton("Gravar");
        JButton btnCarregar = new JButton("Carregar");
        JButton btnMostrarTodos = new JButton("Mostrar Todos");
        
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnGravar);
        panelBotoes.add(btnCarregar);
        panelBotoes.add(btnMostrarTodos);
        
        // Tabela
        tableModel = new DefaultTableModel(new String[]{"Tipo", "Marca", "Modelo"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollTable = new JScrollPane(table);
        
        // Área de texto
        txtArea = new JTextArea();
        JScrollPane scrollText = new JScrollPane(txtArea);
        
        // Split pane para dividir tabela e área de texto
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollTable, scrollText);
        splitPane.setDividerLocation(200);
        
        // Adicionar componentes ao frame
        add(panelEntrada, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        
        // Listeners
        btnAdicionar.addActionListener(e -> adicionarVeiculo());
        btnGravar.addActionListener(e -> gravarVeiculos());
        btnCarregar.addActionListener(e -> carregarVeiculos());
        btnMostrarTodos.addActionListener(e -> mostrarTodosRecursivo());
    }
    
    private void adicionarVeiculo() {
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            String tipo = (String) cbTipo.getSelectedItem();
            
            Veiculo veiculo;
            if ("Carro".equals(tipo)) {
                veiculo = new Carro(marca, modelo);
            } else {
                veiculo = new Bicicleta(marca, modelo);
            }
            
            veiculos.add(veiculo);
            tableModel.addRow(new Object[]{tipo, marca, modelo});
            
            txtMarca.setText("");
            txtModelo.setText("");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar veículo!");
        }
    }
    
    private void gravarVeiculos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
             new FileOutputStream("veiculos.dat"))) {
            oos.writeObject(veiculos);
            JOptionPane.showMessageDialog(this, "Veículos gravados com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao gravar: " + ex.getMessage());
        }
    }
    
    private void carregarVeiculos() {
        try (ObjectInputStream ois = new ObjectInputStream(
             new FileInputStream("veiculos.dat"))) {
            veiculos = (List<Veiculo>) ois.readObject();
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Veículos carregados com sucesso!");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage());
        }
    }
    
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (Veiculo veiculo : veiculos) {
            String tipo = veiculo instanceof Carro ? "Carro" : "Bicicleta";
            tableModel.addRow(new Object[]{tipo, veiculo.getMarca(), veiculo.getModelo()});
        }
    }
    
    private void mostrarTodosRecursivo() {
        txtArea.setText("");
        if (veiculos.isEmpty()) {
            txtArea.setText("Nenhum veículo cadastrado!");
            return;
        }
        
        listarMovimentosRecursivo(veiculos, 0);
    }
    
    private void listarMovimentosRecursivo(List<Veiculo> veiculos, int index) {
        if (index >= veiculos.size()) return;
        
        Veiculo veiculo = veiculos.get(index);
        txtArea.append(veiculo.mover() + "\n");
        listarMovimentosRecursivo(veiculos, index + 1);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SistemaVeiculos().setVisible(true);
        });
    }
}