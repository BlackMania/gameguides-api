package com.gameguides.api.models;

import javax.persistence.*;

@Entity
@Table(name = "lol_guides")
public class Guide {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "gameid")
    private SupportedGame game;

    @Column(name = "title")
    public String title;

    @Column(name = "content")
    public String content;

    @Column(name = "champion")
    public String champion;

}