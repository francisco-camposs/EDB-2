package com.company;

public class Sorter {
    public void heapSort(int[] numeros) {
        for (int i = numeros.length / 2; i >= 0; --i){
            heapIfDown(numeros,i,numeros.length);
        }

        // números virou uma heap
        for(int i = numeros.length - 1; i > 0; --i){
            int tmp = numeros[0];
            numeros[0] = numeros[i];
            numeros[i] = tmp;
            heapIfDown(numeros, 0, i);
        }
    }

    private void heapIfDown(int[] numeros, int index, int length) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        int maxIndex = -1;

        if (leftChild < length && numeros[leftChild] > numeros[index]){
            maxIndex = leftChild;
        }
        if (rightChild < length && numeros[rightChild] > numeros[maxIndex]){
            maxIndex = rightChild;
        }

        if (maxIndex != index){
            int tmp = numeros[maxIndex];
            numeros[maxIndex] = numeros[index];
            numeros[index] = tmp;
            heapIfDown(numeros, maxIndex, length);
        }

    }
}
