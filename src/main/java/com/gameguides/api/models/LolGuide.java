package com.gameguides.api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.json.JSONObject;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)

@Entity
@Table(name = "lol_guides")
public class LolGuide {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name= "uuid", unique = true)
    public UUID uuid;

    @Column(name = "patchversion")
    public String patchversion;

    @Type( type = "json" )
    @Column(name = "skills")
    public String[] skills;

    @Type( type = "json" )
    @Column(name = "runeset")
    public Object runeset;

    @Column(name = "title")
    public String title;

    @Column(name = "content")
    public String content;

    @Column(name = "champion")
    public String champion;

    @Column(name = "lane")
    public String lane;

    @ManyToOne
    @JoinColumn(name="madeby")
    @JsonManagedReference
    public Account madeby;

    @Column(name = "views")
    public int views;

    @Column(name = "upvotes")
    public int upvotes;

    @Column(name = "created")
    public Timestamp created;

    @Column(name = "updated")
    public Timestamp updates;

}