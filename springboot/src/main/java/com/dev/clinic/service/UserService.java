package com.dev.clinic.service;

import java.util.List;
import java.util.Set;

import com.dev.clinic.dto.UserDto;
import com.dev.clinic.model.Certificate;
import com.dev.clinic.model.Register;
import com.dev.clinic.model.User;

public interface UserService {
    
    List<UserDto> getAllUsers(String username);

    UserDto getUserById(long id);

    UserDto createUser(User user);

    UserDto updateUser(long id, User user);

    Boolean deleteUser(long id);

    Set<Register> getRegistersByUserId(long id);

    Set<Certificate> getCertificatesByUserId(long id);

    Set<Certificate> getCertificatesByCurrentUser();

    Set<Register> getRegistersByCurrentUser();

    User getCurrentUser();
}
