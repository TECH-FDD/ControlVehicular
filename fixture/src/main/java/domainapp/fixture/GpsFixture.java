package domainapp.fixture;

import java.sql.Timestamp;

import domainapp.dom.app.gps.Gps;
import domainapp.dom.app.gps.RepositorioGps;

public class GpsFixture extends Fixture {

	private static String codigoIdentificacion="AH46521,FG65482,JK48645,GP44485,VN65484";
	private static String modelo="XM-700,HF-550,LK-864,Xperia 2015,Chino-75";
	private static String marca="Garmin,Samsung,Motorola,Sony,Generic";
	private static String observacion="Dispositivo nuevo en excelente estado.";
	private static String descripcion="";

	private String getCodigoIdentificacion(int x){
		return obtenerValor(codigoIdentificacion, x);
	}

	private String getModelo(int x){
		return obtenerValor(modelo, x);
	}

	private String getMarca(int x){
		return obtenerValor(marca, x);
	}

	public GpsFixture(){
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		borrarTabla(executionContext, "Gps");
		for (int x=0; x<5; x++){
			create(getCodigoIdentificacion(x),getModelo(x),getMarca(x),new Timestamp(System.currentTimeMillis()),executionContext);
		}
	}

	private Gps create(String codIdentificacion,String modelo,String marca,Timestamp fechaAsigVehiculo,ExecutionContext executionContext){
		return executionContext.addResult(this, repoGps.createGps(codIdentificacion, modelo, marca, descripcion, fechaAsigVehiculo, observacion));
	}

	@javax.inject.Inject
	private RepositorioGps repoGps;
}