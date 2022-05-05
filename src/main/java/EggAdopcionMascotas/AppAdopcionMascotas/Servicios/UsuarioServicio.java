package EggAdopcionMascotas.AppAdopcionMascotas.Servicios;

import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Usuario;
import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Zona;
import EggAdopcionMascotas.AppAdopcionMascotas.Errores.ErroresServicio;
import EggAdopcionMascotas.AppAdopcionMascotas.Repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void registrarUsuario(String nombre, String apellido, String telefono, String email, String password, Zona zona) throws ErroresServicio {

        validarDatos(nombre, apellido, telefono, email, password, zona);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);

        String passwordEncriptado = new BCryptPasswordEncoder().encode(password);
        usuario.setPassword(passwordEncriptado);
        usuario.setZona(zona);
        usuario.setFechaAlta(new Date());

        usuarioRepositorio.save(usuario);

    }

    public void modificarUsuario(String id, String nombre, String apellido, String telefono, String email, String password, Zona zona) throws ErroresServicio {

        validarDatos(nombre, apellido, telefono, email, password, zona);

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if (!respuesta.isPresent()) {

            throw new ErroresServicio("No se encontró el usuario solicitado");

        }

        Usuario usuario = respuesta.get();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);

        String passwordEncriptado = new BCryptPasswordEncoder().encode(password);
        usuario.setPassword(passwordEncriptado);

        usuario.setZona(zona);

        usuarioRepositorio.save(usuario);

    }

    public void deshabilitarUsuario(String id) throws ErroresServicio {

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if (!respuesta.isPresent()) {

            throw new ErroresServicio("No se encontró el usuario solicitado");

        }

        Usuario usuario = respuesta.get();
        usuario.setFechaBaja(new Date());

        usuarioRepositorio.save(usuario);

    }

    public void habilitarUsuario(String id) throws ErroresServicio {

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if (!respuesta.isPresent()) {

            throw new ErroresServicio("No se encontró el usuario solicitado");

        }

        Usuario usuario = respuesta.get();
        usuario.setFechaBaja(null);

        usuarioRepositorio.save(usuario);

    }

    private void validarDatos(String nombre, String apellido, String telefono, String email, String password, Zona zona) throws ErroresServicio {

        if (nombre == null || nombre.isEmpty()) {

            throw new ErroresServicio("Ingrese un nombre");

        }

        if (apellido == null || apellido.isEmpty()) {

            throw new ErroresServicio("Ingrese un apellido");

        }

        if (telefono == null || telefono.isEmpty()) {

            throw new ErroresServicio("Ingrese un número de teléfono");

        }

        if (email == null || email.isEmpty()) {

            throw new ErroresServicio("Ingrese un correo electrónico");

        }

        if (password == null || password.isEmpty()) {

            throw new ErroresServicio("Ingrese una contraseña");

        } else if (password.length() < 8) {
            throw new ErroresServicio("La contraseña debe tener 8 o más caracteres");
        }

        if (zona == null) {

            throw new ErroresServicio("Seleccione una zona por favor");

        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority permiso1 = new SimpleGrantedAuthority("MODULO_FOTOS");
            permisos.add(permiso1);

            GrantedAuthority permiso2 = new SimpleGrantedAuthority("MODULO_MASCOTAS");
            permisos.add(permiso2);

            GrantedAuthority permiso3 = new SimpleGrantedAuthority("MODULO_PUBLICACIONES");
            permisos.add(permiso3);

            User user = new User(usuario.getEmail(), usuario.getPassword(), permisos);
            return user;

        } else {
            return null;
        }

    }

}
