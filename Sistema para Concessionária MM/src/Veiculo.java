/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
import java.io.Serializable;

public abstract class Veiculo implements Serializable {
    protected String modelo;
    protected double precoBase;
    protected String fabricante;
    protected int ano;
    protected String cor;
    
    public Veiculo(String modelo, double precoBase, String fabricante, int ano, String cor) {
        this.modelo = modelo;
        this.precoBase = precoBase;
        this.fabricante = fabricante;
        this.ano = ano;
        this.cor = cor;
    }
    
    public abstract double calcularPrecoFinal();
    
    public void imprimirDados() {
        System.out.println("Modelo: " + modelo);
        System.out.println("Fabricante: " + fabricante);
        System.out.println("Ano: " + ano);
        System.out.println("Cor: " + cor);
        System.out.println("Preço Base: " + precoBase);
        System.out.println("Preço Final: " + calcularPrecoFinal());
    }
    
    public String getModelo() { return modelo; }
    public double getPrecoBase() { return precoBase; }
    public String getFabricante() { return fabricante; }
    public int getAno() { return ano; }
    public String getCor() { return cor; }
    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }
}