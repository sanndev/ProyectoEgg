package EggAdopcionMascotas.AppAdopcionMascotas.Controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@RequestMapping("")
public class MainControlador {

    @GetMapping("")
    public String index() {
        return "Home";
    }
    

    @GetMapping("plugins")
    @CrossOrigin(origins="http//localhost:8080")
     @ResponseBody
    public Map<String, Object> plugins() {
        
        
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("email","email");
        params.put("password","password");

        return params;
    }

}
