package EggAdopcionMascotas.AppAdopcionMascotas.Servicios;

import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Publicacion;
import EggAdopcionMascotas.AppAdopcionMascotas.Repositorios.PublicacionRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPublicacion {
    
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
    
    public Publicacion crearPublicacion(Publicacion publicacion/*, id usuario, mascota */){
        
    
        //validacion de campos
        
        //metodo crear mascota mascota.crearMascota(); consultar si recibe mascota o se la arma en la publicacion
        
        return publicacionRepositorio.save(publicacion);
        
    }
    
    public List<Publicacion> listarTodasLasPublicaciones(){
        return publicacionRepositorio.findAll();
    }
    
    public void bajaPublicacion(Publicacion publicacion){
         publicacionRepositorio.delete(publicacion);
    }
}


//alta
//baja 
