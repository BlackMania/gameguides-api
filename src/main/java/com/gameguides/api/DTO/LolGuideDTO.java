package com.gameguides.api.DTO;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class LolGuideDTO {
    public UUID uuid;

    public String patchversion;

    public String[] skills;

    public Object runeset;

    public String title;

    public String content;

    public String champion;

    public String lane;

    public AccountReferenceDTO madeby;

    public int views;

    public int upvotes;

    public Timestamp created;

    public Timestamp updates;
}
