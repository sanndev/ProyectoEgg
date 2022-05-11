package EggAdopcionMascotas.AppAdopcionMascotas.Controladores;

import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Mascota;
import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Usuario;
import EggAdopcionMascotas.AppAdopcionMascotas.Enums.EstadoAdopcion;
import EggAdopcionMascotas.AppAdopcionMascotas.Enums.Sexo;
import EggAdopcionMascotas.AppAdopcionMascotas.Repositorios.MascotaRepositorio;
import EggAdopcionMascotas.AppAdopcionMascotas.Servicios.MascotaServicio;
import EggAdopcionMascotas.AppAdopcionMascotas.Servicios.UsuarioServicio;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller

@RequestMapping("/mascota")
public class MascotaControlador {

        @Autowired
    private MascotaServicio mservicio;

    @GetMapping("/publicar-mascota")
    public String index(Model model) {

        model.addAttribute("mascota", new Mascota());

        return "darenadopcion";
    }
    
    @PostMapping("/publicar-mascota")
    @ResponseBody
    public String registrar(HttpSession session, @RequestParam("file") MultipartFile file, @ModelAttribute("mascota") Mascota mascota) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        mascota.setUsuario(usuario);
        mservicio.NuevaMascota(usuario, mascota, mservicio.CreateImage("src//main//resources//static/upload", file));
        return "Home";
    }

    @GetMapping("")
    public String ViewUserPanel() 
    {
        return "UserPanel";
    }
    
    @GetMapping("/mostrarmascotas")
    @ResponseBody
    public List<Mascota> MostrarMascotas() 
    {
        return mservicio.GetAllMascotas();
    }
}