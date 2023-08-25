package com.nata.empresa.empresa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nata.empresa.empresa.model.FuncionarioModel;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel,UUID>{
    
}
