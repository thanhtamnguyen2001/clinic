package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.model.Register;


public interface RegisterService {
    
    Register creatRegister(Register register);

    List<Register> getAllRegisters(String name, String phone);

    Register getRegisterById(long id);

    Register verifiedRegister(long id);
    
    Boolean deleteRegister(long id);

    Register updateRegister(long id, Register register);
}
