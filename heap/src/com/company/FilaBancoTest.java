package com.company;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class FilaBancoTest {

    FilaBanco fila;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        fila = new FilaBanco();
    }


    @Test
    public void mustBeOrdered() {

        Person people1 = new Person("Vovó", 65);
        Person people2 = new Person("Fulano",10);
        Person people3 = new Person("Sicrano",25);

        //Act
        fila.addPerson(people1);
        fila.addPerson(people2);
        fila.addPerson(people3);

        //Assert
        assertSame(people1, fila.peek());
        assertSame(people3, fila.peek());
        assertSame(people2, fila.peek());

        people2.setIdade(67);
        assertSame(people2, fila.peek());

        assertNull(fila.peek());


    }

    @Test
    public void peekMustReturnElementHighestPriority(){
        //
        Person people1 = new Person("Vovó", 65);
        Person people2 = new Person("Fulano",10);

        //Act
        fila.addPerson(people1);
        fila.addPerson(people2);

        assertSame(people1, fila.peek());
    }
}