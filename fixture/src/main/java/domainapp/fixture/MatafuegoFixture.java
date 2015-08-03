package domainapp.fixture;

import java.sql.Timestamp;
import java.util.Calendar;

import domainapp.dom.app.matafuego.Matafuego;
import domainapp.dom.app.matafuego.RepositorioMatafuego;

public class MatafuegoFixture extends Fixture {

	private String marca="Yukon,Georgia,Drago,3M,TGB";
	private String codigo="YK,GG,DG,3M,TGB";
	private String descripcion="Extintor manual de Polvo Comprimido.";
	private String capacidad= "3,3,5,10,15";
	private Timestamp fechaCarga = new Timestamp(System.currentTimeMillis());
	private Timestamp fechaRecarga;

	public MatafuegoFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	public String getMarca(int x) {
		return obtenerValor(marca, x);
	}

	public String getCodigo(int x) {
		return obtenerValor(codigo, x);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getCapacidad(int x) {
		return obtenerValor(capacidad, x);
	}

	public Timestamp getFechaCarga() {
		return fechaCarga;
	}

	public Timestamp getFechaRecarga() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Timestamp(System.currentTimeMillis()).getTime());
		cal.add(Calendar.YEAR, 1);
		fechaRecarga = new Timestamp(cal.getTimeInMillis());
		return fechaRecarga;
	}

	public void setFechaRecarga(Timestamp fechaRecarga) {
		this.fechaRecarga = fechaRecarga;
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		borrarTabla(executionContext, "Matafuego");
		for (int x=0; x<5; x++){
			create(getMarca(x), getCodigo(x), Integer.parseInt(getCapacidad(x)), getFechaCarga(), getFechaRecarga(), executionContext);
		}
	}

	private Matafuego create(String marca, String codigo, int capacidad, Timestamp fechaRecarga, Timestamp fechaCadRecarga, ExecutionContext executionContext){
		return executionContext.addResult(this, repoMatafuego.createMatafuego(marca, codigo, descripcion, capacidad, fechaRecarga, fechaCadRecarga));
	}

	@javax.inject.Inject
	private RepositorioMatafuego repoMatafuego;
}
