package com.gameguides.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name= "client_id", unique = true)
    public UUID uuid;

    @Column(name = "username", unique = true)
    public String username;

    @Column(name = "password")
    public String password;

    @Column(name = "email")
    public String email;

    @OneToMany(mappedBy = "madeby")
    @JsonBackReference
    public List<LolGuide> lolGuides;

}
