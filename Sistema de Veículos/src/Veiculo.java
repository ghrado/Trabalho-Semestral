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
    protected String marca;
    protected String modelo;
    
    public Veiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }
    
    public abstract String mover();
    
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    
    @Override
    public String toString() {
        return marca + " " + modelo;
    }
}