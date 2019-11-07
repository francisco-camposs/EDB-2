package com.company.model;

public interface Observable {

    void addObserver(Observer observer);

    void notifyObserver();

    void updateObserver();

}
