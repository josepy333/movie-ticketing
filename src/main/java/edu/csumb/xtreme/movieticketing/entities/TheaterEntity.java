package edu.csumb.xtreme.movieticketing.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TheaterEntity {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int capacity;
}
