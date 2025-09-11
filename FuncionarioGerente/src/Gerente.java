/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
public class Gerente extends Funcionario {
    public Gerente(String nome, double salario) {
        super(nome, salario);
    }
    
    public void aumentarSalario(double percentual) {
        double aumento = salario * percentual / 100;
        setSalario(salario + aumento);
    }
    
    @Override
    public String toString() {
        return "Gerente: " + super.toString();
    }
}