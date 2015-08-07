package domainapp.dom.app.vehiculo;

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

import domainapp.dom.app.aceite.TipoAceite;
import domainapp.dom.app.combustible.TipoCombustible;
import domainapp.dom.app.estadoelemento.Activo;
import domainapp.dom.app.estadoelemento.Baja;
import domainapp.dom.app.estadoelemento.Inactivo;
import domainapp.dom.app.estadoelemento.NecesitaReparacion;
import domainapp.dom.app.estadoelemento.Reparacion;
import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.gps.RepositorioGps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.matafuego.RepositorioMatafuego;

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
			final @ParameterLayout(named = "Tipo de Combustible") TipoCombustible tipoCombustible,
			final @ParameterLayout(named = "Capacidad Tanque de comb.") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS, optionality = Optionality.OPTIONAL) Integer capTanqueCombustible,
			final @ParameterLayout(named = "Tipo de Aceite") TipoAceite tipoAceite,
			final @ParameterLayout(named = "cns Combustible Ruta") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String cnsCombustibleRuta,
			final @ParameterLayout(named = "cns Combustible Ciudad") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String cnsCombustibleCiudad,
			final @ParameterLayout(named = "Matafuego") Matafuego matafuego,
			final @ParameterLayout(named = "Kilometros") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String kilometros) {

		final Vehiculo vehiculo = new Vehiculo(marca.toUpperCase(), nombre.toUpperCase(), modelo, fechaCompra,
				patente.toUpperCase(), numeroChasis.toUpperCase(), polizaSeguro, gps, tipoCombustible, capTanqueCombustible,
				tipoAceite, cnsCombustibleRuta, cnsCombustibleCiudad, kilometros, matafuego);

		//Pasar el Gps al estado Asignado
		gps.getEstado().asignarGps(gps);

		//Pasar el Matafuego al estado Asignado
		matafuego.getEstado().asignarMatafuego(matafuego);

		container.persistIfNotAlready(vehiculo);
		return vehiculo;
	}

	/**
	 * Validar el ingreso de los campos unicos, al dar de alta un Vehiculo.
	 *
	 * @return Mensaje de error.
	 */
	public String validateCreateVehiculo(String marca, String nombre, Integer modelo,
			Timestamp fechaCompra, String patente, String nroChasis,
			Integer poliza, Gps gps, TipoCombustible tipoCombustible, Integer tanque,
			TipoAceite tipoAceite, String consumoRuta, String consumoCiudad,
			Matafuego matafuego, String kilometro){

		List<Vehiculo> lista = listAll();
		for (Vehiculo v : lista){
			if (v.getGps().equals(gps))
				return "El Gps ingresado, ya se encuetra asignado al Vehiculo:" + v.toString();
			if (v.getMatafuego().equals(matafuego))
				return "El Matafuego ingresado, ya se encuentra asignado al Vehiculo:" + v.toString();
			if (v.getNumeroChasis()==nroChasis.toUpperCase())
				return "El Número de Chasis ingresado, pertenece al Vehiculo:" + v.toString();
			if (v.getPatente()==patente.toUpperCase())
				return "La Patente Ingresada, pertenece al Vehiculo:" + v.toString();
		}
		return null;
	}

	/**
	 * Mostrar solo Gps Activos.
	 *
	 * @return List<Gps> activos
	 */
	public List<Gps> choices7CreateVehiculo(String marca, String nombre, Integer modelo,
											Timestamp fechaCompra, String patente, String nroChasis,
											Integer poliza, Gps gps, TipoCombustible tipoCombustible, Integer tanque,
											TipoAceite tipoAceite, String consumoRuta, String consumoCiudad,
											Matafuego matafuego, String kilometro){
		List<Gps> lista=repoGps.gpsNoAsignados(container.allInstances(Gps.class));
		return lista;
	}

	/**
	 *Mostrar solo Matafuegos Activos
	 *
	 * @return List<Matafuego> activos.
	 */
	public List<Matafuego> choices13CreateVehiculo(String marca, String nombre, Integer modelo,
											Timestamp fechaCompra, String patente, String nroChasis,
											Integer poliza, Gps gps, TipoCombustible tipoCombustible, Integer tanque,
											TipoAceite tipoAceite, String consumoRuta, String consumoCiudad,
											Matafuego matafuego, String kilometro){
		return repoMatafuego.listAll();
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar Todos")
	public List<Vehiculo> listAll() {
		return activos(container.allInstances(Vehiculo.class));
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Buscar por Marca")
	public List<Vehiculo> findByMarca(
			@ParameterLayout(named = "Marca") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String marca) {
		return activos(container.allMatches(new QueryDefault<>(Vehiculo.class,
				"BuscarMarca", "marca", marca.toUpperCase())));
	}

	@MemberOrder(sequence = "4")
	@ActionLayout(named = "Buscar por Nombre")
	public List<Vehiculo> findByNombre(
			@ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionLetras.ADMITIDOS) final String nombre) {
		return activos(container.allMatches(new QueryDefault<>(Vehiculo.class,
				"BuscarNombre", "nombre", nombre.toUpperCase())));
	}

	@MemberOrder(sequence = "5")
	@ActionLayout(named = "Buscar por Modelo")
	public List<Vehiculo> findByModelo(
			@ParameterLayout(named = "Modelo") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) final Integer modelo) {
		return activos(container.allMatches(new QueryDefault<>(Vehiculo.class,
				"BuscarModelo", "modelo", modelo)));
	}

	@MemberOrder(sequence = "6")
	@ActionLayout(named = "Buscar por Patente")
	public List<Vehiculo> findByPatente(
			@ParameterLayout(named = "Patente") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String patente) {
		return activos(container.allMatches(new QueryDefault<>(Vehiculo.class,
				"BuscarPatente", "patente", patente.toUpperCase())));
	}

	@MemberOrder(sequence = "7")
	@ActionLayout(named = "Buscar por N° Chasis")
	public List<Vehiculo> findByNumeroChasis(
			@ParameterLayout(named = "Numero Chasis") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) final String numeroChasis) {
		return activos(container.allMatches(new QueryDefault<>(Vehiculo.class,
				"BuscarNumeroChasis", "numeroChasis", numeroChasis.toUpperCase())));
	}

	/**
	 * Filtrar lista de Vehiculo, por estado Activo.
	 * @param lista
	 * @return lista de Vehiculo Activos.
	 */
	private List<Vehiculo> activos(List<Vehiculo> lista){
		List<Vehiculo> activos = new ArrayList<Vehiculo>();
		for (Vehiculo v : lista){
			if (v.getEstado() instanceof Activo)
				activos.add(v);
		}
		return activos;
	}

	@MemberOrder(sequence = "5", name="Elementos Inactivos")
	@ActionLayout(named = "Vehiculo")
	public List<Vehiculo> listInactivos(){
		List<Vehiculo> lista=container.allInstances(Vehiculo.class);
		List<Vehiculo> inactivos= new ArrayList<Vehiculo>();
		for (Vehiculo vehiculo : lista){
			if ((vehiculo.getEstado() instanceof Inactivo ||
				vehiculo.getEstado() instanceof NecesitaReparacion ||
				vehiculo.getEstado() instanceof Reparacion))
				inactivos.add(vehiculo);
		}
		return inactivos;
	}

	@MemberOrder(sequence = "3", name="Elementos Desestimados")
	@ActionLayout(named = "Vehiculo")
	public List<Vehiculo> listBaja(){
		List<Vehiculo> lista = container.allInstances(Vehiculo.class);
		List<Vehiculo> bajas= new ArrayList<Vehiculo>();
		for (Vehiculo vehiculo : lista){
			if (vehiculo.getEstado() instanceof Baja)
				bajas.add(vehiculo);
		}
		return bajas;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	RepositorioGps repoGps;
	@javax.inject.Inject
	RepositorioMatafuego repoMatafuego;
}
