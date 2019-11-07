package br.com.waldson.aula15;

import java.io.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        AvlTree<Pessoa> tree = new AvlTree<>();
        AvlTree<Time> teamTree = new AvlTree<>();

        long begin = 0;
        long end = 0;
        long demora = 0;

        File auxFile1 = new File("/home/francisco/AVL_insert.csv");
        try {
            auxFile1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File auxFile2 = new File("/home/francisco/AVL_search.csv");
        try {
            auxFile2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        File auxFile3 = new File("/home/francisco/AVL_remove.csv");
        try {
            auxFile3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int contador = 1; contador <= 1000000; contador = contador * 10){

            FileWriter file;

            begin = System.currentTimeMillis();

            for (int i = 0; i < contador; i++){
                tree.insert(new Pessoa(i));
            }

            end = System.currentTimeMillis();
            demora = end - begin;

            try {
                file = new FileWriter(auxFile1, true);
                file.write(contador+";"+demora+";\n");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            begin = System.currentTimeMillis();

            for (int i = contador-1; i > -1; i++){
                tree.search(i);
            }

            end = System.currentTimeMillis();
            demora = end - begin;

            try {
                file = new FileWriter(auxFile2, true);
                file.write(contador+";"+demora+";\n");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            begin = System.currentTimeMillis();

            for (int i = contador-1; i > -1; i++){
                tree.remove(i);
            }

            end = System.currentTimeMillis();
            demora = end - begin;

            try {
                file = new FileWriter(auxFile3, true);
                file.write(contador+";"+demora+";\n");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Funciona!!!");

        }

    }
}
