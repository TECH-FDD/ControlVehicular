package domainapp.dom.app.alerta;

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

import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.vehiculo.Vehiculo;

@DomainService(repositoryFor = AlertaVehiculo.class)
@DomainServiceLayout(menuOrder = "120", named = "Alerta")
public class RepositorioAlertaVehiculo {
	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear Nueva Alerta Vehiculo")
	public AlertaVehiculo createAlertaVehiculo(

			final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombre,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String descripcion,
			final @ParameterLayout(named = "Creado Por: ") Empleado empleado,
			final @ParameterLayout(named = "Vehiculo") Vehiculo vehiculo,
			final @ParameterLayout(named = "kilometros Alarma") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) int kilometrosAlarma) {

		final AlertaVehiculo alertaVehiculo = new AlertaVehiculo(nombre,
				descripcion, new Date(System.currentTimeMillis()), empleado,
				vehiculo, kilometrosAlarma);

		container.persistIfNotAlready(alertaVehiculo);
		return alertaVehiculo;
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<AlertaVehiculo> listAll() {
		List<AlertaVehiculo> lista = this.container
				.allMatches(new QueryDefault<AlertaVehiculo>(
						AlertaVehiculo.class, "ListarTodos"));
		if (lista.isEmpty()) {
			this.container
					.warnUser("No hay alertas de vehiculos cargadas en el sistema");
		}
		return lista;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
