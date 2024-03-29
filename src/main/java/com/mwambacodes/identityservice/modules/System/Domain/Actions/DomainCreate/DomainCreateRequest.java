package com.mwambacodes.identityservice.modules.System.Domain.Actions.DomainCreate;

import com.mwambacodes.identityservice.modules.System.Domain.DomainRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainCreateRequest {

    private String name;
    private String url;
    private String administratorEmail;
    private String administratorFirstName;
    private String administratorLastName;
    private MultipartFile logo;
    private Double commissionPercentage;
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

        if (administratorFirstName == null || administratorFirstName.isEmpty()) {
            error_count++;
            errors.put("administratorFirstName", "Administrator First Name should not be empty");
        }

        if (administratorLastName == null || administratorLastName.isEmpty()) {
            error_count++;
            errors.put("administratorLastName", "Administrator Last Name should not be empty");
        }

        if (logo == null || logo.isEmpty()) {
            error_count++;
            errors.put("logo", "Logo should not be empty");
        }

        if (commissionPercentage == null || commissionPercentage.isNaN() || commissionPercentage == 0) {
            error_count++;
            errors.put("commissionPercentage", "Commission Percentage should be a value greater than 0");
        }

        if (error_count > 0) {
            errorMessage = "Validation Error";
            return false;
        }

        return true;
    }
}
