package com.mwambacodes.identityservice.modules.Domain.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDomainRequest {

    private String name;
    private String url;
    private String administratorEmail;
    @Builder.Default
    private String errorMessage = null;
    private HashMap<String,String> errors;


    public Boolean validate() {
        int error_count = 0;

        if (name.isEmpty()) {
            error_count++;
            errors.put("name","Name should not be empty");
        }

        if (name.isEmpty()) {
            error_count++;
            errors.put("url", "URL should not be empty");
        }

        if (administratorEmail.isEmpty()) {
            error_count++;
            errors.put("url", "Administrator Email should not be empty");
        }

        if (error_count > 0) {
            errorMessage = "Validation Error";
            return false;
        }

        return true;
    }
}
