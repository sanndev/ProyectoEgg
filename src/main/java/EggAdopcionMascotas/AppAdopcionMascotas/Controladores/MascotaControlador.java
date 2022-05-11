package EggAdopcionMascotas.AppAdopcionMascotas.Controladores;

import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Mascota;
import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Usuario;
import EggAdopcionMascotas.AppAdopcionMascotas.Servicios.MascotaServicio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping("/mostrarmascotas")
    @ResponseBody
    public List<Mascota> MostrarMascotas() {
        return mservicio.GetAllMascotas();
    }

    @DeleteMapping("eliminar/{id}")
    @ResponseBody
    public List<String> EliminarMascota(@PathVariable String id) {

        System.out.println(id);
        System.out.println("ELIMINAR MASCOTA");
        mservicio.ElimarMascota(id);
        List<String> nex = new ArrayList<String>();

        nex.add("Mascota");

        return nex;
    }

}
