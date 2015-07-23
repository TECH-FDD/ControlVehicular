package domainapp.dom.app.taller;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import domainapp.dom.app.area.Area;
import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.persona.Ciudad;
import domainapp.dom.app.persona.Documento;
import domainapp.dom.app.persona.Provincia;
import domainapp.dom.app.persona.Sexo;

@DomainService(repositoryFor = Taller.class)
@DomainServiceLayout(menuOrder = "100", named = "Taller")
public class RepositorioTaller {

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear Taller")
	public Taller createTaller(
			final @ParameterLayout(named = "Nombre Comercial") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombreComercial,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String descripcion,
			final @ParameterLayout(named = "Direccion")  @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String direccion,
			final @ParameterLayout(named = "telefono")  @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionTel.ADMITIDOS) String telefono,
			final @ParameterLayout(named = "Codigo")  @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codigo,
			final @ParameterLayout(named = "Email")  @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionEmail.ADMITIDOS) String email){

		final Taller taller = container.newTransientInstance(Taller.class);

		taller.setNombreComercial(nombreComercial);
		taller.setTelefono(telefono);
		taller.setDescripcion(descripcion);
		taller.setDireccion(direccion);
		taller.setEmail(email);
		taller.setCodigo(codigo);
		container.persistIfNotAlready(taller);
		return taller;

	}

	
	@javax.inject.Inject
	DomainObjectContainer container;
}
