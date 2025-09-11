/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
public class Bicicleta extends Veiculo {
    public Bicicleta(String marca, String modelo) {
        super(marca, modelo);
    }
    
    @Override
    public String mover() {
        return "Bicicleta " + toString() + " está pedalando...";
    }
}