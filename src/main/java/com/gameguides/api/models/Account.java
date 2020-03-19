package com.gameguides.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "id")
    private int id;

    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name= "client_id", unique = true)
    public UUID uuid;

    @Column(name = "username", unique = true)
    public String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "madeby")
    @JsonBackReference
    public List<LolGuide> lolGuides;

}
