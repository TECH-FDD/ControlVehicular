package domainapp.dom.app.alerta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.empleado.Empleado;
import domainapp.dom.app.vehiculo.Vehiculo;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "AlertaVehiculo_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
		+ "FROM domainapp.dom.app.alerta.AlertaVehiculo") })
@DomainObject(objectType = "ALERTAVEHICULO")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class AlertaVehiculo extends Alerta {
	private Vehiculo vehiculo;
	private Integer kilometrosAlarma;

	@Persistent
	@MemberOrder(sequence = "6")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public Integer getKilometrosAlarma() {
		return kilometrosAlarma;
	}

	public void setKilometrosAlarma(Integer kilometrosAlarma) {
		this.kilometrosAlarma = kilometrosAlarma;
	}

	@Persistent
	@MemberOrder(sequence = "5")
	@javax.jdo.annotations.Column(allowsNull = "Vehiculo")
	@Property(editing = Editing.DISABLED)
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public String toString() {
		return getVehiculo().toString();
	}

	public AlertaVehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlertaVehiculo(String nombre, String descripcion, Date fechaAlta,
			Empleado empleado, Vehiculo vehiculo, Integer kilometrosAlarma) {
		super(nombre, descripcion, fechaAlta, empleado);
		this.vehiculo = vehiculo;
		this.kilometrosAlarma = kilometrosAlarma;
	}

	@MemberOrder(sequence = "1")
	@ActionLayout(named = "Edit", position = Position.BELOW)
	public ModificacionAlertaVehiculo updateEdit(
			final @ParameterLayout(named = "Empleado Involucrado") Empleado empleado,
			final @ParameterLayout(named = "Nombre") @Parameter(optionality = Optionality.OPTIONAL) String nombre,
			final @ParameterLayout(named = "Descripcion") @Parameter(optionality = Optionality.OPTIONAL) String descripcion,
			final @ParameterLayout(named = "Kilometros Alerta") @Parameter(optionality = Optionality.OPTIONAL) Integer kilometrosAlerta) {
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
		if (kilometrosAlerta != null) {
			datosModificados = datosModificados + "Kilometros Alarma: "
					+ this.getKilometrosAlarma();
			this.setKilometrosAlarma(kilometrosAlerta);
		}
		ModificacionAlertaVehiculo modificacionAlerta = new ModificacionAlertaVehiculo();
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
			String descripcion, Integer kilometrosAlerta) {
		if (nombre == null && descripcion == null && kilometrosAlerta == null) {
			return "No se ha ingresado ninguna modificacion, por favor ingrese alguna modificación";
		}
		return null;
	}

	@MemberOrder(sequence = "2")
	@ActionLayout(named = "Lista de Modificaciones", position = Position.BELOW)
	public List<ModificacionAlertaVehiculo> listAllVehiculo() {
		final List<ModificacionAlertaVehiculo> listaModificacionesVehiculo = container
				.allMatches(new QueryDefault<ModificacionAlertaVehiculo>(
						ModificacionAlertaVehiculo.class, "ListarTodos"));
		final List<ModificacionAlertaVehiculo> lista = new ArrayList<ModificacionAlertaVehiculo>();
		for (ModificacionAlertaVehiculo aV : listaModificacionesVehiculo) {
			if (aV.getAlertaModificacion().equals(this)) {
				lista.add(aV);
			}
		}

		if (lista.isEmpty()) {
			this.container.warnUser("No existen Modificaciones en esta alerta");
		}
		return lista;
	}
	@javax.inject.Inject
	DomainObjectContainer container;

}
