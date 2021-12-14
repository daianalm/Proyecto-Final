package com.informatorio.api.controller;
import com.informatorio.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.informatorio.api.entity.Emprendimiento;

@RestController
@RequestMapping(value = "/emprendimiento")

public class EmprendimientoController{
    private final EmprendimientoRepository emprendimientoRepository;
    
    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository){
        this.emprendimientoRepository = emprendimientoRepository;
    }
    @PostMapping

    public ResponseEntity<?> createEmprendimiento(@Validated @RequestBody Emprendimiento emprendimiento) {
        return new ResponseEntity<>(emprendimientoRepository.save(emprendimiento), HttpStatus.CREATED);
    }
}




