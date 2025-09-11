/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
public class Carro extends Veiculo implements Desconto {
    private boolean electrico;
    private int numeroPortas;
    private String tipoCombustivel;
    
    public Carro(String modelo, double precoBase, String fabricante, int ano, String cor,
                boolean electrico, int numeroPortas, String tipoCombustivel) {
        super(modelo, precoBase, fabricante, ano, cor);
        this.electrico = electrico;
        this.numeroPortas = numeroPortas;
        this.tipoCombustivel = tipoCombustivel;
    }
    
    @Override
    public double calcularPrecoFinal() {
        double precoFinal = precoBase;
        if (electrico) {
            precoFinal += precoBase * 0.12;
        }
        return precoFinal;
    }
    
    @Override
    public double aplicarDesconto(double percentual) {
        double precoFinal = calcularPrecoFinal();
        return precoFinal - (precoFinal * percentual / 100);
    }
    
    @Override
    public void imprimirDados() {
        super.imprimirDados();
        System.out.println("Tipo: Carro");
        System.out.println("Elétrico: " + (electrico ? "Sim" : "Não"));
        System.out.println("Número de Portas: " + numeroPortas);
        System.out.println("Tipo de Combustível: " + tipoCombustivel);
        System.out.println("-----------------------------");
    }
    
    public boolean isElectrico() { return electrico; }
    public int getNumeroPortas() { return numeroPortas; }
    public String getTipoCombustivel() { return tipoCombustivel; }
}