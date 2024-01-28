package com.mwambacodes.identityservice.modules.Core.User.Actions.ListUser;

import com.mwambacodes.identityservice.modules.Core.User.UserDTO;
import com.mwambacodes.identityservice.modules.Core.User.UserDTOMapper;
import com.mwambacodes.identityservice.modules.Core.User.UserRepository;
import com.mwambacodes.identityservice.utils.Helpers;
import com.mwambacodes.identityservice.utils.ServiceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserListService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public ServiceResult init() {

        List<UserDTO> users = userRepository.findAll()
                .stream()
                .map(userDTOMapper).collect(Collectors.toList());
        return Helpers.success(users);

    }

}

