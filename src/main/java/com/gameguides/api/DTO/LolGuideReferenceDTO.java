package com.gameguides.api.DTO;

import java.sql.Timestamp;
import java.util.UUID;

public class LolGuideReferenceDTO {
    public UUID uuid;

    public String patchversion;

    public String[] skills;

    public Object runeset;

    public String title;

    public String content;

    public String champion;

    public String lane;

    public int views;

    public int upvotes;

    public Timestamp created;

    public Timestamp updates;
}
