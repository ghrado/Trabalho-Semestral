/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hibernateModel;

/**
 *
 * @author massas
 */
public class Pessoa {
    private Integer id;
    private String nome;
    private Integer Idade;

    public Pessoa(Integer id, String nome, Integer Idade) {
        this.id = id;
        this.nome = nome;
        this.Idade = Idade;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
   
    
    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return Idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(Integer Idade) {
        this.Idade = Idade;
    }
    
    
}
