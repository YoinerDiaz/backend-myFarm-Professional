package com.innovatexts.myFarm.controller;

import com.innovatexts.myFarm.DTO.JwtResponseDTO;
import com.innovatexts.myFarm.DTO.LoginDTO;
import com.innovatexts.myFarm.DTO.RegistroUsuarioDTO;
import com.innovatexts.myFarm.models.Usuario;
import com.innovatexts.myFarm.config.JwtUtil;
import com.innovatexts.myFarm.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
      @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtTokenProvider;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        
        // Obtener detalles del usuario
        Usuario usuario = usuarioService.findByUsername(loginDTO.getUsername());
        
        return ResponseEntity.ok(new JwtResponseDTO(
                jwt,
                usuario.getUid(),
                usuario.getUsuario(),
                usuario.getNombre(),
                usuario.getContacto(),
                usuario.getRol().getNombre()
        ));
    }

    
    @PostMapping("/registro")
    public ResponseEntity<?> register(@Valid @RequestBody RegistroUsuarioDTO registroDTO) {
        Usuario usuario = usuarioService.registrarUsuario(registroDTO);
        
        // Autenticar al usuario recién registrado
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registroDTO.getUsuario(), registroDTO.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        
        return ResponseEntity.ok(new JwtResponseDTO(
                jwt,
                usuario.getUid(),
                usuario.getUsuario(),
                usuario.getNombre(),
                usuario.getContacto(),
                usuario.getRol().getNombre()
        ));
    }
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtTokenProvider.validateToken(token)) {
                return ResponseEntity.ok("Token válido");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
    }
}
