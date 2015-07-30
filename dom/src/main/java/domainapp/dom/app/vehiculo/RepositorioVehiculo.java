package domainapp.dom.app.vehiculo;

import java.sql.Timestamp;
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

import domainapp.dom.app.aceite.Aceite;
import domainapp.dom.app.combustible.Combustible;
import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.gps.RepositorioGps;
import domainapp.dom.app.matafuego.Matafuego;

@DomainService(repositoryFor = Vehiculo.class)
@DomainServiceLayout(menuOrder = "80", named = "Vehiculo")
public class RepositorioVehiculo {

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear nuevo Vehiculo")
	public Vehiculo createVehiculo(
			final @ParameterLayout(named = "Marca") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String marca,
			final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) String nombre,
			final @ParameterLayout(named = "Modelo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) Integer modelo,
			final @ParameterLayout(named = "Fecha Compra") Timestamp fechaCompra,
			final @ParameterLayout(named = "Patente") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String patente,
			final @ParameterLayout(named = "Numero de Chasis") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String numeroChasis,
			final @ParameterLayout(named = "Poliza/Seguro") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) Integer polizaSeguro,
			final @ParameterLayout(named = "Gps") Gps gps,
			final @ParameterLayout(named = "Combustible") Combustible combustible,
			final @ParameterLayout(named = "Capacidad Tanque de comb.") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS, optionality = Optionality.OPTIONAL) Integer capTanqueCombustible,
			final @ParameterLayout(named = "Aceite") Aceite aceite,
			final @ParameterLayout(named = "cns Combustible Ruta") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String cnsCombustibleRuta,
			final @ParameterLayout(named = "cns Combustible Ciudad") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String cnsCombustibleCiudad,
			final @ParameterLayout(named = "Matafuego") Matafuego matafuego,
			final @ParameterLayout(named = "Kilometros") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String kilometros) {

		final Vehiculo vehiculo = container
				.newTransientInstance(Vehiculo.class);
		vehiculo.setMarca(marca);
		vehiculo.setModelo(modelo);
		vehiculo.setNombre(nombre);
		vehiculo.setFechaCompra(fechaCompra);
		vehiculo.setPatente(patente);
		vehiculo.setNumeroChasis(numeroChasis);
		vehiculo.setPolizaSeguro(polizaSeguro);
		vehiculo.setGps(gps);
		vehiculo.setCombustible(combustible);
		vehiculo.setCapacTanqueCombustible(capTanqueCombustible);
		vehiculo.setAceite(aceite);
		vehiculo.setCnsCombustibleRuta(cnsCombustibleRuta);
		vehiculo.setCnsCombuestibleCiudad(cnsCombustibleCiudad);
		vehiculo.setMatafuego(matafuego);
		vehiculo.setKilometros(kilometros);

		container.persistIfNotAlready(vehiculo);
		return vehiculo;
	}

	public List<Gps> choices7CreateVehiculo(String marca, String nombre, Integer modelo,
											Timestamp fechaCompra, String patente, String nroChasis,
											Integer poliza, Gps gps, Combustible combustible, Integer tanque,
											Aceite aceite, String consumoRuta, String consumoCiudad,
											Matafuego matafuego, String kilometro){
		List<Gps> lista=repoGps.listarTodos();
		return lista;
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar Todos")
	public List<Vehiculo> listAll() {
		return container.allInstances(Vehiculo.class);
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por Marca")
	public List<Vehiculo> findByMarca(
			@ParameterLayout(named = "Marca") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String marca) {
		return container.allMatches(new QueryDefault<>(Vehiculo.class,
				"BuscarMarca", "marca", marca));
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por Nombre")
	public List<Vehiculo> findByNombre(
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) final String nombre) {
		return container.allMatches(new QueryDefault<>(Vehiculo.class,
				"BuscarNombre", "nombre", nombre));
	}

	@MemberOrder(sequence = "5")
	@ActionLayout(named = "Buscar por Modelo")
	public List<Vehiculo> findByModelo(
			@ParameterLayout(named = "Modelo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) final Integer modelo) {
		return container.allMatches(new QueryDefault<>(Vehiculo.class,
				"BuscarModelo", "modelo", modelo));
	}

	@MemberOrder(sequence = "6")
	@ActionLayout(named = "Buscar por Patente")
	public List<Vehiculo> findByPatente(
			@ParameterLayout(named = "Patente") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String patente) {
		return container.allMatches(new QueryDefault<>(Vehiculo.class,
				"BuscarPatente", "patente", patente));
	}

	@MemberOrder(sequence = "7")
	@ActionLayout(named = "Buscar por N° Chasis")
	public List<Vehiculo> findByNumeroChasis(
			@ParameterLayout(named = "Numero Chasis") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String numeroChasis) {
		return container.allMatches(new QueryDefault<>(Vehiculo.class,
				"BuscarNumeroChasis", "numeroChasis", numeroChasis));
	}

	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	RepositorioGps repoGps;
}
