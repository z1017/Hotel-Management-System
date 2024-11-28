package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class EatReservePageRequest extends BaseRequest{
    private String eatName;
    private String eatNo;
    private String userName;
}
