/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
import java.io.Serializable;

public class Funcionario implements Serializable {
    protected String nome;
    protected double salario;
    
    public Funcionario(String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }
    
    public String getNome() { return nome; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    
    @Override
    public String toString() {
        return nome + " - Salário: " + salario;
    }
}