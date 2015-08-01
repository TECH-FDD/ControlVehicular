package domainapp.fixture;

import java.sql.Timestamp;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;

import domainapp.dom.app.aceite.TipoAceite;
import domainapp.dom.app.combustible.TipoCombustible;
import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.vehiculo.RepositorioVehiculo;
import domainapp.dom.app.vehiculo.Vehiculo;

public class VehiculoFixture extends Fixture {

	private static String marca="Ford";
	private static String nombre= "Cargo 815";
	private static int modelo=2015;
	private static String patente="SER 123,HBD 789,HUG 675,IBM 255,KIJ 567";
	private static String numeroChasis="JGASDYUGSUY5468FG,KSUDS327DSF44RFDE,HDJSY2653GHETDBSH78,JFUERJ463548EHDGJFU80K,JUDH6DHSYS987SHYC7";
	private static String polizaSeguro="12545687,45875658,5287469,542134569,457821456";
	private static int capTanqueCombustible=1000;
	private static String cnsCombustibleRuta="1850";
	private static String cnsCombustibleCiudad="900";
	private static String kilometros="466580";

	private static String getPatente(int x) {
		return obtenerValor(patente, x);
	}

	private static String getNumeroChasis(int x) {
		return obtenerValor(numeroChasis, x);
	}

	private static int getPolizaSeguro(int x) {
		return Integer.parseInt(obtenerValor(polizaSeguro, x));
	}

	public VehiculoFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {

		borrarTabla(executionContext,"Vehiculo");
		List<Gps> gps= container.allInstances(Gps.class);
		List<TipoCombustible> tipoCombustibles= container.allInstances(TipoCombustible.class);
//		List<Aceite> aceites= container.allInstances(Aceite.class);
		List<Matafuego> matafuegos= container.allInstances(Matafuego.class);

		for (int x=0; x<5; x++){
			create(new Timestamp(System.currentTimeMillis()),getPatente(x),
					getNumeroChasis(x),getPolizaSeguro(x),gps.get(0),tipoCombustibles.get(0),
					matafuegos.get(0),TipoAceite.SemiSintetico,executionContext);
		}
	}

	private Vehiculo create( Timestamp fechaCompra,
			String patente, String numeroChasis, Integer polizaSeguro,
			Gps gps, TipoCombustible tipoCombustible, Matafuego matafuego,
			TipoAceite tipoAceite, ExecutionContext executionContext) {
		return executionContext.addResult(this, repoVehiculo.createVehiculo(marca, nombre, modelo, fechaCompra, patente, numeroChasis, polizaSeguro, gps, tipoCombustible, capTanqueCombustible, tipoAceite, cnsCombustibleRuta, cnsCombustibleCiudad, matafuego, kilometros));
	}

	@javax.inject.Inject
	private RepositorioVehiculo repoVehiculo;
	@javax.inject.Inject
	DomainObjectContainer container;
}
