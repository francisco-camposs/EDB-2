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
        int valor = 0;
        try {
             Pessoa person = ((Pessoa)o);
        } catch (ClassCastException ex){
            System.out.println("Fodeu");
            return 0;
        }
        return idade - valor;
    }

    public int compareTo(Pessoa o) {
        return idade - o.idade;
    }

    @Override
    public String toString() {
        return "Idade: "+idade+"; \nNome: "+nome+"\n";
    }
}
