package com.mahsan.messenger.dto;

import io.swagger.annotations.ApiModelProperty;

public class MessageDTO {



    @ApiModelProperty(required = false, hidden = true)
    private Long id;

    @ApiModelProperty(required = true, hidden = false)
    private Long fromId;

    @ApiModelProperty(required = true, hidden = false)
    private Long toId;

     @ApiModelProperty(required = true, hidden = false)
    private String text;

    @ApiModelProperty(required = false, hidden = true)
    private String createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
