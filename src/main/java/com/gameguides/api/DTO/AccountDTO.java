package com.gameguides.api.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AccountDTO {
    public UUID uuid;
    public String username;
    public List<LolGuideReferenceDTO> guides;
}
