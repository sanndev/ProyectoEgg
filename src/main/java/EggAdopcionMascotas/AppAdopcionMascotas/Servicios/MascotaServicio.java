/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EggAdopcionMascotas.AppAdopcionMascotas.Servicios;

import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Foto;
import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Mascota;
import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Usuario;
import EggAdopcionMascotas.AppAdopcionMascotas.Enums.EdadMascota;
import EggAdopcionMascotas.AppAdopcionMascotas.Enums.EstadoAdopcion;
import EggAdopcionMascotas.AppAdopcionMascotas.Enums.Sexo;
import EggAdopcionMascotas.AppAdopcionMascotas.Enums.TamanioMascota;
import EggAdopcionMascotas.AppAdopcionMascotas.Enums.TipoAnimal;
import EggAdopcionMascotas.AppAdopcionMascotas.Repositorios.MascotaRepositorio;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MascotaServicio {
   @Autowired 
    MascotaRepositorio repomascota;
   
   public void NuevaMascota(Usuario user,Mascota gmascota,String image)
   {
    Mascota mascota = new Mascota();
    mascota.setNombre(gmascota.getNombre());
    mascota.setDescripcion(gmascota.getDescripcion());
    mascota.setTipoAnimal(gmascota.getTipoAnimal());
    mascota.setSexo(gmascota.getSexo());
    mascota.setEstadoAdopcion(EstadoAdopcion.ENADOPCION);
    mascota.setFechaAdopcion(new Date());
    mascota.setFoto(image);
    mascota.setTamanioMascota(gmascota.getTamanioMascota());
    mascota.setEdadMascota(gmascota.getEdadMascota());
    mascota.setUsuario(gmascota.getUsuario());
    repomascota.save(mascota);
   }
   

   public Optional<Mascota> ModificarMascota(String MascotaID)
   {
       Optional<Mascota> mascota = repomascota.findById(MascotaID);
       
       if(mascota.get() != null)
       {
            return mascota;
       }
       
       return null;
 
   }

   
   public boolean ElimarMascota(String MascotaID)
   {
        Optional<Mascota> mascota = repomascota.findById("08bbf0cc-7ffc-4cc5-bc2b-dcde99b9e15f");
        
        try
        {
           repomascota.delete(mascota.get());
           System.out.println("Eliminando mascota");
        }catch(Exception ex)
        {
        
            System.out.println(ex.toString());
        }
        
        return true;
   }
   
   public List<Mascota> GetAllMascotas()
   {
       List<Mascota> mascotas = repomascota.findAll();
       
       if(mascotas.isEmpty())
       {
           return null;
       }
       
      return mascotas;
   }

   
   
  public String CreateImage(String dir, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                Path directorio, rutag1;

                directorio = Paths.get(dir);

                rutag1 = Paths.get(directorio.toFile().getAbsolutePath() + "//" + file.getOriginalFilename());

                Files.write(rutag1, file.getBytes());
                
                
            } catch (Exception ex) {
                System.out.println("Error en la Creacion");
            }
        }

        return file.getOriginalFilename();
    } 
   
   
   
}
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
