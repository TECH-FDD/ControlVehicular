package domainapp.dom.app.cargaCombustible;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import domainapp.dom.app.combustible.Combustible;
import domainapp.dom.app.vehiculo.Vehiculo;

@DomainService(repositoryFor = CargaCombustible.class)
@DomainServiceLayout(menuOrder = "40", named = "Carga Combustible")
public class RepositorioCargaCombustible {
	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear Carga combustible")
	public CargaCombustible createCargaCombustible(final @ParameterLayout(named = "Vehiculo") Vehiculo vehiculo,
			final @ParameterLayout(named = "TipoCombustible") Combustible combustible,
			final @ParameterLayout(named = "Litros Cargados") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) Double litrosCargados) {

		BigDecimal costoTotal = (combustible.getPrecioLitro()).multiply(BigDecimal.valueOf(litrosCargados));

		final CargaCombustible cargaCombustible = new CargaCombustible(vehiculo, new Date(System.currentTimeMillis()),
				combustible, costoTotal, litrosCargados);
		container.persistIfNotAlready(cargaCombustible);
		return cargaCombustible;
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<CargaCombustible> listAll() {
		final List<CargaCombustible> listaCargaCombustible = this.container
				.allMatches(new QueryDefault<CargaCombustible>(CargaCombustible.class,
						"ListarTodos"));
		if (listaCargaCombustible.isEmpty()) {
			this.container
					.warnUser("No hay cargas de combustible cargados en el sistema");
		}
		return listaCargaCombustible;
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por vehiculo")
	public List<CargaCombustible> findByVehiculo(
			@ParameterLayout(named = "Vehiculo") final Vehiculo vehiculo) {

		final List<CargaCombustible> listaCargaCombustible = listAll();
		final List<CargaCombustible> lista = new ArrayList<CargaCombustible>();
		for (CargaCombustible cc : listaCargaCombustible) {
			if (cc.getVehiculo().equals(vehiculo)) {
				lista.add(cc);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existe el Vehiculo buscado");
		}
		return lista;
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por Fecha")
	public List<CargaCombustible> findByFechaCarga(@ParameterLayout(named = "Fecha Carga") final LocalDate fecha) {
		List<CargaCombustible> listaCargaCombustible = container.allInstances(CargaCombustible.class);
		List<CargaCombustible> listaCarga = new ArrayList<CargaCombustible>();
		for (CargaCombustible cargaCombustible: listaCargaCombustible) {
			LocalDate ld = LocalDate.fromDateFields(cargaCombustible.getFechaCarga());
			if (ld.compareTo(fecha) == 0) {
				listaCarga.add(cargaCombustible);
			}
		}
		if (listaCarga.isEmpty()) {
			this.container.warnUser("No existe la fecha buscada");
		}
		return listaCarga;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
