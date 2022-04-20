package EggAdopcionMascotas.AppAdopcionMascotas.Repositorios;

import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

}
