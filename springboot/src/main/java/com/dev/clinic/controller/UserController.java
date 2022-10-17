package com.dev.clinic.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.clinic.dto.UserDto;
import com.dev.clinic.model.Certificate;
import com.dev.clinic.model.Register;
import com.dev.clinic.model.User;
import com.dev.clinic.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(required = false, defaultValue = "") String username) {
        List<UserDto> users = this.userService.getAllUsers(username);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        UserDto user = this.userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // @PostMapping
    // public ResponseEntity<UserDto> createUser(@RequestBody User user) {
    //     UserDto newUser = this.userService.createUser(user);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    // }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody User user) {
        UserDto updateUser = this.userService.updateUser(id, user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable long id) {
        boolean isDeleted = this.userService.deleteUser(id);
        return ResponseEntity.ok(isDeleted);
    }

    @GetMapping("{id}/registers")
    public ResponseEntity<Set<Register>> getRegistersByUserId(@PathVariable long id) {
        Set<Register> registers = this.userService.getRegistersByUserId(id);
        return ResponseEntity.ok(registers);
    }

    @GetMapping("{id}/certificates")
    public ResponseEntity<Set<Certificate>> getCertificatesByUserId(@PathVariable Long id) {
        Set<Certificate> certificates = this.userService.getCertificatesByUserId(id);
        return ResponseEntity.ok(certificates);
    }

    @GetMapping("certificates")
    public ResponseEntity<Set<Certificate>> getCertificatesByCurrentUser() {
        Set<Certificate> certificates = this.userService.getCertificatesByCurrentUser();
        return ResponseEntity.ok(certificates);
    }

    @GetMapping("registers")
    public ResponseEntity<Set<Register>> getRegistersByCurrentUser() {
        Set<Register> registers = this.userService.getRegistersByCurrentUser();
        return ResponseEntity.ok(registers);
    }

}
