package domainapp.dom.app.empleado;

import java.sql.Timestamp;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.area.Area;
import domainapp.dom.app.persona.Ciudad;
import domainapp.dom.app.persona.Documento;
import domainapp.dom.app.persona.Provincia;
import domainapp.dom.app.persona.Sexo;

@DomainService(repositoryFor = Empleado.class)
@DomainServiceLayout(menuOrder = "20", named="Empleado")
public class RepositorioEmpleado {
	@MemberOrder(sequence = "1")
    public Empleado crearEmpleado(
        final @ParameterLayout(named="Nombre")@Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String nombre,
        final @ParameterLayout(named="Apellido") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String apellido,
        final @ParameterLayout(named="Tipo de Documento") Documento tipoDocumento,
        final @ParameterLayout(named="N° de Documento") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) int nro_documento,
        final @ParameterLayout(named="Fecha de Nacimiento") Timestamp fechaNacimiento,
        final @ParameterLayout(named="Sexo") Sexo sexo,
        final @ParameterLayout(named="Provincia") Provincia provincia,
        final @ParameterLayout(named="Ciudad") Ciudad ciudad,
        final @ParameterLayout(named="Codigo Postal") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) int codigoPostal,
        final @ParameterLayout(named="Domicilio") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String domicilio,
        final @ParameterLayout(named="Telefono") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionTel.ADMITIDOS)String telefono,
        final @ParameterLayout(named="E-mail") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionEmail.ADMITIDOS)String email,
        //final @ParameterLayout(named="Fecha de Alta") Timestamp fecha_alta ,
        final @ParameterLayout(named="Nro de Legajo") String legajo,
		final @ParameterLayout(named="Area")Area area){
        final Empleado empleado = container.newTransientInstance(Empleado.class);
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setTipoDocumento(tipoDocumento);
        empleado.setNroDocumento(nro_documento);
        empleado.setFechaNacimiento(fechaNacimiento);
        empleado.setDomicilio(domicilio);
        empleado.setCiudad(ciudad);
        empleado.setCodigoPostal(codigoPostal);
        empleado.setFechaAlta(new Timestamp(System.currentTimeMillis()));
        empleado.setLegajo(legajo);
        empleado.setSexo(sexo);
        empleado.setArea(area);
        empleado.setTelefono(telefono);
        empleado.setEmail(email);
        empleado.setProvincia(provincia);
        empleado.setActivo(true);
        container.persistIfNotAlready(empleado);
        return empleado;
    }
	
	// Validar atributos N° Documento y Fecha Nacimiento
	public String validateCrearEmpleado(String nombre, String apellido,Documento tipoDocumento, int nroDocumento,
										Timestamp fechaNacimiento, Sexo sexo, Provincia provincia,
										Ciudad ciudad, int codigoPostal, String domicilio,
										String telefono, String email, String legajo, Area area) {
		if (!container.allMatches(new QueryDefault<Empleado>(Empleado.class, "Buscar_Documento","nroDocumento", nroDocumento)).isEmpty()) {
			return "El número de Documento ya existe. Por favor vericar los Datos Ingresados.";
		}
		if (fechaNacimiento.after(new Timestamp(System.currentTimeMillis()))) {
			return "La fecha de nacimiento debe ser menor al día actual.";
		}
		if (!container.allMatches(new QueryDefault<Empleado>(Empleado.class, "Buscar_Legajo","legajo", legajo)).isEmpty()) {
			return "El numero de legajo ingresado ya esxite! Por favor verificar los datos ingresados.";
		}
		return null;
	}

	@MemberOrder(sequence = "2")
    public Empleado BuscarPorDni(
            @ParameterLayout(named="N° de Documento") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS)
            final int nroDocumento
    ) {
        return container.firstMatch(new QueryDefault<>(
                Empleado.class,
                "Buscar_Documento",
                "nroDocumento", nroDocumento));
    }

	@MemberOrder(sequence = "4")
    public List<Empleado> buscarPorNombre(@ParameterLayout(named="Nombre") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) final String nombre){
        return container.allMatches(new QueryDefault<>(Empleado.class,"Buscar_Nombre","nombre", nombre));
    }

	@MemberOrder(sequence = "5")
    public List<Empleado> buscarPorApellido(@ParameterLayout(named="Apellido") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) final String apellido){
        return container.allMatches(new QueryDefault<>(Empleado.class,"Buscar_Apellido","apellido", apellido));
    }

	@MemberOrder(sequence = "3")
    public List<Empleado> listarTodos(){
        return container.allInstances(Empleado.class);
    }

	@MemberOrder(sequence = "6")
    public List<Empleado> buscarPorLegajo(@ParameterLayout(named="N° de Legajo")final String legajo){
        return container.allMatches(new QueryDefault<>(Empleado.class,"Buscar_Legajo","legajo", legajo));
    }

	public List<Ciudad> choices7CrearEmpleado(String nombre, String apellido, Documento tipoDocumento,
			int nroDocumento, Timestamp fechaNacimiento, Sexo sexo, Provincia provincia,
			 Ciudad ciudad, int codigoPostal,String domicilio, String telefono, String email,String legajo, Area area) {
        return Ciudad.listarPor(provincia);
    }

	@javax.inject.Inject
    DomainObjectContainer container;
}
