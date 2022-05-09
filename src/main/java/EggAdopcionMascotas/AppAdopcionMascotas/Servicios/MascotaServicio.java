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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MascotaServicio {
   @Autowired 
    MascotaRepositorio repomascota;
   
   public void NuevaMascota(TipoAnimal tipoAnimal,Sexo sexo,Date fechaAdopcion,Usuario usuario,Foto foto,TamanioMascota tamanioMascota,EdadMascota edadMascota)
   {
   
    Mascota mascota = new Mascota();
    
    mascota.setTipoAnimal(tipoAnimal);
    mascota.setSexo(sexo);
    mascota.setEstadoAdopcion(EstadoAdopcion.ENADOPCION);
    mascota.setFechaAdopcion(new Date());
    mascota.setUsuario(usuario);
    mascota.setFoto(foto);
    mascota.setTamanioMascota(tamanioMascota);
    mascota.setEdadMascota(edadMascota);
    repomascota.save(mascota);

   }
   
   public Optional<Mascota> ModificarMascota(String MascotaID)
   {
       Optional<Mascota> mascota = repomascota.findById(MascotaID);
       
       if(mascota.get() != null)
       
       return mascota;
   }
   
   public void ElimarMascota(String MascotaID)
   {
        Optional<Mascota> mascota = repomascota.findById(MascotaID);
        
        repomascota.delete(mascota.get());
   }
   
   
   public List<Mascota> GetListMascota()
   {
   
   
   }
   
   
   private void validate()
   {
   
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
