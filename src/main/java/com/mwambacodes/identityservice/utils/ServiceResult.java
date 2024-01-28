package com.mwambacodes.identityservice.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResult {

    Boolean status;
    String message;
    Integer code;
    Object data;

}
