package br.com.company;

public class Pessoa implements Comparable{

    int idade;
    String nome;

    public Pessoa(int idade, String nome) {
        this.idade = idade;
        this.nome = nome;
    }

    @Override
    public int compareTo(Object o) {
        return idade - ((Pessoa)o).idade;
    }

    @Override
    public String toString() {
        return "Idade: "+idade+"; \nNome: "+nome+"\n";
    }
}
