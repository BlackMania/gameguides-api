package com.gameguides.api.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AccountReferenceDTO {
    public UUID uuid;
    public String username;
}
