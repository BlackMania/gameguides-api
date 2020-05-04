package com.gameguides.api.services.utils;

import com.gameguides.api.DTO.AccountDTO;
import com.gameguides.api.DTO.LolGuideDTO;
import com.gameguides.api.models.Account;
import com.gameguides.api.models.LolGuide;
import org.modelmapper.ModelMapper;

public class DTOConverter {
    private static ModelMapper modelMapper = new ModelMapper();

    public static LolGuideDTO convertToLolGuideDTO(LolGuide guide)
    {
        return modelMapper.map(guide, LolGuideDTO.class);
    }

    public static AccountDTO convertToAccountDTO(Account account)
    {
        return modelMapper.map(account, AccountDTO.class);
    }
}
