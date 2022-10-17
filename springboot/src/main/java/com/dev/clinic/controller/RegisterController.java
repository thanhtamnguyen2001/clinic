package com.dev.clinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.clinic.model.Register;
import com.dev.clinic.service.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/api/registers")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping
    public ResponseEntity<List<Register>> getAllRegisters(@RequestParam(required = true, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String phone) {
        List<Register> registers = this.registerService.getAllRegisters(name, phone);
        return ResponseEntity.ok(registers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Register> getRegisterById(@PathVariable long id) {
        Register register = this.registerService.getRegisterById(id);
        if (register != null) {
            return ResponseEntity.ok(register);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<Register> createRegister(@RequestBody Register register) {
        Register newRegister = this.registerService.creatRegister(register);

        return ResponseEntity.status(HttpStatus.CREATED).body(newRegister);
    }

    @PostMapping("/{id}/verified")
    public ResponseEntity<Register> verifiedRegister(@PathVariable long id) {
        Register register = this.registerService.verifiedRegister(id);
        if (register != null) {
            return ResponseEntity.ok(register);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRegister(@PathVariable long id) {
        if (this.registerService.deleteRegister(id)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Register> updateRegister(@PathVariable long id, @RequestBody Register register) {
        Register updateRegister = this.registerService.updateRegister(id, register);
        if (updateRegister != null) {
            return ResponseEntity.ok(updateRegister);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
