package EggAdopcionMascotas.AppAdopcionMascotas.Servicios;

import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Usuario;
import EggAdopcionMascotas.AppAdopcionMascotas.Entidades.Zona;
import EggAdopcionMascotas.AppAdopcionMascotas.Errores.ErroresServicio;
import EggAdopcionMascotas.AppAdopcionMascotas.Repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService { // implements UserDetailsService

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ZonaServicio zonaServicio;

    public void registrarUsuario(String nombre, String apellido, String telefono, String email, String password, String password2, String idZona) throws ErroresServicio {

        Zona zona = zonaServicio.devolverZona(idZona);
        validarDatos(nombre, apellido, telefono, email, password, password2, zona);

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

    public void modificarUsuario(String id, String nombre, String apellido, String telefono, String email, String password, String password2, String idZona) throws ErroresServicio {

        Zona zona = zonaServicio.devolverZona(idZona);
        validarDatos(nombre, apellido, telefono, email, password, password2, zona);

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

    private void validarDatos(String nombre, String apellido, String telefono, String email, String password, String password2, Zona zona) throws ErroresServicio {

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

            throw new ErroresServicio("Ingrese un correo electrónico válido");

        }

        if (usuarioRepositorio.buscarPorEmail(email).equals(email)) {

            throw new ErroresServicio("Ya existe un usuario con este email");

        }

        if (password == null || password.isEmpty()) {

            throw new ErroresServicio("Ingrese una contraseña");
        }

        if (password.length() < 8) {
            throw new ErroresServicio("La contraseña debe tener 8 o más caracteres");
        }

        if (password.length() > 15) {
            throw new ErroresServicio("La contraseña no puede superar los 15 caracteres");
        }

        if (!password.equals(password2)) {

            throw new ErroresServicio("Las contraseñas deben ser iguales");

        }

        if (zona == null) {

            throw new ErroresServicio("Seleccione una zona");

        }

    }

    public void agregarUsuarioALaSesion(Usuario usuario) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        session.setAttribute("usuariosession", usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {

            Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
            agregarUsuarioALaSesion(usuario);
            List<GrantedAuthority> permisos = new ArrayList<>();

            if (usuario != null) {
                GrantedAuthority permiso1 = new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO");
                permisos.add(permiso1);
            }

            // advertencia aqui porque posiblemente si la variable usuario viene null nunca podre obtener el mail ni password ni agregar permisos a esa variable
            return new User(usuario.getEmail(), usuario.getPassword(), permisos);

        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }

    }

}
