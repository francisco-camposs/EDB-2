package com.company;

import com.company.arvoreBuscaBinaria.ArvoreBuscaBinaria;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Random rand = new Random();
        ArvoreBuscaBinaria tree = new ArvoreBuscaBinaria();

        tree.remove(10);

        tree.insert(50);

        for (int i = 0; i < 15; i++){
            tree.insert(Math.abs(rand.nextInt())%100);
        }

        tree.insert(36);
        tree.viewTree();
        tree.remove(50);
        System.out.println("");
        tree.viewTree();
    }
}
