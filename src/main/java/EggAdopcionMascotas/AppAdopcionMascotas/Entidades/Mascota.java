package EggAdopcionMascotas.AppAdopcionMascotas.Entidades;

import EggAdopcionMascotas.AppAdopcionMascotas.Enums.EdadMascota;
import EggAdopcionMascotas.AppAdopcionMascotas.Enums.EstadoAdopcion;
import EggAdopcionMascotas.AppAdopcionMascotas.Enums.Sexo;
import EggAdopcionMascotas.AppAdopcionMascotas.Enums.TamanioMascota;
import EggAdopcionMascotas.AppAdopcionMascotas.Enums.TipoAnimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Mascota {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Enumerated(EnumType.STRING)
    private TipoAnimal tipoAnimal;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoAdopcion estadoAdopcion;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fechaAdopcion;

    @ManyToOne
    private Usuario usuario;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Foto foto;

    @Enumerated(EnumType.STRING)
    private TamanioMascota tamanioMascota;

    @Enumerated(EnumType.STRING)
    private EdadMascota edadMascota;

}
