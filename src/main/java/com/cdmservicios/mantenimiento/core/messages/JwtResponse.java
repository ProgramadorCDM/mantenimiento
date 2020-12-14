package com.cdmservicios.mantenimiento.core.messages;

import lombok.Data;

import java.util.List;

@Data

public class JwtResponse {

    private String token;
    private final String TYPE = "Bearer";
    private Long id;
    private String username;
    private String name;
    private String Email;
    private List<String> roles;

    public JwtResponse(String token, Long id, String username, String name, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.name = name;
        Email = email;
        this.roles = roles;
    }
}
