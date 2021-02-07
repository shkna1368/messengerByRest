package com.mahsan.messenger.controller;


import com.mahsan.messenger.dto.PageDTO;
import com.mahsan.messenger.dto.UserDTO;
import com.mahsan.messenger.entity.User;
import com.mahsan.messenger.mapper.UserMapper;
import com.mahsan.messenger.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    private UserMapper userMapper;
    private UserService userService;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper=userMapper;
        this.userService=userService;
    }

    @ApiOperation(value = "Add new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Added successfully"),
            @ApiResponse(code = 401, message = "You are not authorized"),
            @ApiResponse(code = 409, message = "It is duplicate"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PutMapping
    public ResponseEntity<Void> addNewUser(@Valid @RequestBody UserDTO userDTO) {

        User user=userMapper.toUser(userDTO);
        userService.addNewUser(user);
        return ResponseEntity.status(HttpsURLConnection.HTTP_CREATED).build();
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<PageDTO> getAllWithPagination(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {

        Page<User> usersPage = userService.findAll(size,page);

        List<UserDTO> userDTOList=userMapper.toUserDTOs(usersPage.getContent());



        PageDTO pageDTO=new PageDTO();
        pageDTO.setCurrentPage(usersPage.getNumber());
        pageDTO.setPageSize(size);
        pageDTO.setTotalPage(usersPage.getTotalPages());
        pageDTO.setList(userDTOList);


        return  ResponseEntity.ok(pageDTO);

    }
}
