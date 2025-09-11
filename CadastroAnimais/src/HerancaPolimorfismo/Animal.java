/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HerancaPolimorfismo;

/**
 *
 * @author anicamassas
 */
import java.io.Serializable;

// Classe abstrata que serve de modelo.
// "implements Serializable" permite que objetos desta classe sejam salvos em ficheiros.
public abstract class Animal implements Serializable {
    protected String nome;
    protected int idade;

    public Animal(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    // Contrato: toda classe filha DEVE implementar este método.
    public abstract String fazerSom();

    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade;
    }
}

/*
Crie a superclasse Animal com atributos nome e idade e método abstrato fazerSom().
Crie as subclasses Cachorro e Gato.
Crie uma interface gráfica com Swing que permita:
o Inserir animais via formulário (JTextField, JComboBox)
o Gravar os animais em ficheiro de objetos (ObjectOutputStream)
o Listar os animais cadastrados em um JTable.
Use recursividade para percorrer a lista de animais e imprimir os sons.
*/