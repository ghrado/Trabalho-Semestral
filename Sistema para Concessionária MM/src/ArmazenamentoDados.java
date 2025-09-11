/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArmazenamentoDados {
    
    /**
     * Salva uma lista de veículos em arquivo
     */
    public static void salvarVeiculos(List<Veiculo> veiculos, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
             new FileOutputStream(nomeArquivo))) {
            
            oos.writeObject(veiculos);
            System.out.println("Veículos salvos em " + nomeArquivo + " com sucesso!");
            
        } catch (IOException e) {
            System.out.println("Erro ao salvar veículos: " + e.getMessage());
        }
    }
    
    /**
     * Salva veículos individuais em arquivo
     */
    public static void salvarVeiculos(Veiculo v1, Veiculo v2, String nomeArquivo) {
        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(v1);
        veiculos.add(v2);
        salvarVeiculos(veiculos, nomeArquivo);
    }
    
    /**
     * Carrega veículos do arquivo
     */
    public static List<Veiculo> carregarVeiculos(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(
             new FileInputStream(nomeArquivo))) {
            
            List<Veiculo> veiculos = (List<Veiculo>) ois.readObject();
            System.out.println("Veículos carregados de " + nomeArquivo + " com sucesso!");
            return veiculos;
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar veículos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Exibe todos os veículos carregados
     */
    public static void exibirVeiculosCarregados(List<Veiculo> veiculos) {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo carregado.");
            return;
        }
        
        System.out.println("\n=== VEÍCULOS CARREGADOS ===");
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println("Veículo " + (i + 1) + ":");
            veiculos.get(i).imprimirDados();
        }
    }
}