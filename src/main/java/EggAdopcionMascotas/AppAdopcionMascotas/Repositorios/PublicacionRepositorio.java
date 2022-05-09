package EggAdopcionMascotas.AppAdopcionMascotas.Repositorios;

import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, String> {

}
