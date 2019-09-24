package com.company;

//https://www.baeldung.com/java-observer-pattern


import jdk.jfr.Event;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;

public class Person{
    private PropertyChangeSupport support;
    private String name;
    private int age;

    public Person() {
        support = new PropertyChangeSupport(this);
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

    public PropertyChangeSupport getSuport() {
        return support;
    }

    public void setSuport(PropertyChangeSupport support) {
        this.support = support;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setNews(String value) {
        support.firePropertyChange("news", this.news, value);
    }

}


