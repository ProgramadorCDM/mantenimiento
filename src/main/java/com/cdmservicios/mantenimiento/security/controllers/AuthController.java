package com.cdmservicios.mantenimiento.security.controllers;

import com.cdmservicios.mantenimiento.core.messages.JwtResponse;
import com.cdmservicios.mantenimiento.core.messages.MessageResponse;
import com.cdmservicios.mantenimiento.core.request.LoginRequest;
import com.cdmservicios.mantenimiento.core.request.SignUpRequest;
import com.cdmservicios.mantenimiento.security.keys.JwtUtils;
import com.cdmservicios.mantenimiento.security.models.ERole;
import com.cdmservicios.mantenimiento.security.models.Role;
import com.cdmservicios.mantenimiento.security.models.User;
import com.cdmservicios.mantenimiento.security.repositories.RoleRepository;
import com.cdmservicios.mantenimiento.security.repositories.UserRepository;
import com.cdmservicios.mantenimiento.security.services.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder encoder,
                          JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    @ApiOperation(
            value = "Iniciar Sesión",
            notes = "Servicio que nos valida la información de usuario e inicia sesión")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario inicia sesión correctamente"),
            @ApiResponse(code = 201, message = "Usuario inicia sesión correctamente"),
            @ApiResponse(code = 400, message = "Campos Inválidos"),
            @ApiResponse(code = 401, message = "Usuario no autorizado"),
            @ApiResponse(code = 403, message = "Recurso no disponible"),
            @ApiResponse(code = 404, message = "Recurso no encontrado")
    })
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getName(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    @ApiOperation(
            value = "Registrar Usuario",
            notes = "Servicio que crea los usuario para el aplicativo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario creado correctamente"),
            @ApiResponse(code = 201, message = "Usuario creado correctamente"),
            @ApiResponse(code = 400, message = "Campos Inválidos(Duplicados o incorrectos)"),
            @ApiResponse(code = 401, message = "Usuario no autorizado"),
            @ApiResponse(code = 403, message = "Recurso no disponible"),
            @ApiResponse(code = 404, message = "Recurso no encontrado")
    })
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El nombre de Usuario ya existe"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El email ya se encuentra registrado"));
        }
        // Creamos la nueva cuenta de usuario
        // Create new user's account
        User user = new User (
                signUpRequest.getUsername(),
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())
                );
        Set<String> srtRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if(srtRoles == null){
            Role userRole = roleRepository.findAllByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado"));
            roles.add(userRole);
        }else {
            srtRoles.forEach(role -> {
                switch (role){
                    case "admin":
                        Role adminRole = roleRepository.findAllByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado"));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findAllByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
                        roles.add(modRole);
                        break;
                    case "sup":
                        Role supRole = roleRepository.findAllByName(ERole.ROLE_SUPERVISOR)
                                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
                        roles.add(supRole);
                    default:
                        Role userRole = roleRepository.findAllByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Usuario Creado Correctamente"));
    }
}
