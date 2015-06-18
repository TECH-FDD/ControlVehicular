package domainapp.dom.app.gps;

import java.sql.Timestamp;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

@DomainService(repositoryFor = Gps.class)
@DomainServiceLayout(menuOrder = "50", named = "Gps")
public class RepoGps {
	@MemberOrder(sequence = "1")
	public Gps crearGps(
			final @ParameterLayout(named = "Codigo Identificacion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codIdentificacion,
			final @ParameterLayout(named = "Modelo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String modelo,
			final @ParameterLayout(named = "Marca") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String marca,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String descripcion,
			final @ParameterLayout(named = "Fecha Asignacion Vehiculo") Timestamp fechaAsigVehiculo,
			// final @ParameterLayout(named="Fecha Alta") Timestamp fechaAlta,
			final @ParameterLayout(named = "observacion Estado Dispositivo ") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String obsEstadoDisp) {

		final Gps gps = container.newTransientInstance(Gps.class);

		gps.setCodIdentificacion(codIdentificacion);
		gps.setModelo(modelo);
		gps.setMarca(marca);
		gps.setDescripcion(descripcion);
		gps.setFechaAlta(new Timestamp(System.currentTimeMillis()));
		gps.setFechaAsigVehiculo(fechaAsigVehiculo);
		gps.setObsEstadoDispositivo(obsEstadoDisp);

		container.persistIfNotAlready(gps);
		return gps;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
