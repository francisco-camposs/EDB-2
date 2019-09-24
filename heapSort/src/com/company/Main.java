package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
    int[] numeros = new int[20];
    Random rand = new Random();

    for (int i = 0; i < 20; i++){
        numeros[i] = rand.nextInt();
    }

    Sorter sort = new Sorter();
    sort.heapSort(numeros);



    }
}
