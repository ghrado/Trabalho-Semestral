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

public class CadastroAnimais extends JFrame {
    private List<Animal> animais = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtNome, txtIdade;
    private JComboBox<String> cbTipo;
    
    public CadastroAnimais() {
        setTitle("Cadastro de Animais");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Painel de entrada
        JPanel panelEntrada = new JPanel(new GridLayout(3, 2));
        panelEntrada.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panelEntrada.add(txtNome);
        
        panelEntrada.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        panelEntrada.add(txtIdade);
        
        panelEntrada.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(new String[]{"Cachorro", "Gato"});
        panelEntrada.add(cbTipo);
        
        // Painel de botões
        JPanel panelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnGravar = new JButton("Gravar");
        JButton btnCarregar = new JButton("Carregar");
        JButton btnEmitirSons = new JButton("Emitir Sons");
        
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnGravar);
        panelBotoes.add(btnCarregar);
        panelBotoes.add(btnEmitirSons);
        
        // Tabela
        tableModel = new DefaultTableModel(new String[]{"Tipo", "Nome", "Idade"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Adicionar componentes ao frame
        add(panelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        
        // Listeners
        btnAdicionar.addActionListener(e -> adicionarAnimal());
        btnGravar.addActionListener(e -> gravarAnimais());
        btnCarregar.addActionListener(e -> carregarAnimais());
        btnEmitirSons.addActionListener(e -> emitirSonsRecursivo());
    }
    
    private void adicionarAnimal() {
        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            String tipo = (String) cbTipo.getSelectedItem();
            
            Animal animal;
            if ("Cachorro".equals(tipo)) {
                animal = new Cachorro(nome, idade);
            } else {
                animal = new Gato(nome, idade);
            }
            
            animais.add(animal);
            tableModel.addRow(new Object[]{tipo, nome, idade});
            
            txtNome.setText("");
            txtIdade.setText("");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade deve ser um número!");
        }
    }
    
    private void gravarAnimais() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
             new FileOutputStream("animais.dat"))) {
            oos.writeObject(animais);
            JOptionPane.showMessageDialog(this, "Animais gravados com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao gravar: " + ex.getMessage());
        }
    }
    
    private void carregarAnimais() {
        try (ObjectInputStream ois = new ObjectInputStream(
             new FileInputStream("animais.dat"))) {
            animais = (List<Animal>) ois.readObject();
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Animais carregados com sucesso!");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage());
        }
    }
    
    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (Animal animal : animais) {
            String tipo = animal instanceof Cachorro ? "Cachorro" : "Gato";
            tableModel.addRow(new Object[]{tipo, animal.getNome(), animal.getIdade()});
        }
    }
    
    private void emitirSonsRecursivo() {
        if (animais.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum animal cadastrado!");
            return;
        }
        
        StringBuilder sons = new StringBuilder("Sons dos animais:\n");
        emitirSonsRecursivo(animais, 0, sons);
        JOptionPane.showMessageDialog(this, sons.toString());
    }
    
    private void emitirSonsRecursivo(List<Animal> animais, int index, StringBuilder sb) {
        if (index >= animais.size()) return;
        
        Animal animal = animais.get(index);
        sb.append(animal.getNome()).append(": ").append(animal.fazerSom()).append("\n");
        emitirSonsRecursivo(animais, index + 1, sb);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CadastroAnimais().setVisible(true);
        });
    }
}