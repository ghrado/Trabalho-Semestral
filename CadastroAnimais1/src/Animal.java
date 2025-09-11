/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
import java.io.Serializable;

public abstract class Animal implements Serializable {
    protected String nome;
    protected int idade;
    
    public Animal(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    public abstract String fazerSom();
    
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    
    @Override
    public String toString() {
        return nome + " (" + idade + " anos)";
    }
}