package com.mahsan.messenger.mapper;


import com.mahsan.messenger.dto.UserDTO;
import com.mahsan.messenger.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);

    List<UserDTO> toUserDTOs(List<User> users);
    List<User> toUsers(List<UserDTO> userDTOS);

}
