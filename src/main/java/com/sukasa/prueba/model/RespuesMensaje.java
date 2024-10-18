/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sukasa.prueba.model;

/**
 *
 * @author crist
 */
public class RespuesMensaje {
    private String message;
    private ClienteModel data;

    public RespuesMensaje(String message, ClienteModel data) {
        this.message = message;
        this.data = data;
    }

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClienteModel getData() {
        return data;
    }

    public void setData(ClienteModel data) {
        this.data = data;
    }
}
