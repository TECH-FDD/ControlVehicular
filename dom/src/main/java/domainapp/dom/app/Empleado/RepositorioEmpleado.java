package domainapp.dom.app.Empleado;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.Pesona.Documento;

@DomainService(repositoryFor = Empleado.class)
@DomainServiceLayout(menuOrder = "20", named="Empleado")
public class RepositorioEmpleado {
	@MemberOrder(sequence = "1")
    public Empleado crearEmpleado(
        final @ParameterLayout(named="Nombre") String nombre, 
        final @ParameterLayout(named="Apellido") String apellido,
        final @ParameterLayout(named="Tipo de Documento") Documento tipo_documento,
        final @ParameterLayout(named="N° de Documento") int nro_documento,
        final @ParameterLayout(named="Fecha de Nacimiento") Timestamp fecha_nacimiento,
        final @ParameterLayout(named="Domicilio") String domicilio,
        final @ParameterLayout(named="Ciudad") String ciudad,
        final @ParameterLayout(named="Codigo Postal") int cod_postal,
        final @ParameterLayout(named="Fecha de Alta") Timestamp fecha_alta,
        final @ParameterLayout(named="Nro de Legajo") String legajo){
        final Empleado empleado = container.newTransientInstance(Empleado.class);
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setDoc(tipo_documento);
        empleado.setNro_doc(nro_documento);
        empleado.setFecha_nacimiento(fecha_nacimiento);
        empleado.setDomicilio(domicilio);
        empleado.setCiudad(ciudad);
        empleado.setCodigo_postal(cod_postal);
        empleado.setFecha_alta(fecha_alta);
        empleado.setLegajo(legajo);
        container.persistIfNotAlready(empleado);
        return empleado;
    }
	
	@javax.inject.Inject 
    DomainObjectContainer container;
}
