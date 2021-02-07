package com.mahsan.messenger.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {



    @ApiModelProperty(required = false, hidden = true)
    private Long id;

    @NotEmpty(message = "userName may not be empty")
    @NotBlank(message = "userName may not be blank")
    @NotNull(message = "userName can not be null!")
    @ApiModelProperty(required = true, hidden = false)
    private String userName;


    @NotEmpty(message = "phone may not be empty")
    @NotBlank(message = "phone may not be blank")
    @NotNull(message = "phone can not be null!")
     @ApiModelProperty(required = true, hidden = false)
     private String phone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
