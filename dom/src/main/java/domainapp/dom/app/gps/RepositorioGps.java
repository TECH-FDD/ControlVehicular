package domainapp.dom.app.gps;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.estadoelemento.Activo;

@DomainService(repositoryFor = Gps.class)
@DomainServiceLayout(menuOrder = "50", named = "Gps")
public class RepositorioGps {
	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear nuevo Gps")
	public Gps createGps(
			final @ParameterLayout(named = "Codigo Identificacion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String codIdentificacion,
			final @ParameterLayout(named = "Modelo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String modelo,
			final @ParameterLayout(named = "Marca") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String marca,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String descripcion,
			final @ParameterLayout(named = "Fecha Asignacion Vehiculo") Timestamp fechaAsigVehiculo,
			final @ParameterLayout(named = "observacion Estado Dispositivo ") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String obsEstadoDisp) {

		final Gps gps = new Gps(codIdentificacion, marca, modelo,
				descripcion, new Timestamp(System.currentTimeMillis()),
				fechaAsigVehiculo, obsEstadoDisp);

//				container.newTransientInstance(Gps.class);
//		gps.setCodIdentificacion(codIdentificacion);
//		gps.setModelo(modelo);
//		gps.setMarca(marca);
//		gps.setDescripcion(descripcion);
//		gps.setFechaAlta(new Timestamp(System.currentTimeMillis()));
//		gps.setFechaAsigVehiculo(fechaAsigVehiculo);
//		gps.setObsEstadoDispositivo(obsEstadoDisp);

		container.persistIfNotAlready(gps);
		return gps;
	}

	// Validar atributos Codigo Identificación
	public String validateCreateGps(String codIdentificacion, String modelo,
			String marca, String descripcionString,
			Timestamp fechaAsigVehiculo, String ObsEstadoDispositivo) {
		if (!container.allMatches(
				new QueryDefault<Gps>(Gps.class,
						"buscarPorCodigoIdentificacion", "codIdentificacion",
						codIdentificacion)).isEmpty()) {
			return "El código de Identificación ya existe. Por favor verificar los Datos Ingresados.";
		}
		return null;
	}

	@MemberOrder(sequence = "2")
	public List<Gps> listarTodos() {
		return GpsActivos(container.allInstances(Gps.class));
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por Modelo")
	public List<Gps> findByModelo(
			@ParameterLayout(named = "Modelo Gps") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String modelo) {
		return container.allMatches(new QueryDefault<>(Gps.class,
				"buscarPorModelo", "modelo", modelo));
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por Marca")
	public List<Gps> findByMarca(
			@ParameterLayout(named = "Marca Gps") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String marca) {
		return container.allMatches(new QueryDefault<>(Gps.class,
				"buscarPorMarca", "marca", marca));
	}

	@MemberOrder(sequence = "5")
	@ActionLayout(named = "Buscar por Codigo Identificacion")
	public List<Gps> findByCodIdentificacion(
			@ParameterLayout(named = "Codigo Identificacacion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String codIdentificacion) {
		return container.allMatches(new QueryDefault<>(Gps.class,
				"buscarPorCodigoIdentificacion", "codIdentificacion",
				codIdentificacion));
	}

	@MemberOrder(sequence = "3", name="Elementos Inactivos")
	@ActionLayout(named = "Gps")
	public List<Gps> listGpsInactivos(){
		List<Gps> lista=container.allInstances(Gps.class);
		List<Gps> inactivos= new ArrayList<Gps>();
		for (Gps gps : lista){
			if (!(gps.getEstado() instanceof Activo))
				inactivos.add(gps);
		}
		return lista;
	}

	/**
	 * Filtrar lista de Gps, por activos.
	 * @param lista
	 * @return lista de Gps en estado Activo.
	 */
	private List<Gps> GpsActivos(final List<Gps> lista){
		List<Gps> activos= new ArrayList<Gps>();
		for (Gps gps : lista){
			if (gps.getEstado() instanceof Activo)
				activos.add(gps);
		}
		return activos;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
