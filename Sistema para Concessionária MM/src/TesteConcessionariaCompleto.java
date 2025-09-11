/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
public class TesteConcessionariaCompleto {
    public static void main(String[] args) {
        System.out.println("=== TESTE COMPLETO DA CONCESSIONÁRIA MM ===");
        
        // Teste básico
        ConcessionariaMM.main(new String[]{});
        
        // Teste adicional da interface Desconto
        System.out.println("\n=== TESTE ADICIONAL DA INTERFACE DESCONTO ===");
        
        Desconto carroTeste = new Carro("Teste", 10000, "Teste", 2024, "Teste", 
                                      false, 4, "Gasolina");
        
        System.out.println("Preço Original: " + ((Veiculo)carroTeste).calcularPrecoFinal());
        System.out.println("Preço com 20% desconto: " + carroTeste.aplicarDesconto(20));
    }
}