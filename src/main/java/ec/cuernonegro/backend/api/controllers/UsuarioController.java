package ec.cuernonegro.backend.api.controllers;

import ec.cuernonegro.backend.api.dtos.request.usuario.UsuarioCreateDTO;
import ec.cuernonegro.backend.api.dtos.response.UsuarioResDTO;
import ec.cuernonegro.backend.api.models.service.UsuarioSERV;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioSERV usuarioSERV;

    public UsuarioController(UsuarioSERV usuarioSERV) {
        this.usuarioSERV = usuarioSERV;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody UsuarioCreateDTO req) {
        try {
            int userIdDB = usuarioSERV.register(req);
            if (userIdDB < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error al registrar el usuario");
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario registrado con éxito");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}