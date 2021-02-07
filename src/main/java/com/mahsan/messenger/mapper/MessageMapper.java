package com.mahsan.messenger.mapper;


import com.mahsan.messenger.dto.MessageDTO;
import com.mahsan.messenger.dto.UserDTO;
import com.mahsan.messenger.entity.Message;
import com.mahsan.messenger.entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(source = "createdDate", dateFormat = "dd.MM.yyyy",target = "createdDate")
    MessageDTO toMessageDTO(Message message);

    @IterableMapping(qualifiedByName="mapWithoutIdsData")
    List<MessageDTO> toMessageDTOsWithoutIds(List<Message> messages);


     @Mapping(source = "createdDate", dateFormat = " yyyy.MM.dd hh:mm:ss",target = "createdDate")
     @Mapping(target = "fromId", ignore = true)
     @Mapping(target = "toId", ignore = true)
     @Named("mapWithoutIdsData")
     MessageDTO toMessageDtoWithoutIds(Message message);










    Message toMessage(MessageDTO messageDTO);

    List<MessageDTO> toMessageDTOs(List<Message> messages);



    List<Message> toMessages(List<MessageDTO> messageDTOS);

}
