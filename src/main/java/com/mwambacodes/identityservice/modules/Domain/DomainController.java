package com.mwambacodes.identityservice.modules.Domain;

import com.mwambacodes.identityservice.modules.Domain.Actions.DomainList.DomainListService;
import com.mwambacodes.identityservice.modules.Domain.Requests.RegisterDomainRequest;
import com.mwambacodes.identityservice.modules.Domain.Services.RegisterDomainService;
import com.mwambacodes.identityservice.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.mwambacodes.identityservice.utils.Helpers.sendResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/domains")
public class DomainController {

    private final RegisterDomainService registerDomainService;
    private final DomainListService domainListService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('Domain.list')")
    public ResponseEntity<ApiResponse> index(
            @RequestParam Map<String, String> params
    ) {
        return sendResponse(domainListService.init(params));
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('Domain.create')")
    public ResponseEntity<ApiResponse> register(
            @RequestBody RegisterDomainRequest request
    ) {
        return sendResponse(registerDomainService.init(request));
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('Domain.update')")
    public ResponseEntity<ApiResponse> update(
            @RequestBody RegisterDomainRequest request
    ) {
        return sendResponse(registerDomainService.init(request));
    }

}
