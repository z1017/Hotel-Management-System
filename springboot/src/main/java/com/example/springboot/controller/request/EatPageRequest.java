package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class EatPageRequest extends BaseRequest{
    private String name;
    private String eatNo;

}
