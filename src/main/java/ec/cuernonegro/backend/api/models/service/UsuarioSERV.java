package ec.cuernonegro.backend.api.models.service;

import ec.cuernonegro.backend.api.models.entity.Usuario;
import ec.cuernonegro.backend.api.dtos.request.usuario.UsuarioCreateDTO;
import ec.cuernonegro.backend.api.models.repository.UsuarioREP;
import ec.cuernonegro.backend.api.utilities.SecurityUtil;
import org.springframework.stereotype.Service;
import ec.cuernonegro.backend.api.utilities.StringUtil;
import ec.cuernonegro.backend.api.utilities.ValidationUtil;

@Service
public class UsuarioSERV {

    private final UsuarioREP usuarioREP;
    public UsuarioSERV(UsuarioREP usuarioREP) {
        this.usuarioREP = usuarioREP;
    }

    public int register (UsuarioCreateDTO req) throws Exception {
        req.validate();
        if (!StringUtil.isValidEmail(req.getMail())) {
            throw new Exception("Debe ingresar un correo electrónico válido");
        }

        boolean verifyEmailDB = usuarioREP.exitsUserWithEmail(req.getMail());
        if (verifyEmailDB) {
            throw new Exception("El correo ya se encuentra registrado");
        }

        if (!ValidationUtil.isValidPassword(req.getPassword())) {
            throw new Exception("La contraseña debe tener al menos 8 caracteres, incluyendo mayúsculas, minúsculas y números");
        }

        if (!ValidationUtil.isValidPhone(req.getPhone())) {
            throw new Exception("El número de teléfono debe tener un formato válido (10 dígitos)");
        }

        String passwordDB = SecurityUtil.hashPassword(req.getPassword());

        Usuario user = new Usuario();
        user.setUsername(StringUtil.sanitize(req.getName()));
        user.setUsermail(StringUtil.sanitize(req.getMail()));
        user.setUserphone(StringUtil.sanitize(req.getPhone()));
        user.setUserpass(passwordDB);

        return usuarioREP.saveUser(user);
    }

}
