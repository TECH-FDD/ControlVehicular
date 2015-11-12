package domainapp.dom.app.area;

import java.sql.Timestamp;
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
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.app.empleado.Empleado;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Area_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.area.Area " 
				+ "WHERE activo == true"),
		@javax.jdo.annotations.Query(name = "ListarInactivos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.area.Area "
				+ "WHERE activo == false"),
		@javax.jdo.annotations.Query(name = "buscarPorNombre", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.area.Area "
				+ "WHERE nombre.indexOf(:nombre) >= 0 && activo == true"),
		@javax.jdo.annotations.Query(name = "buscarPorCodigo", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.area.Area "
				+ "WHERE codigoArea.indexOf(:codigoArea) >= 0 && activo == true") })
@DomainObject(objectType = "AREA", bounded = true)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Area {

	private String codigoArea;
	private String nombre;
	private String descripcion;
	private Timestamp fechaAlta;
	private boolean activo;

	public Area(String codigoArea, String nombre, String descripcion,
			Timestamp fechaAlta, boolean activo) {
		super();
		this.codigoArea = codigoArea;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.activo = activo;
	}

	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Persistent
	@MemberOrder(sequence = "10")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "20")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Persistent
	@MemberOrder(sequence = "30")
	@javax.jdo.annotations.Column(allowsNull = "true")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "40")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Property(hidden = Where.EVERYWHERE)
	@MemberOrder(sequence = "50")
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@ActionLayout(named = "Eliminar area")
	public Area deleteArea() {

		boolean band = true;
		List<Empleado> lista = this.container
				.allMatches(new QueryDefault<Empleado>(Empleado.class,
						"ListarTodos"));
		for (Empleado e : lista) {
			if (e.getArea().equals(this)) {
				band = false;
			}
		}
		if (band == true) {
			this.setActivo(false);
			this.container
					.informUser("El area ha sido eliminado de manera exitosa");
		} else {
			this.container
					.warnUser("No se pudo realizar esta acción. El objeto que intenta eliminar esta asignado");
		}
		return this;
	}

	@javax.inject.Inject
	DomainObjectContainer container;

	@Override
	public String toString() {
		return "Area " + nombre;
	}

}
