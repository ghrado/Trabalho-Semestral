/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anicamassas
 */
import java.util.Stack;

public class TesteExercicio3 {
    
    /**
     * Método recursivo que calcula o produto de todos os elementos de uma pilha
     * @param pilha - Stack<Integer> com os números
     * @return produto de todos os elementos da pilha
     */
    public static int produtoPilhaRecursivo(Stack<Integer> pilha) {
        // Caso base: pilha vazia retorna 1 (elemento neutro da multiplicação)
        if (pilha.isEmpty()) {
            return 1;
        }
        
        // Remove o elemento do topo e chama recursivamente
        int elemento = pilha.pop();
        int produto = elemento * produtoPilhaRecursivo(pilha);
        
        // Restaura a pilha (opcional)
        pilha.push(elemento);
        
        return produto;
    }
    
    // Versão alternativa que não modifica a pilha original
    public static int produtoPilhaRecursivoCopia(Stack<Integer> pilhaOriginal) {
        // Cria uma cópia da pilha para trabalhar
        Stack<Integer> pilha = new Stack<>();
        pilha.addAll(pilhaOriginal);
        return produtoPilhaRecursivoHelper(pilha);
    }
    
    private static int produtoPilhaRecursivoHelper(Stack<Integer> pilha) {
        if (pilha.isEmpty()) {
            return 1;
        }
        return pilha.pop() * produtoPilhaRecursivoHelper(pilha);
    }
    
    // Testes
    public static void main(String[] args) {
        System.out.println("=== TESTE DO EXERCÍCIO 3 ===");
        
        // Teste 1: Pilha com números positivos
        Stack<Integer> pilha1 = new Stack<>();
        pilha1.push(2);
        pilha1.push(3);
        pilha1.push(4);
        
        System.out.println("Pilha 1: " + pilha1);
        System.out.println("Produto (com cópia): " + produtoPilhaRecursivoCopia(pilha1));
        System.out.println("Pilha original intacta: " + pilha1);
        
        // Teste 2: Pilha com zero
        Stack<Integer> pilha2 = new Stack<>();
        pilha2.push(5);
        pilha2.push(0);
        pilha2.push(7);
        
        System.out.println("\nPilha 2: " + pilha2);
        System.out.println("Produto (com cópia): " + produtoPilhaRecursivoCopia(pilha2));
        
        // Teste 3: Pilha vazia
        Stack<Integer> pilha3 = new Stack<>();
        
        System.out.println("\nPilha 3: " + pilha3);
        System.out.println("Produto (com cópia): " + produtoPilhaRecursivoCopia(pilha3));
        
        // Teste 4: Pilha com números negativos
        Stack<Integer> pilha4 = new Stack<>();
        pilha4.push(-2);
        pilha4.push(3);
        pilha4.push(-1);
        
        System.out.println("\nPilha 4: " + pilha4);
        System.out.println("Produto (com cópia): " + produtoPilhaRecursivoCopia(pilha4));
        
        // Teste 5: Pilha com um único elemento
        Stack<Integer> pilha5 = new Stack<>();
        pilha5.push(10);
        
        System.out.println("\nPilha 5: " + pilha5);
        System.out.println("Produto (com cópia): " + produtoPilhaRecursivoCopia(pilha5));
    }
}