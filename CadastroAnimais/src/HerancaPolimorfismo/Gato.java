/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HerancaPolimorfismo;

/**
 *
 * @author anicamassas
 */
// Gato também "é um" Animal.
public class Gato extends Animal {
    public Gato(String nome, int idade) {
        super(nome, idade);
    }
    
    // Implementação própria do método abstrato.
    @Override
    public String fazerSom() {
        return getNome() + " faz: Miau!";
    }

    private String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
