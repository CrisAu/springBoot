/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sukasa.prueba.controller;

import com.sukasa.prueba.model.ClienteModel;
import com.sukasa.prueba.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author crist
 */
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public String ListarPersonas(Model model){
            return  this.clienteService.ListarClientes();
    }
    @PostMapping("/save")
    private ResponseEntity<Object> guardarCliente(@RequestBody ClienteModel cliente)throws Exception{
        try{
            ResponseEntity<Object> resultado = clienteService.guardar(cliente);
            return new ResponseEntity<>(resultado,HttpStatus.OK);
        } catch (Exception e) {
            // Manejar excepciones aquí, por ejemplo, loguear el error
            return new ResponseEntity("Error al subir el archivo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/buscarCliente")
    public ResponseEntity<Object> buscarCliente(@RequestParam String id) {
try{
            ResponseEntity<Object> resultado = clienteService.buscarCliente(id);
            return new ResponseEntity<>(resultado,HttpStatus.OK);
        } catch (Exception e) {
            // Manejar excepciones aquí, por ejemplo, loguear el error
            return new ResponseEntity("Error al subir el archivo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
}
