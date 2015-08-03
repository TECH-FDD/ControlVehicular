package domainapp.dom.app.empleado;

import java.sql.Timestamp;
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
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Where;
import org.joda.time.LocalDate;

import domainapp.dom.app.area.Area;
import domainapp.dom.app.persona.Ciudad;
import domainapp.dom.app.persona.Documento;
import domainapp.dom.app.persona.Persona;
import domainapp.dom.app.persona.Provincia;
import domainapp.dom.app.persona.Sexo;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Empleado_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.empleado.Empleado "
				+ "WHERE activo == true"),
		@javax.jdo.annotations.Query(name = "Buscar_Documento", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.Empleado "
				+ "WHERE nroDocumento==:nroDocumento"),
		@javax.jdo.annotations.Query(name = "Buscar_Nombre", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.Empleado "
				+ "WHERE nombre.indexOf(:nombre) >= 0 "),
		@javax.jdo.annotations.Query(name = "Buscar_Apellido", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.Empleado "
				+ "WHERE apellido.indexOf(:apellido) >= 0 "),
		@javax.jdo.annotations.Query(name = "Buscar_Legajo", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.Empleado "
				+ "WHERE legajo.indexOf(:legajo) >= 0 ") })
@DomainObject(objectType = "EMPLEADO")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Empleado extends Persona {
	private String legajo;
	private Area area;
	private boolean activo;

	public Empleado(String nombre, String apellido, Documento tipoDocumento,
			int nroDocumento, LocalDate fechaNacimiento, String domicilio,
			Provincia provincia, Ciudad ciudad, int codigoPostal,
			Timestamp fechaAlta, Sexo sexo, String telefono, String email,
			String legajo, Area area, boolean activo) {
		super(nombre, apellido, tipoDocumento, nroDocumento, fechaNacimiento,
				domicilio, provincia, ciudad, codigoPostal, fechaAlta, sexo,
				telefono, email);
		this.legajo = legajo;
		this.area = area;
		this.activo = activo;
	}

	public Empleado() {
		super();
	}

	@Persistent
	@MemberOrder(sequence = "20")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property(editing = Editing.DISABLED)
	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	@Persistent
	@MemberOrder(sequence = "21")
	@javax.jdo.annotations.Column(allowsNull = "Area")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Property(hidden = Where.EVERYWHERE)
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/**
	 * Desactivar un Empleado, de manera que no pueda realizar acciones en el
	 * sistema.
	 *
	 * @return mensaje de confirmacion.
	 */
	public Empleado desactivarEmpleado() {
		this.setActivo(false);
		container
				.informUser("El Empleado ha sido desactivado de manera exitosa!");
		return this;

	}
	/**
	 * Se desactiva la opcion desactivar Empleado , cuando el empleado ya este
	 * Desactivado
	 */
	@Programmatic
	public boolean hideDesactivarEmpleado() {
		if (isActivo() == false)
			return true;
		else
			return false;
	}
	/**
	 * Modificar la Ciudad actual del Empleado, lo cual puede implicar o no, un
	 * cambio de Provincia, pero si o si, debe implicar un cambio de Codigo
	 * Postal y Direccion.
	 *
	 * @param provincia
	 * @param ciudad
	 * @param codigoPostal
	 * @param domicilio
	 * @return
	 */
	@MemberOrder(sequence = "1", name = "CodigoPostal")
	@ActionLayout(named = "Cambiar Ciudad", position = Position.BELOW)
	public Empleado updateCodigoPostal(
			final @ParameterLayout(named = "Provincia") Provincia provincia,
			final @ParameterLayout(named = "Ciudad") Ciudad ciudad,
			final @ParameterLayout(named = "Codigo Postal") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionNumerica.ADMITIDOS) int codigoPostal,
			final @ParameterLayout(named = "Domicilio") @Parameter(regexPattern = domainapp.dom.regex.validador.Validador.ValidacionAlfanumerico.ADMITIDOS) String domicilio) {
		this.setCiudad(ciudad);
		this.setProvincia(provincia);
		this.setCodigoPostal(codigoPostal);
		this.setDomicilio(domicilio);
		return this;

	}

	/**
	 * Reactivar un Empleado, permitiendo su reincorporación al sistema.
	 *
	 * @return mensaje de confirmacion.
	 */

	public Empleado activarEmpleado() {
		if (getArea().isActivo() == true) {
			this.setActivo(true);
			container.informUser("El Empleado ha sido activado");
			return this;
		} else {
			container
					.informUser("El Area se encuentra inactiva, Ingrese otra Area");
			return this;
		}
	}

	@ActionLayout(named = "Cambiar Area")
	public Empleado updateArea(final @ParameterLayout(named = "Area") Area are) {
		this.setArea(are);
		// container.persistIfNotAlready(this);
		return this;
	}

	public String validateUpdateArea(Area ar) {
		if (ar.isActivo() == false) {
			return "El area esta inactiva. Por favor ingrese otra area.";
		}
		return null;
	}

	/**
	 * Se desactiva la opcion desactivar Empleado , cuando el empleado ya este
	 * Activado
	 */
	@Programmatic
	public boolean hideActivarEmpleado() {
		if (isActivo() == true)
			return true;
		else
			return false;
	}


	/**
	 * Se desactiva el UpdateArea, cuando no sea necesario su actualización
	 */

	@Programmatic
	public boolean hideUpdateArea() {
		if (getArea().isActivo() == false)
			return false;
		else
			return true;
	}

	/**
	 * Mostrar lista de Ciudades según la Provincia seleccionada.
	 *
	 * @param provincia
	 * @param ciudad
	 * @param codigoPostal
	 * @param Domicilio
	 * @return List<Ciudad>
	 */
	public List<Ciudad> choices1UpdateCodigoPostal(final Provincia provincia,
			final Ciudad ciudad, final int codigoPostal, final String Domicilio) {
		return Ciudad.listarPor(provincia);
	}

	@Override
	public String toString() {
		return "Empleado [Legajo N°=" + legajo + "]";
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
