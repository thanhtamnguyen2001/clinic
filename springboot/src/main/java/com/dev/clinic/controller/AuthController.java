package com.dev.clinic.controller;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.clinic.payload.response.JwtResponse;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dev.clinic.dto.LoginDto;
import com.dev.clinic.dto.UserDto;
import com.dev.clinic.exception.NotFoundException;
import com.dev.clinic.model.User;
import com.dev.clinic.security.jwt.JwtUtils;
import com.dev.clinic.security.services.UserDetailsImpl;
import com.dev.clinic.service.UserService;
import com.dev.clinic.util.CommonMethod;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private Cloudinary cloudinary;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPhone(),
                userDetails.getAvatar(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestParam("file") MultipartFile file,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("comfirmPassword") String comfirmPassword,
            @RequestParam("phone") String phone) {

        String img;
        User user = new User();
        try {

            Map resolve;
            resolve = this.cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            img = (String) resolve.get("secure_url");
            user.setAvatar(img);
            user.setUsername(username);
            user.setPassword(password);
            user.setPhone(phone);
            user.setComfirmPassword(comfirmPassword);
        } catch (IOException ex) {
            throw new NotFoundException("anh loi nhe");
        }
        UserDto newUser = this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/curent-username")
    public ResponseEntity<String> getCurrentUserName() {
        String username = CommonMethod.getCurrentUsername();
        if (username != null) {
            return ResponseEntity.ok(username);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}