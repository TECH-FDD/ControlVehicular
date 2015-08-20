package domainapp.fixture;

import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;

import domainapp.dom.app.alerta.AlertaVehiculo;
import domainapp.dom.app.alerta.RepositorioAlertaVehiculo;
import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.vehiculo.Vehiculo;

public class AlertaVehiculoFixture extends Fixture{
	private static String nombre= "Cambio de Aceite,Cambio de filtro de Aceite,Cambio de filtro de Aire, Cambio de Correa";
	private static String descripcion = "";
	private static String contadorAlerta="10000,20000,10000,20000";
	
	public AlertaVehiculoFixture() {
		// TODO Auto-generated constructor stub
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	private static String getNombre(int x) {
		return obtenerValor(nombre, x);
	}
	private static Integer getContadorAlerta(int x){
		return Integer.parseInt(obtenerValor(contadorAlerta, x));
	}
	@Override
	protected void execute(ExecutionContext executionContext) {
		List<Empleado> empleados=container.allInstances(Empleado.class);
		List<Vehiculo> vehiculos=container.allInstances(Vehiculo.class);
		List<AlertaVehiculo> alertavehiculos= container.allInstances(AlertaVehiculo.class);
		if (alertavehiculos!=null){
			borrarTabla(executionContext,"AlertaVehiculo");
		}
			
			for (int x = 0; x < 4; x++) {
				create(getNombre(x), descripcion, new Date(System.currentTimeMillis()),empleados.get(x+1),vehiculos.get(x+1),getContadorAlerta(x), executionContext);
			}
		
	}

	private AlertaVehiculo create(String nombre, String descripcion,Date fechaAlta, Empleado empleado, Vehiculo vehiculo,int contadorAlerta,
			ExecutionContext executionContext) {
		return executionContext.addResult(this,
				repoAlerta.createAlertaVehiculo(nombre, descripcion, empleado, vehiculo, contadorAlerta));
	}

	@javax.inject.Inject
	private RepositorioAlertaVehiculo repoAlerta;
	@javax.inject.Inject
	DomainObjectContainer container;
}
