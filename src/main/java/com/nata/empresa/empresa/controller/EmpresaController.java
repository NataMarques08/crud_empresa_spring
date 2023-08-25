package com.nata.empresa.empresa.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nata.empresa.empresa.dto.FuncionarioRecordDto;
import com.nata.empresa.empresa.model.FuncionarioModel;
import com.nata.empresa.empresa.repository.FuncionarioRepository;

import jakarta.validation.Valid;

@RestController
public class EmpresaController {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @PostMapping("funcionario")
    public ResponseEntity<FuncionarioModel> saveFuncionario(
        @RequestBody @Valid FuncionarioRecordDto funcionarioRecordDto
        ){
            var funcionarioModel = new FuncionarioModel();
            BeanUtils.copyProperties(funcionarioRecordDto, funcionarioModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioRepository.save(funcionarioModel));
    }

    @GetMapping("funcionario")
    public ResponseEntity<List<FuncionarioModel>> getAllFuncionario(){
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioRepository.findAll());
    }

    @GetMapping("funcionario/{id}")
    public ResponseEntity<Object> getOneFuncionario(
        @PathVariable(value = "id") UUID id
    ){
        Optional<FuncionarioModel> funcionario = funcionarioRepository.findById(id);
        if(funcionario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(funcionario.get());
    }

    @PutMapping("funcionario/{id}")
    public ResponseEntity<Object> updateFuncionario(
        @PathVariable(value = "id") UUID id, 
        @RequestBody @Valid FuncionarioRecordDto funcionarioRecordDto
        ){
            Optional<FuncionarioModel> funcionario = funcionarioRepository.findById(id);
            if(funcionario.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado!");
            }
            var funcionarioModel = funcionario.get();
            BeanUtils.copyProperties(funcionarioRecordDto, funcionarioModel);
            return ResponseEntity.status(HttpStatus.OK).body(funcionarioRepository.save(funcionarioModel));
    }

    @DeleteMapping("funcionario/{id}")
    public ResponseEntity<Object> deleteFuncionario(@PathVariable(value = "id") UUID id){
        Optional<FuncionarioModel> funcionario = funcionarioRepository.findById(id);

        if(funcionario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado");
        }
        funcionarioRepository.delete(funcionario.get());
        return ResponseEntity.status(HttpStatus.OK).body("Funcionário exluído com sucesso!");
    }

}
