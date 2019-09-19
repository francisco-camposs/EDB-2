package com.company;

public class Main {

    public static void main(String[] args) {
        FilaBanco fila = new FilaBanco();
        fila.addPerson("Fulano",20);
        fila.addPerson("Sicrano",10);
        fila.addPerson("Beltrano",64);
        fila.addPerson("Entrano",50);
        while (fila.getSize() != 0){
            Person p = fila.peek();
            System.out.println(p.getNome() + " está sendo atendido.");
            fila.remove();
        }
    }
}
