/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
public class Aluno extends Pessoa {
    private String matricula;
    
    public Aluno(String nome, int idade, String matricula) {
        super(nome, idade);
        this.matricula = matricula;
    }
    
    public String getMatricula() { return matricula; }
    
    @Override
    public String toString() {
        return super.toString() + " - Matrícula: " + matricula;
    }
}