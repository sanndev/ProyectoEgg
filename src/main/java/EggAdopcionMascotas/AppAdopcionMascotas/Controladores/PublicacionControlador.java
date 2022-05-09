/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EggAdopcionMascotas.AppAdopcionMascotas.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author I5 7400
 */
@Controller
@RequestMapping("/publicacion")
public class PublicacionControlador {
    
    //localhost:8080/publicacion/form
    @GetMapping("/form")
    public String form() {
        return "publicacion-form";
    }
}
