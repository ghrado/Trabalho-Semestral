/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HerancaPolimorfismo;

/**
 *
 * @author anicamassas
 */
// Cachorro "é um" Animal, por isso usa "extends".
public class Cachorro extends Animal {
    public Cachorro(String nome, int idade) {
        super(nome, idade); // Chama o construtor da classe mãe (Animal)
    }
    
    

    // Implementação obrigatória do método abstrato.
    @Override
    public String fazerSom() {
        return getNome() + " faz: Au au!";
    }

    private String getNome() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
