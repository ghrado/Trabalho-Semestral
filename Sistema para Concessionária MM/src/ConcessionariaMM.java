/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ConcessionariaMM {
    private static List<Veiculo> veiculos = new ArrayList<>();
    private static final String ARQUIVO_DADOS = "veiculos_concessionaria.dat";
    
    public static void main(String[] args) {
        // d) Criar dois objetos para cada subclasse
        criarVeiculosExemplo();
        
        // e) Criar lista dinâmica já foi feita acima
        
        // f) Selecionar objeto e imprimir atributos
        System.out.println("=== ITEM F - ATRIBUTOS ESPECÍFICOS ===");
        selecionarEImprimirAtributos();
        
        // g) Filtrar e exibir apenas carros
        System.out.println("\n=== ITEM G - FILTRAR CARROS ===");
        filtrarCarros();
        
        // h) Armazenar em base de dados
        System.out.println("\n=== ITEM H - ARMAZENAR EM BD ===");
        armazenarEmBaseDados();
        
        // i) Interface gráfica para cadastro
        System.out.println("\n=== ITEM I - INTERFACE GRÁFICA ===");
        criarInterfaceCadastro();
        
        // Demonstração adicional - carregar dados
        System.out.println("\n=== DEMONSTRAÇÃO - CARREGAR DADOS ===");
        demonstrarCarregamentoDados();
    }
    
    private static void criarVeiculosExemplo() {
        // d) Criar dois objetos para cada subclasse
        Veiculo carro1 = new Carro("Model S", 80000, "Tesla", 2024, "Vermelho", 
                                  true, 4, "Elétrico");
        Veiculo carro2 = new Carro("Civic", 35000, "Honda", 2023, "Preto", 
                                  false, 4, "Gasolina");
        
        Veiculo moto1 = new Moto("Ninja", 12000, "Kawasaki", 2024, "Verde", 
                                650, true, "Esportivo");
        Veiculo moto2 = new Moto("CG 160", 8000, "Honda", 2023, "Azul", 
                                160, false, "Convencional");
        
        veiculos.add(carro1);
        veiculos.add(carro2);
        veiculos.add(moto1);
        veiculos.add(moto2);
        
        System.out.println("Veículos de exemplo criados com sucesso!");
    }
    
    private static void selecionarEImprimirAtributos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo disponível.");
            return;
        }
        
        // Selecionar o primeiro veículo
        Veiculo selecionado = veiculos.get(0);
        
        System.out.println("=== ATRIBUTOS DA CLASSE VEÍCULO ===");
        System.out.println("Modelo: " + selecionado.getModelo());
        System.out.println("Fabricante: " + selecionado.getFabricante());
        System.out.println("Ano: " + selecionado.getAno());
        System.out.println("Cor: " + selecionado.getCor());
        System.out.println("Preço Base: " + selecionado.getPrecoBase());
        
        System.out.println("\n=== TODOS OS ATRIBUTOS ===");
        selecionado.imprimirDados();
        
        // Demonstração de desconto
        if (selecionado instanceof Desconto) {
            Desconto comDesconto = (Desconto) selecionado;
            System.out.println("Preço com 10% de desconto: " + comDesconto.aplicarDesconto(10));
        }
    }
    
    private static void filtrarCarros() {
        for (Veiculo veiculo : veiculos) {
            if (veiculo instanceof Carro) {
                veiculo.imprimirDados();
            }
        }
    }
    
    private static void armazenarEmBaseDados() {
        if (veiculos.size() >= 2) {
            ArmazenamentoDados.salvarVeiculos(veiculos.get(0), veiculos.get(1), ARQUIVO_DADOS);
        } else {
            System.out.println("Não há veículos suficientes para armazenar.");
        }
    }
    
    private static void demonstrarCarregamentoDados() {
        List<Veiculo> veiculosCarregados = ArmazenamentoDados.carregarVeiculos(ARQUIVO_DADOS);
        ArmazenamentoDados.exibirVeiculosCarregados(veiculosCarregados);
    }
    
    private static void criarInterfaceCadastro() {
        JFrame frame = new JFrame("Cadastro de Carro - Concessionária MM");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 2, 5, 5));
        
        // Campos de entrada
        JTextField txtModelo = new JTextField();
        JTextField txtPreco = new JTextField();
        JTextField txtFabricante = new JTextField();
        JTextField txtAno = new JTextField();
        JTextField txtCor = new JTextField();
        JCheckBox chkElectrico = new JCheckBox("Elétrico");
        JTextField txtPortas = new JTextField();
        JTextField txtCombustivel = new JTextField();
        
        // Adicionar componentes com bordas
        frame.add(criarLabel("Modelo:"));
        frame.add(txtModelo);
        frame.add(criarLabel("Preço Base:"));
        frame.add(txtPreco);
        frame.add(criarLabel("Fabricante:"));
        frame.add(txtFabricante);
        frame.add(criarLabel("Ano:"));
        frame.add(txtAno);
        frame.add(criarLabel("Cor:"));
        frame.add(txtCor);
        frame.add(criarLabel("Elétrico:"));
        frame.add(chkElectrico);
        frame.add(criarLabel("Nº Portas:"));
        frame.add(txtPortas);
        frame.add(criarLabel("Combustível:"));
        frame.add(txtCombustivel);
        
        // Botão de cadastro
        JButton btnCadastrar = new JButton("Cadastrar Carro");
        btnCadastrar.addActionListener(e -> cadastrarCarro(txtModelo, txtPreco, txtFabricante, 
                txtAno, txtCor, chkElectrico, txtPortas, txtCombustivel, frame));
        
        frame.add(new JLabel()); // Espaço vazio
        frame.add(btnCadastrar);
        
        frame.setVisible(true);
    }
    
    private static JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return label;
    }
    
    private static void cadastrarCarro(JTextField txtModelo, JTextField txtPreco, 
            JTextField txtFabricante, JTextField txtAno, JTextField txtCor, 
            JCheckBox chkElectrico, JTextField txtPortas, JTextField txtCombustivel, 
            JFrame frame) {
        
        try {
            String modelo = txtModelo.getText().trim();
            double preco = Double.parseDouble(txtPreco.getText().trim());
            String fabricante = txtFabricante.getText().trim();
            int ano = Integer.parseInt(txtAno.getText().trim());
            String cor = txtCor.getText().trim();
            boolean electrico = chkElectrico.isSelected();
            int portas = Integer.parseInt(txtPortas.getText().trim());
            String combustivel = txtCombustivel.getText().trim();
            
            if (modelo.isEmpty() || fabricante.isEmpty() || cor.isEmpty() || combustivel.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos!");
                return;
            }
            
            Carro novoCarro = new Carro(modelo, preco, fabricante, ano, cor,
                                      electrico, portas, combustivel);
            
            veiculos.add(novoCarro);
            JOptionPane.showMessageDialog(frame, "Carro cadastrado com sucesso!\n" +
                "Preço Final: " + novoCarro.calcularPrecoFinal());
            
            // Limpar campos
            limparCampos(txtModelo, txtPreco, txtFabricante, txtAno, txtCor, txtPortas, txtCombustivel, chkElectrico);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira valores numéricos válidos!");
        }
    }
    
    private static void limparCampos(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }
}