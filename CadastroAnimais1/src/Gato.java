/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
public class Gato extends Animal {
    public Gato(String nome, int idade) {
        super(nome, idade);
    }
    
    @Override
    public String fazerSom() {
        return "Miau!";
    }
    
    @Override
    public String toString() {
        return "Gato: " + super.toString();
    }
}