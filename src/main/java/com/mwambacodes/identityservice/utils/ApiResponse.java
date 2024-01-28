package com.mwambacodes.identityservice.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private Date startProcessing;
    private Date finishProcessing;
    private Boolean status;
    private String message;
    private Integer code;
    private Object data;

}
