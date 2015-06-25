package domainapp.dom.app.matafuego;
import java.sql.Timestamp;


import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;


@DomainService(repositoryFor=Matafuego.class)
@DomainServiceLayout(menuOrder="80",named="Matafuego")
public class RepositorioMatafuego {
	
	@MemberOrder(sequence="1")
	@ActionLayout(named="Crear matafuego")
	public Matafuego createMatafuego(
		final @ParameterLayout(named="Nombre") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombre,
		final @ParameterLayout(named="codigo") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codigo,
		final @ParameterLayout(named="Descripcion") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) StringBuffer descripcion,
		final @ParameterLayout(named="Capacidad") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) int capacidad,
		final @ParameterLayout(named="Fecha de alta") Timestamp fechaAlta,
		final @ParameterLayout(named="Fecha de recarga") Timestamp fechaRecarga,
		final @ParameterLayout(named="Fecha de caducidad") Timestamp fechaCadRecarga,
		final @ParameterLayout(named="Vehiculo") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String vehiculo,
		final @ParameterLayout(named="Mantenimiento") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String mantenimiento,
		final @ParameterLayout(named="Reparacion") @Parameter(regexPattern=domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String reparacion){
		
		Matafuego matafuego = container.newTransientInstance(Matafuego.class);
		matafuego.setNombre(nombre);
		matafuego.setCodigo(codigo);
		matafuego.setDescripcion(descripcion);
		matafuego.setCapacidad(capacidad);
		matafuego.setFechaAlta(fechaAlta);
		matafuego.setFechaRecarga(fechaRecarga);
		matafuego.setFechaCadRecarga(fechaCadRecarga);
		matafuego.setVehiculo(vehiculo);
		matafuego.setMantenimiento(mantenimiento);
		matafuego.setReparacion(reparacion);
		container.persistIfNotAlready(matafuego);
		return matafuego; 
	}  
	
	@javax.inject.Inject
	DomainObjectContainer container;
}
