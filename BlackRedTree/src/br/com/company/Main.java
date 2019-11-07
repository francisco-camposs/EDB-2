package br.com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        RedBlackTree<Pessoa> pessoa = new RedBlackTree<>();
        pessoa.insert(new Pessoa(13, "Pedro"));
        pessoa.insert(new Pessoa(23, "Marcos"));
        pessoa.insert(new Pessoa(56, "Mia"));
        pessoa.insert(new Pessoa(98, "Shun"));
        pessoa.insert(new Pessoa(0, "Leo"));
        pessoa.insert(new Pessoa(2, "Ikki"));
        pessoa.insert(new Pessoa(3, "Goku"));
        pessoa.insert(new Pessoa(7, "Lee"));
        pessoa.insert(new Pessoa(9, "Mai"));

        pessoa.printAll();

    }
}
