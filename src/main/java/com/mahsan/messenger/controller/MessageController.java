package com.mahsan.messenger.controller;


import com.mahsan.messenger.dto.MessageDTO;
import com.mahsan.messenger.dto.PageDTO;
import com.mahsan.messenger.dto.UserDTO;
import com.mahsan.messenger.entity.Message;
import com.mahsan.messenger.entity.User;
import com.mahsan.messenger.mapper.MessageMapper;
import com.mahsan.messenger.mapper.UserMapper;
import com.mahsan.messenger.service.MessageService;
import com.mahsan.messenger.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {


    private MessageMapper messageMapper;
    private MessageService messageService;

    @Autowired
    public MessageController(MessageMapper messageMapper, MessageService messageService) {
        this.messageMapper=messageMapper;
        this.messageService=messageService;
    }

    @ApiOperation(value = "Add new message")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Added successfully"),
            @ApiResponse(code = 401, message = "You are not authorized"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PutMapping
    public ResponseEntity<Void> addNewMessage(@Valid @RequestBody MessageDTO messageDTO) {

        Message message=messageMapper.toMessage(messageDTO);
        messageService.saveNewMessage(message);
        return ResponseEntity.status(HttpsURLConnection.HTTP_CREATED).build();
    }

    @ApiOperation(value = "get history message")
    @GetMapping("/{fromId}/{toId}/{page}/{size}")
    public ResponseEntity<PageDTO> getAllMessageWithPagination(@PathVariable(value = "fromId") Long fromId,@PathVariable(value = "toId") Long toId,@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {

        Page<Message> messagePage = messageService.getMessageByContact(page,size,fromId,toId);

        List<MessageDTO> messageDTOList=messageMapper.toMessageDTOsWithoutIds(messagePage.getContent());



        PageDTO pageDTO=new PageDTO();
        pageDTO.setCurrentPage(messagePage.getNumber());
        pageDTO.setPageSize(size);
        pageDTO.setTotalPage(messagePage.getTotalPages());
        pageDTO.setList(messageDTOList);


        return  ResponseEntity.ok(pageDTO);

    }

     @GetMapping("/byPhone/{fromPhone}/{toPhone}/{page}/{size}")
    public ResponseEntity<PageDTO> getAllMessageWithPaginationByPhone(@PathVariable(value = "fromPhone") String fromPhone,@PathVariable(value = "toPhone") String toPhone,@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {

        Page<Message> messagePage = messageService.getMessageByContactPhone(page,size,fromPhone,toPhone);

        List<MessageDTO> messageDTOList=messageMapper.toMessageDTOsWithoutIds(messagePage.getContent());



        PageDTO pageDTO=new PageDTO();
        pageDTO.setCurrentPage(messagePage.getNumber());
        pageDTO.setPageSize(size);
        pageDTO.setTotalPage(messagePage.getTotalPages());
        pageDTO.setList(messageDTOList);

        return  ResponseEntity.ok(pageDTO);

    }


}
