/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
public class Carro extends Veiculo {
    public Carro(String marca, String modelo) {
        super(marca, modelo);
    }
    
    @Override
    public String mover() {
        return "Carro " + toString() + " está acelerando...";
    }
}