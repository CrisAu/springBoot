/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sukasa.prueba.repository;

import com.sukasa.prueba.model.ClienteModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author crist
 */
public interface ClienteRepository extends JpaRepository<ClienteModel, String>{
    Optional<ClienteModel> findByCedula(String cedula);
}
