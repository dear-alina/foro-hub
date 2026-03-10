package com.alurachallenge.forohub.controller;


import com.alurachallenge.forohub.domain.usuario.DatosAutenticacion;
import com.alurachallenge.forohub.domain.usuario.Usuario;
import com.alurachallenge.forohub.infra.errores.security.DatosTokenJWT;
import com.alurachallenge.forohub.infra.errores.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){

        var autenticacionToken = new UsernamePasswordAuthenticationToken(datos.correo_electronico(), datos.contrasena());

        var authentication = manager.authenticate(autenticacionToken);

        var tokenJWT = tokenService.generarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
