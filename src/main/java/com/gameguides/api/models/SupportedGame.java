package com.gameguides.api.models;

import javax.persistence.*;

@Entity
@Table(name = "supportedgames")
public class SupportedGame {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    public String name;

    @Column(name = "shorthand")
    public String shorthand;

}
