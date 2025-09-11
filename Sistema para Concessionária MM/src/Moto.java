/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
public class Moto extends Veiculo implements Desconto {
    private int cilindrada;
    private boolean partidaEletrica;
    private String tipoGuidao;
    
    public Moto(String modelo, double precoBase, String fabricante, int ano, String cor,
               int cilindrada, boolean partidaEletrica, String tipoGuidao) {
        super(modelo, precoBase, fabricante, ano, cor);
        this.cilindrada = cilindrada;
        this.partidaEletrica = partidaEletrica;
        this.tipoGuidao = tipoGuidao;
    }
    
    @Override
    public double calcularPrecoFinal() {
        double precoFinal = precoBase;
        if (cilindrada > 600) {
            precoFinal += precoBase * 0.05;
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
        System.out.println("Tipo: Moto");
        System.out.println("Cilindrada: " + cilindrada + "cc");
        System.out.println("Partida Elétrica: " + (partidaEletrica ? "Sim" : "Não"));
        System.out.println("Tipo de Guidão: " + tipoGuidao);
        System.out.println("-----------------------------");
    }
    
    public int getCilindrada() { return cilindrada; }
    public boolean isPartidaEletrica() { return partidaEletrica; }
    public String getTipoGuidao() { return tipoGuidao; }
}