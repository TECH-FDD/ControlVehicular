package domainapp.dom.app.Empleado;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.RegEx;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.Area.Area;
import domainapp.dom.app.Pesona.Documento;
import domainapp.dom.app.Pesona.Sexo;

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
        final @ParameterLayout(named="Sexo") Sexo sexo,
        final @ParameterLayout(named="Domicilio") String domicilio,
        final @ParameterLayout(named="Ciudad") String ciudad,
        final @ParameterLayout(named="Codigo Postal") int cod_postal,
        final @ParameterLayout(named="Telefono") String telefono,
        final @ParameterLayout(named="E-mail") @Parameter(regexPattern=domainapp.dom.regex.Validador.Validador.ValidacionEmail.ADMITIDOS,regexPatternReplacement="@")String email,
        //final @ParameterLayout(named="Fecha de Alta") Timestamp fecha_alta ,
        final @ParameterLayout(named="Nro de Legajo") String legajo,
		final @ParameterLayout(named="Area")Area area){
        final Empleado empleado = container.newTransientInstance(Empleado.class);
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setTipo_documento(tipo_documento);
        empleado.setNro_documento(nro_documento);
        empleado.setFecha_nacimiento(fecha_nacimiento);
        empleado.setDomicilio(domicilio);
        empleado.setCiudad(ciudad);
        empleado.setCodigo_postal(cod_postal);
        empleado.setFecha_alta(new Timestamp(System.currentTimeMillis()));
        empleado.setLegajo(legajo);
        empleado.setSexo(sexo);
        empleado.setArea(area);
        empleado.setTelefono(telefono);
        empleado.setEmail(email);
        container.persistIfNotAlready(empleado);
        return empleado;
    }
	
	// Validar atributos N° Documento  y Fecha Nacimiento
    public String validateCrearEmpleado(String nombre, String apellido, Documento tipo_documento,
			int nro_documento, Timestamp fecha_nacimiento, Sexo sexo, String domicilio,
			String ciudad, int codigo_postal, String telefono, String email,String legajo, Area area){	
    	if(!container.allMatches(new QueryDefault<Empleado>(Empleado.class, "Buscar_Documento", "nro_documento", nro_documento)).isEmpty()){				
			return "El número de Documento ya existe. Por favor vericar los Datos Ingresados.";			
		}
    	if (fecha_nacimiento.after(new Timestamp(System.currentTimeMillis()))){		
			return "La fecha de nacimiento debe ser menor al día actual.";
		}
    	if (!container.allMatches(new QueryDefault<Empleado>(Empleado.class,"Buscar_Legajo","legajo",legajo)).isEmpty()) {
    		return "El numero de legajo ingresado ya esxite! Por favor verificar los datos ingresados."; 
		}
		return null;	
	}

	@MemberOrder(sequence = "2")
    public Empleado BuscarPorDni(
            @ParameterLayout(named="N° de Documento")
            final int nro_documento
    ) {
        return container.firstMatch(new QueryDefault<>(
                Empleado.class,
                "Buscar_Documento",
                "nro_documento", nro_documento)); 
    }

	@MemberOrder(sequence = "4")
    public List<Empleado> buscarPorNombre(@ParameterLayout(named="Nombre")final String nombre){
        return container.allMatches(new QueryDefault<>(Empleado.class,"Buscar_Nombre","nombre", nombre));
    }

	@MemberOrder(sequence = "5")
    public List<Empleado> buscarPorApellido(@ParameterLayout(named="Apellido")final String apellido){
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
	@javax.inject.Inject 
    DomainObjectContainer container;
}
