package domainapp.dom.app.alerta;

import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
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
import org.apache.isis.applib.annotation.ActionLayout.Position;

import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.matafuego.Matafuego;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "AlertaMatafuego_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.alerta.AlertaMatafuego"),
		@javax.jdo.annotations.Query(name = "Buscar_matafuego", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.alerta.AlertaMatafuego "
				+ "WHERE matafuego.indexOf(:matafuego)>= 0") })
@DomainObject(objectType = "ALERTAMATAFUEGO")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class AlertaMatafuego extends Alerta {

	private Matafuego matafuego;
	private Date contadorAlerta;

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
	public Date getContadorAlerta() {
		return contadorAlerta;
	}

	public void setContadorAlerta(Date contadorAlerta) {
		this.contadorAlerta = contadorAlerta;
	}

	@Override
	public String toString() {
		return "AlertaMatafuego: " + getMatafuego().toString();
	}

	public AlertaMatafuego() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlertaMatafuego(String nombre, String descripcion, Date fechaAlta,
			Empleado empleado, Matafuego matafuego, Date contadorAlerta,
			Date fechaModificacion, Empleado empleadoModificacion) {
		super(nombre, descripcion, fechaAlta, empleado);
		this.matafuego = matafuego;
		this.contadorAlerta = contadorAlerta;
	}

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Edit", position = Position.BELOW)
	public ModificacionAlertaMatafuego updateEdit(
			final @ParameterLayout(named = "Empleado Involucrado") Empleado empleado,
			final @ParameterLayout(named = "Nombre") @Parameter(optionality = Optionality.OPTIONAL) String nombre,
			final @ParameterLayout(named = "Descripcion") @Parameter(optionality = Optionality.OPTIONAL) String descripcion,
			final @ParameterLayout(named = "Fecha Alerta") @Parameter(optionality = Optionality.OPTIONAL) Date contadorAlerta) {
		String datosModificados = "";

		if (nombre != null) {
			datosModificados = datosModificados + "nombre: " + this.getNombre();
			this.setNombre(nombre);
		}
		if (descripcion != null) {
			datosModificados = datosModificados + "descripcion: "
					+ this.getDescripcion();
			this.setDescripcion(descripcion);
		}
		if (contadorAlerta != null) {
			datosModificados = datosModificados + "contador Alarma: "
					+ this.getDescripcion();
			this.setContadorAlerta(contadorAlerta);
		}
		ModificacionAlertaMatafuego modificacionAlerta = new ModificacionAlertaMatafuego();
		modificacionAlerta.setAlertaModificacion(this);
		modificacionAlerta.setFechaModificacion(new Date(System
				.currentTimeMillis()));
		modificacionAlerta.setModificacionEmpleado(empleado);
		modificacionAlerta.setDatosModificados(datosModificados);

		container.persistIfNotAlready(modificacionAlerta);
		return modificacionAlerta;
	}

	@Programmatic
	public String validateUpdateEdit(Empleado empleado, String nombre,
			String descripcion, Date fechaAlerta) {
		if (nombre == null && descripcion == null && fechaAlerta == null) {
			return "No se ha ingresado ninguna modificacion, por favor ingrese alguna modificación";
		}
		return null;
	}

	@javax.inject.Inject
	DomainObjectContainer container;

}
