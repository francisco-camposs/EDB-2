package com.company.model;

import java.util.Arrays;

public class FilaBanco {

    private Person[] persons;

    // Number of elements.
    private int size;

    // Max number of elements.
    private int capacity;


    public FilaBanco() {
        this(10);
    }

    public FilaBanco(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        persons = new Person[capacity];
    }

    public void addPerson(String people, int age) {
        Person person = new Person(people, age);
        addPerson(new Person(people, age));
    }

    public void addPerson(Person people){
        this.ensureCapacity();
        this.persons[getSize()] = people;
        System.out.println("Aqui");
        heapifyUp(getSize());
        size+= 1;
    }

    private void heapifyUp(int index){
        if (!(hasParent(index))){
            return;
        }
        int parentIndex = getParentIndex(index);
        Person node = persons[index];
        Person pai = persons[parentIndex];

        if (node.getIdade() > pai.getIdade()){
            persons[index] = pai;
            persons[parentIndex] = node;
            heapifyUp(parentIndex);
        }
    }

    private boolean hasParent(int index){
        return getParentIndex(index) >= 0 && getParentIndex(index) < size;
    }

    private int getParentIndex(int index){
        return (int) Math.floor((index -1) / 2);
    }

    private void ensureCapacity() {
        if (getSize() == capacity){
            this.persons = Arrays.copyOf(this.persons, getSize()*2);
            capacity = getSize() * 2;
        }
    }

    public int getSize() {
        return size;
    }

    public void remove() {
        persons[0] = persons[getSize()-1];
        persons[getSize() -1] = null;
        size--;
        heapifyDown(0);
    }

    private void heapifyDown(int index){
        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;

        int childIndex = -1;

        if (leftChild < getSize()){
            childIndex = leftChild;
        }

        if (childIndex < 0){
            return;
        }

        if (rightChild < getSize()){
            if (persons[rightChild].getIdade() > persons[leftChild].getIdade()){
                childIndex = rightChild;
            }
        }

        if (persons[index].getIdade() < persons[childIndex].getIdade()){
            Person tmp = persons[index];
            persons[index] = persons[childIndex];
            persons[childIndex] = tmp;
            heapifyDown(childIndex);
        }
    }

    public Person peek() {
        if (getSize() == 0){
            return null;
        }
        return persons[0];
    }

    public Person[] heapSort(){
        FilaBanco fila = new FilaBanco();
        fila.persons = Arrays.copyOfRange(this.persons, 0, this.size);
        fila.size = size;
        fila.capacity = capacity;
        return fila.heap();
    }

    private Person[] heap(){
        if (size != 1){
            Person aux;
            aux = persons[getSize() - 1];
            persons[getSize() - 1] = persons[0];
            persons[0] = aux;
            size--;
            heapifyDown(0);
            return heap();
        } else {
            return persons;
        }
    }

    @Override
    public String toString() {
        String finall = "";
        for (Person value: persons) {
            finall = finall + value.toString();
        }
        return finall;
    }
}
