/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sukasa.prueba.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sukasa.prueba.model.ClienteModel;
import com.sukasa.prueba.model.RespuesMensaje;
import com.sukasa.prueba.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author crist
 */
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    public String ListarClientes(){
        try{
            List<ClienteModel> informacionList = clienteRepository.findAll();
            
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode responseNode = objectMapper.createObjectNode();
            
            //agregarmos la cabecera
            
            responseNode.put("AllData", "AllData");
            
            if (!informacionList.isEmpty()) {
                ArrayNode dataArrayNode = objectMapper.createArrayNode();
                for(ClienteModel row : informacionList){
                    ObjectNode dataObjectNode = objectMapper.createObjectNode();
                    
                    //asignamos nombres a los campos 
                    dataObjectNode.put("id",row.getId());
                    dataObjectNode.put("cedula",row.getCedula());
                    dataObjectNode.put("nombre",row.getNombre());
                    dataObjectNode.put("apellido",row.getApellido());
                    dataObjectNode.put("correo",row.getEmail());
                    dataArrayNode.add(dataObjectNode);
                }
                responseNode.put("status", "success");
                responseNode.set("data", dataArrayNode);
            }else{
                responseNode.put("status", "empty");
                responseNode.putNull("data");
            }
            return objectMapper.writeValueAsString(responseNode);
    } catch (DataAccessException | JsonProcessingException e) {
        e.printStackTrace();
        return "{\"status\":\"error\", \"message\":\"Error al procesar la solicitud\"}";
    }
    }
    
    // Método para guardar un cliente
    public ResponseEntity<Object> guardar(ClienteModel cliente) {
        try {
            ClienteModel nuevoCliente = clienteRepository.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(new RespuesMensaje("Cliente guardado exitosamente", nuevoCliente));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new RespuesMensaje("Error al procesar la solicitud: " + e.getMessage(), null));
        }
    }
    
    
    //metodo para buscar un cliente en especifico 
    public ResponseEntity<Object>  buscarCliente(String id){
        try {
            ClienteModel nuevoCliente = clienteRepository.findById(id).orElse(null);
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(new RespuesMensaje("Cliente encontrado", nuevoCliente));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new RespuesMensaje("Error al procesar la solicitud: " + e.getMessage(), null));
        }
    }
    /*
    public eliminar(String cedula){
        clienteRepository.delete(cedula);
    }*/
}
