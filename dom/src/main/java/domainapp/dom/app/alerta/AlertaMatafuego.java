package domainapp.dom.app.alerta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.ActionLayout.Position;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.actinvoc.ActionInvocationContext;

import domainapp.dom.app.Estadoalerta.EstadoAlerta;
import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.matafuego.Matafuego;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "AlertaMatafuego_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.alerta.AlertaMatafuego"),
		@javax.jdo.annotations.Query(name = "Buscar_matafuego", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.alerta.AlertaMatafuego " + "WHERE matafuego.indexOf(:matafuego)>= 0") })

@DomainObject(objectType = "Alerta Matafuego")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class AlertaMatafuego extends Alerta {

	private Matafuego matafuego;
	private Date fechaAlerta;

	@Persistent
	@MemberOrder(sequence = "21")
	@javax.jdo.annotations.Column(allowsNull = "Matafuego")
	@Property(editing = Editing.DISABLED)
	public Matafuego getMatafuego() {
		return matafuego;
	}

	public void setMatafuego(Matafuego matafuego) {
		this.matafuego = matafuego;
	}

	@Persistent
	@MemberOrder(sequence = "22")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public Date getFechaAlerta() {
		return fechaAlerta;
	}

	public void setFechaAlerta(Date fechaAlerta) {
		this.fechaAlerta = fechaAlerta;
	}

	@Override
	public String toString() {
		return "AlertaMatafuego: " + getMatafuego().toString();
	}

	public AlertaMatafuego() {
		super();
	}

	public AlertaMatafuego(String nombre, String descripcion, Date fechaAlta, Empleado empleado, Matafuego matafuego,
			Date fechaAlerta, EstadoAlerta estado, String estadoAnterior) {
		super(nombre, descripcion, fechaAlta, empleado, estado, estadoAnterior);
		this.matafuego = matafuego;
		this.fechaAlerta = fechaAlerta;
	}

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Edit", position = Position.BELOW)
	public ModificacionAlertaMatafuego updateEdit(
			final @ParameterLayout(named = "Empleado Involucrado") Empleado empleado,
			final @ParameterLayout(named = "Nombre") @Parameter(optionality = Optionality.OPTIONAL) String nombre,
			final @ParameterLayout(named = "Descripcion") @Parameter(optionality = Optionality.OPTIONAL) String descripcion,
			final @ParameterLayout(named = "Fecha Alerta") @Parameter(optionality = Optionality.OPTIONAL) Date fechaAlerta) {
		String datosModificados = "";
		if (nombre != null) {
			datosModificados = datosModificados + "nombre: " + this.getNombre();
			this.setNombre(nombre);
		}
		if (descripcion != null) {
			datosModificados = datosModificados + "descripcion: " + this.getDescripcion();
			this.setDescripcion(descripcion);
		}
		if (fechaAlerta != null) {
			EstadoAlerta estado = repo.asignarAlertaEstado(fechaAlerta);
			datosModificados = datosModificados + "contador Alarma: " + this.getDescripcion();
			this.setFechaAlerta(fechaAlerta);
			this.setEstadoAlerta(estado);
		}
		ModificacionAlertaMatafuego modificacionAlerta = new ModificacionAlertaMatafuego();
		modificacionAlerta.setAlertaModificacion(this);
		modificacionAlerta.setFechaModificacion(new Date(System.currentTimeMillis()));
		modificacionAlerta.setModificacionEmpleado(empleado);
		modificacionAlerta.setDatosModificados(datosModificados);

		container.persistIfNotAlready(modificacionAlerta);
		return modificacionAlerta;
	}

	@Programmatic
	public String validateUpdateEdit(Empleado empleado, String nombre, String descripcion, Date fechaAlerta) {
		if (nombre == null && descripcion == null && fechaAlerta == null) {
			return "No se ha ingresado ninguna modificacion, por favor ingrese alguna modificación";
		}
		return null;
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Lista de Modificaciones", position = Position.BELOW)
	public List<ModificacionAlertaMatafuego> listAllMatafuego() {
		final List<ModificacionAlertaMatafuego> listaModificacionAlertaMatafuego = container.allMatches(
				new QueryDefault<ModificacionAlertaMatafuego>(ModificacionAlertaMatafuego.class, "ListarTodos"));
		final List<ModificacionAlertaMatafuego> lista = new ArrayList<ModificacionAlertaMatafuego>();
		for (ModificacionAlertaMatafuego aV : listaModificacionAlertaMatafuego) {
			if (aV.getAlertaModificacion().equals(this)) {
				lista.add(aV);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existen Modificaciones en esta alerta");
		}
		return lista;
	}

	@MemberOrder(sequence = "3")
	@ActionLayout(named = "Aplazar", position = Position.BELOW)
	public AlertaMatafuego Aplazar() {
		getEstadoAlerta().aplazarAlertas(this);
		return this;
	}

	@javax.inject.Inject
	DomainObjectContainer container;

	@javax.inject.Inject
	public ActionInvocationContext actionInvocationContext;

	@javax.inject.Inject
	RepositorioAlertaMatafuego repo;
}
