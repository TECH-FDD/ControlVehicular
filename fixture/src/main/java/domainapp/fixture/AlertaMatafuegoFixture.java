package domainapp.fixture;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;

import domainapp.dom.app.alerta.AlertaMatafuego;
import domainapp.dom.app.alerta.RepositorioAlertaMatafuego;
import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.matafuego.Matafuego;

public class AlertaMatafuegoFixture extends Fixture {
	private static String nombre= "Recarga";
	private static String descripcion = "Vencimiento";
	
	public AlertaMatafuegoFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		borrarTabla(executionContext, "AlertaMatafuego");
		List<Empleado> empleados=container.allInstances(Empleado.class);
		List<Matafuego> matafuegos=container.allInstances(Matafuego.class);
		List<AlertaMatafuego> alertaMatafuegos= container.allInstances(AlertaMatafuego.class);
		if (alertaMatafuegos!=null){
			borrarTabla(executionContext,"AlertaMatafuego");
		}
			Date fecha=new Date(System.currentTimeMillis());
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(fecha);
			for (int x = 0; x < 3; x++) {
				calendar.add(Calendar.DAY_OF_YEAR,(100) );
				create(nombre, descripcion, new Date(System.currentTimeMillis()),empleados.get(x+1),matafuegos.get(x+1),calendar.getTime(),null,executionContext);
			}
		
	}

	private AlertaMatafuego create(String nombre, String descripcion,Date fechaAlta, Empleado empleado, Matafuego matafuego,Date contadorAlerta,String estadoAnterior,
			ExecutionContext executionContext) {
		return executionContext.addResult(this,
				repoAlerta.createAlertaMatafuego(nombre, descripcion, empleado, matafuego, contadorAlerta));
	}

	@javax.inject.Inject
	private RepositorioAlertaMatafuego repoAlerta;
	@javax.inject.Inject
	DomainObjectContainer container;
}
