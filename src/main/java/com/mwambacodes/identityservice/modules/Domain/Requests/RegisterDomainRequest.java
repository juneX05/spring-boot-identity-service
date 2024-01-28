package com.mwambacodes.identityservice.modules.Domain.Requests;

import com.mwambacodes.identityservice.modules.Domain.DomainRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDomainRequest {

    private String name;
    private String url;
    private String administratorEmail;
    @Builder.Default
    private String errorMessage = null;
    @Builder.Default
    private HashMap<String,String> errors = new HashMap<>();


    public Boolean validate(DomainRepository domainRepository) {
        int error_count = 0;

        if (name == null || name.isEmpty()) {
            error_count++;
            errors.put("name","Name should not be empty");
        } else {
            if (domainRepository.findByName(name).isPresent()) {
                error_count++;
                errors.put("name", "Name already exists");
            }
        }

        if (url == null || url.isEmpty()) {
            error_count++;
            errors.put("url", "URL should not be empty");
        } else {
            if (domainRepository.findByUrl(url).isPresent()) {
                error_count++;
                errors.put("url", "URL already exists");
            }
        }

        if (administratorEmail == null || administratorEmail.isEmpty()) {
            error_count++;
            errors.put("administratorEmail", "Administrator Email should not be empty");
        } else {
            if (domainRepository.findByAdministratorEmail(administratorEmail).isPresent()) {
                error_count++;
                errors.put("administratorEmail", "Administrator Email already exists");
            }
        }

        if (error_count > 0) {
            errorMessage = "Validation Error";
            return false;
        }

        return true;
    }
}
