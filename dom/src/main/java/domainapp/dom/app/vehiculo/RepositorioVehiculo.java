package domainapp.dom.app.vehiculo;

import java.sql.Timestamp;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

import domainapp.dom.app.aceite.Aceite;
import domainapp.dom.app.combustible.Combustible;
import domainapp.dom.app.gps.Gps;

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
			final @ParameterLayout(named = "Poliza del Seguro") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) Integer polizaSeguro,
			final @ParameterLayout(named = "Gps") Gps gps,
			final @ParameterLayout(named = "Combustible") Combustible combustible,
			final @ParameterLayout(named = "Capacidad Tanque de comb.") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS, optionality = Optionality.OPTIONAL) Integer capTanqueCombustible,
			final @ParameterLayout(named = "Aceite") Aceite aceite,
			final @ParameterLayout(named = "cns Combustible Ruta") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String cnsCombustibleRuta,
			final @ParameterLayout(named = "cns Combustible Ciudad") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS, optionality = Optionality.OPTIONAL) String cnsCombustibleCiudad,
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
		vehiculo.setKilometros(kilometros);

		container.persistIfNotAlready(vehiculo);
		return vehiculo;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
