package com.hi.mvc2basic.rabbitmq;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageDTO {
    private String title;
    private String content;
}
