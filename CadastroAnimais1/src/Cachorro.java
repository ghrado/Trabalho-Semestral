/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
public class Cachorro extends Animal {
    public Cachorro(String nome, int idade) {
        super(nome, idade);
    }
    
    @Override
    public String fazerSom() {
        return "Au Au!";
    }
    
    @Override
    public String toString() {
        return "Cachorro: " + super.toString();
    }
}