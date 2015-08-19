package domainapp.dom.app.alerta;

import java.util.Date;
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

import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.matafuego.Matafuego;

@DomainService(repositoryFor = AlertaMatafuego.class)
@DomainServiceLayout(menuOrder = "120", named = "Alerta")
public class RepositorioAlertaMatafuego {
	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Crear Nueva Alerta Matafuego")
	public AlertaMatafuego createAlertaMatafuego(
			
			final @ParameterLayout(named = "Nombre") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String nombre,
			final @ParameterLayout(named = "Descripcion") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS,optionality = Optionality.OPTIONAL) String descripcion,
			final @ParameterLayout(named = "Creado Por: ") Empleado empleado,
			final @ParameterLayout(named = "Matafuego")Matafuego matafuego,
			final @ParameterLayout(named="Fecha Alerta")Date contadorAlerta)
			 {

		final AlertaMatafuego alertaMatafuego = container
				.newTransientInstance(AlertaMatafuego.class);
		alertaMatafuego.setNombre(nombre);
		alertaMatafuego.setDescripcion(descripcion);
		alertaMatafuego.setEmpleado(empleado);
		alertaMatafuego.setMatafuego(matafuego);
		alertaMatafuego.setFechaAlta(new Date(System.currentTimeMillis()));
		alertaMatafuego.setContadorAlerta(contadorAlerta);
		
		
		container.persistIfNotAlready(alertaMatafuego);
		return alertaMatafuego;
	}

	// Validar contadorAlerta
	public String validateCreateAlertaMatafuego(String nombre, String detalle,
			Empleado empleado,Matafuego matafuego,Date contadorAlerta) {

			if (contadorAlerta.before(new Date(System.currentTimeMillis())))
				return "La fecha de la Alerta ingresado, debe ser posterior a la fecha actual";
	
		return null;
	}
	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Listar todos")
	public List<AlertaMatafuego> listAll() {
		List<AlertaMatafuego> lista = this.container
				.allMatches(new QueryDefault<AlertaMatafuego>(AlertaMatafuego.class,
						"ListarTodos"));
		if (lista.isEmpty()) {
			this.container.warnUser("No hay alertas de matafuegos cargadas en el sistema");
		}
		return lista;
	}
	@javax.inject.Inject
	DomainObjectContainer container;
}
