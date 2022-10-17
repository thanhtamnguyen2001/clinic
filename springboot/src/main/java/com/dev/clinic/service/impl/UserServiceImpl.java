package com.dev.clinic.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.clinic.dto.UserDto;
import com.dev.clinic.exception.BadRequestException;
import com.dev.clinic.exception.InternalException;
import com.dev.clinic.exception.NotFoundException;
import com.dev.clinic.exception.UnauthorizedException;
import com.dev.clinic.model.Certificate;
import com.dev.clinic.model.Register;
import com.dev.clinic.model.Role;
import com.dev.clinic.model.User;
import com.dev.clinic.repository.RoleRepository;
import com.dev.clinic.repository.UserRepository;
import com.dev.clinic.service.UserService;
import com.dev.clinic.util.CommonMethod;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<UserDto> getAllUsers(String username) {
        List<User> users = this.userRepository.findByUsernameContaining(username);
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(modelMapper.map(user, UserDto.class));
        }

        if (userDtos.isEmpty()) {
            throw new NotFoundException("No user exist!");
        }

        return userDtos;
    }

    @Override
    public UserDto getUserById(long id) {
        Optional<User> uOptional = this.userRepository.findById(id);

        if (uOptional.isPresent()) {
            User user = uOptional.get();

            return modelMapper.map(user, UserDto.class);
        }

        throw new NotFoundException("User does not exist!");
    }

    @Override
    public UserDto createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new BadRequestException("Username is already taken!");
        }
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            throw new BadRequestException("Username and password are required!");
        }
        if (!user.getPassword().equals(user.getComfirmPassword())) {
            throw new BadRequestException("Password and confirmation password do not match!");
        }

        Optional<Role> rOptional = this.roleRepository.findByName("ROLE_PATIENT");
        if (rOptional.isEmpty()) {
            throw new InternalException("ROLE_PATIENT does not exist!");
        }

        Role role = rOptional.get();
        user.addRole(role);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        User newUser = this.userRepository.save(user);

        UserDto userDto = modelMapper.map(newUser, UserDto.class);

        return userDto;

    }

    @Override
    public UserDto updateUser(long id, User user) {
        Optional<User> uOptional = this.userRepository.findById(id);
        if (uOptional.isPresent()) {
            Optional<Role> rOptional = this.roleRepository.findByName("ROLE_PATIENT");
            if (rOptional.isEmpty()) {
                throw new InternalException("ROLE_PATIENT does not exist!");
            }

            Role role = rOptional.get();
            user.setId(id);
            user.addRole(role);

            User updateUser = this.userRepository.save(user);
            UserDto userDto = modelMapper.map(updateUser, UserDto.class);

            return userDto;
        }

        throw new NotFoundException("User does not exist!");
    }

    @Override
    public Boolean deleteUser(long id) {
        Optional<User> uOptional = this.userRepository.findById(id);
        if (uOptional.isPresent()) {
            this.userRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException("User does not exist!");
    }

    @Override
    public Set<Register> getRegistersByUserId(long id) {
        Optional<User> uOptional = this.userRepository.findById(id);
        if (uOptional.isPresent()) {
            User user = uOptional.get();
            Set<Register> registers = user.getRegisters();

            if (!registers.isEmpty()) {
                return registers;
            }
            throw new NotFoundException("User does not have any registrations!");
        }
        throw new NotFoundException("User does not exist!");
    }

    @Override
    public Set<Certificate> getCertificatesByUserId(long id) {
        Optional<User> uOptional = this.userRepository.findById(id);
        if (uOptional.isPresent()) {
            User user = uOptional.get();
            Set<Certificate> certificates = user.getCertificates();

            if (!certificates.isEmpty()) {
                return certificates;
            }
            throw new NotFoundException("User does not have any certificates!");
        }
        throw new NotFoundException("User does not exist!");
    }

    @Override
    public User getCurrentUser() {
        String currentUsername = CommonMethod.getCurrentUsername();
        if (currentUsername != null) {
            Optional<User> uOptional = this.userRepository.findByUsername(currentUsername);
            if (uOptional.isPresent()) {
                return uOptional.get();
            }
            throw new NotFoundException("Username " + currentUsername + " does not exist!");
        }
        
        throw new UnauthorizedException("User is not authenticared!");
    }

    @Override
    public Set<Certificate> getCertificatesByCurrentUser() {
        User user = this.getCurrentUser();
        Set<Certificate> certificates = user.getCertificates();

        if (!certificates.isEmpty()) {
            return certificates;
        }
        throw new NotFoundException("User does not have any certificates!");
    }

    @Override
    public Set<Register> getRegistersByCurrentUser() {
        User user = this.getCurrentUser();
        Set<Register> registers = user.getRegisters();

        if (!registers.isEmpty()) {
            return registers;
        }
        throw new NotFoundException("User does not have any registers!");
    }

}