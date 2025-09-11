/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
import java.util.Stack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Exercicio3Recursividade extends JFrame {
    private JTextField txtNumero;
    private JTextArea txtAreaPilha;
    private JButton btnAdicionar, btnCalcular, btnLimpar;
    private Stack<Integer> pilha;
    
    public Exercicio3Recursividade() {
        setTitle("Exercício 3 - Produto Recursivo de Pilha");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        pilha = new Stack<>();
        
        // Painel de entrada
        JPanel panelEntrada = new JPanel(new FlowLayout());
        panelEntrada.add(new JLabel("Número:"));
        txtNumero = new JTextField(10);
        panelEntrada.add(txtNumero);
        
        btnAdicionar = new JButton("Adicionar à Pilha");
        panelEntrada.add(btnAdicionar);
        
        // Área de texto para mostrar a pilha
        txtAreaPilha = new JTextArea(8, 30);
        txtAreaPilha.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaPilha);
        
        // Painel de botões
        JPanel panelBotoes = new JPanel();
        btnCalcular = new JButton("Calcular Produto");
        btnLimpar = new JButton("Limpar Pilha");
        panelBotoes.add(btnCalcular);
        panelBotoes.add(btnLimpar);
        
        // Adicionar componentes ao frame
        add(panelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        
        // Listeners
        btnAdicionar.addActionListener(e -> adicionarNumero());
        btnCalcular.addActionListener(e -> calcularProduto());
        btnLimpar.addActionListener(e -> limparPilha());
        
        // Enter no campo de texto também adiciona
        txtNumero.addActionListener(e -> adicionarNumero());
    }
    
    private void adicionarNumero() {
        try {
            String texto = txtNumero.getText().trim();
            if (!texto.isEmpty()) {
                int numero = Integer.parseInt(texto);
                pilha.push(numero);
                atualizarExibicaoPilha();
                txtNumero.setText("");
                txtNumero.requestFocus();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um número inteiro válido!");
            txtNumero.setText("");
            txtNumero.requestFocus();
        }
    }
    
    private void calcularProduto() {
        if (pilha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "A pilha está vazia! Adicione números primeiro.");
            return;
        }
        
        // Criar uma cópia da pilha para não modificar a original
        Stack<Integer> pilhaCopia = new Stack<>();
        pilhaCopia.addAll(pilha);
        
        int produto = produtoPilhaRecursivo(pilhaCopia);
        JOptionPane.showMessageDialog(this, 
            "Pilha: " + pilha.toString() + 
            "\nProduto de todos os elementos: " + produto);
    }
    
    /**
     * Método recursivo que calcula o produto de todos os elementos de uma pilha
     * @param pilha - Stack<Integer> com os números
     * @return produto de todos os elementos da pilha
     */
    private int produtoPilhaRecursivo(Stack<Integer> pilha) {
        // Caso base: pilha vazia retorna 1 (elemento neutro da multiplicação)
        if (pilha.isEmpty()) {
            return 1;
        }
        
        // Remove o elemento do topo e chama recursivamente
        int elemento = pilha.pop();
        int produto = elemento * produtoPilhaRecursivo(pilha);
        
        return produto;
    }
    
    private void limparPilha() {
        pilha.clear();
        atualizarExibicaoPilha();
        JOptionPane.showMessageDialog(this, "Pilha limpa!");
    }
    
    private void atualizarExibicaoPilha() {
        txtAreaPilha.setText("Conteúdo da Pilha (topo primeiro):\n");
        if (pilha.isEmpty()) {
            txtAreaPilha.append("[Pilha vazia]");
        } else {
            // Mostrar do topo para a base
            for (int i = pilha.size() - 1; i >= 0; i--) {
                txtAreaPilha.append("[" + i + "] → " + pilha.get(i) + "\n");
            }
            txtAreaPilha.append("\nTotal de elementos: " + pilha.size());
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Exercicio3Recursividade().setVisible(true);
        });
    }
}