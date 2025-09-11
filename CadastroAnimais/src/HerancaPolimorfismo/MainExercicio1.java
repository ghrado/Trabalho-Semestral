/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HerancaPolimorfismo;

/**
 *
 * @author anicamassas
 */
import java.util.ArrayList;
import java.util.List;

public class MainExercicio1 {

    // Método recursivo para imprimir os sons.
    // Recebe a lista de animais e o índice do elemento atual.
    public static void imprimirSonsRecursivo(List<Animal> animais, int index) {
        // Condição de parada: se o índice for igual ao tamanho da lista, a recursão para.
        if (index >= animais.size()) {
            return;
        }

        // Ação: Pega o animal atual, imprime seu som.
        Animal animalAtual = animais.get(index);
        System.out.println(animalAtual.fazerSom()); // Polimorfismo em ação!

        // Chamada recursiva: chama o mesmo método para o próximo índice.
        imprimirSonsRecursivo(animais, index + 1);
    }

    public static void main(String[] args) {
        // 1. Criamos uma lista que pode conter qualquer objeto que seja um "Animal".
        List<Animal> listaDeAnimais = new ArrayList<>();

        // 2. Adicionamos objetos das classes filhas (concretas).
        listaDeAnimais.add(new Cachorro("Bobi", 5));
        listaDeAnimais.add(new Gato("Mimi", 3));
        listaDeAnimais.add(new Cachorro("Rex", 2));

        System.out.println("--- Listando sons dos animais de forma recursiva ---");
        
        // 3. Iniciamos a chamada recursiva a partir do primeiro elemento (índice 0).
        imprimirSonsRecursivo(listaDeAnimais, 0);
    }
}
