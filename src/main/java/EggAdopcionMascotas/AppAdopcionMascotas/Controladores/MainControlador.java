package EggAdopcionMascotas.AppAdopcionMascotas.Controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("")
public class MainControlador {

    @GetMapping("")
    public String index() {
        return "Home";
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @GetMapping("/facundo")
    public String facundo() {
        return "facundo";
    }

}
