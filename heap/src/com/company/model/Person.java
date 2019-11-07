package com.company.model;

//https://www.baeldung.com/java-observer-pattern

public class Person{

    private String name;
    private int age;

    public Person(){
    }

    public Person(String name, int age) {
        this();
        this.name = name;
        this.age = age;
    }

    public int getIdade() {
        return age;
    }

    public void setIdade(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNome() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nIdade: " + age + "\n";
    }
}


