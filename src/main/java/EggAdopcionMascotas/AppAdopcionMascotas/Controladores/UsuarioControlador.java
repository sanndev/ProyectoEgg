package EggAdopcionMascotas.AppAdopcionMascotas.Controladores;

import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Zona;
import EggAdopcionMascotas.AppAdopcionMascotas.Errores.ErroresServicio;
import EggAdopcionMascotas.AppAdopcionMascotas.Servicios.UsuarioServicio;
import EggAdopcionMascotas.AppAdopcionMascotas.Servicios.ZonaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ZonaServicio zonaServicio;

    @GetMapping("/registro")
    public String registro(Model modelo) {
        List<Zona> zonas = zonaServicio.listarZonas();
        modelo.addAttribute("zonas", zonas);

        return "SignUp";
    }

    @PostMapping("/registrar")
    public String registrar(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String telefono, @RequestParam String email,
            @RequestParam String password, @RequestParam String password2,
            @RequestParam String idZona, Model modelo) {
        try {

            usuarioServicio.registrarUsuario(nombre, apellido, telefono, email, password, password2, idZona);
            modelo.addAttribute("succes", "Usuario registrado correctamente");
            return "SignUp";

        } catch (ErroresServicio e) {

            List<Zona> zonas = zonaServicio.listarZonas();

            modelo.addAttribute("error", e.getMessage());
            modelo.addAttribute("nombre", nombre);
            modelo.addAttribute("apellido", apellido);
            modelo.addAttribute("telefono", telefono);
            modelo.addAttribute("email", email);
            modelo.addAttribute("password", password);
            modelo.addAttribute("password2", password2);
            modelo.addAttribute("zonas", zonas);

            return "SignUp";
        }

    }

}
