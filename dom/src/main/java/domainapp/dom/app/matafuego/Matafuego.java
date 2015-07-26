package domainapp.dom.app.matafuego;

import java.sql.Timestamp;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Property;

import domainapp.dom.app.estadoelemento.Activo;
import domainapp.dom.app.estadoelemento.Estado;
import domainapp.dom.app.estadoelemento.Motivo;
import domainapp.dom.app.estadoelemento.ServicioEstado;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Matafuego_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
		+ "FROM domainapp.dom.app.matafuego.Matafuego "),
		@javax.jdo.annotations.Query(name = "buscarPorMarca", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.matafuego.Matafuego "
				+ "WHERE marca.indexOf(:marca) >= 0 ") })
@DomainObject(objectType = "MATAFUEGO", bounded = true)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Matafuego {
	private String marca;
	private String codigo;
	private String descripcion;
	private int capacidad;
	private Timestamp fechaAlta;
	private Timestamp fechaRecarga;
	private Timestamp fechaCadRecarga;
	private Estado estado;
	private ServicioEstado servicioEstado;

	public Matafuego(String marca, String codigo, String descripcion,
			int capacidad, Timestamp fechaAlta, Timestamp fechaRecarga,
			Timestamp fechaCadRecarga) {
		super();
		this.marca = marca;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
		this.fechaAlta = fechaAlta;
		this.fechaRecarga = fechaRecarga;
		this.fechaCadRecarga = fechaCadRecarga;
		this.estado=new Activo(new Timestamp(System.currentTimeMillis()),null);
	}

	public Matafuego() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "10")
	@Column(allowsNull = "false")
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "20")
	@Column(allowsNull = "false")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "30")
	@Column(allowsNull = "false")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "40")
	@Column(allowsNull = "false")
	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "50")
	@Column(allowsNull = "false")
	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Persistent
	@MemberOrder(sequence = "60")
	@Column(allowsNull = "false")
	public Timestamp getFechaRecarga() {
		return fechaRecarga;
	}

	public void setFechaRecarga(Timestamp fechaRecarga) {
		this.fechaRecarga = fechaRecarga;
	}

	@Persistent
	@MemberOrder(sequence = "70")
	@Column(allowsNull = "false")
	public Timestamp getFechaCadRecarga() {
		return fechaCadRecarga;
	}

	public void setFechaCadRecarga(Timestamp fechaCadRecarga) {
		this.fechaCadRecarga = fechaCadRecarga;
	}

	@Persistent
	@MemberOrder(sequence = "90")
	@Column(allowsNull = "false")
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Programmatic
	public ServicioEstado getServicioEstado() {
		return servicioEstado;
	}

	public void setServicioEstado(ServicioEstado servicioEstado) {
		this.servicioEstado = servicioEstado;
	}

	@Override
	public String toString() {
		return "Matafuego " + marca;
	}

	/**
	 * Desactivar un Matafuego, para que el mismo no pueda usarse en un vehiculo.
	 *
	 * @return Matafuego con estado Actualizado.
	 */
	public Matafuego desactivar(@ParameterLayout(named="Motivo") Motivo motivo){
		//Obtengo el nuevo Estado.
//		Estado e= this.getServicioEstado().desactivar(this.getEstado(), new Timestamp(System.currentTimeMillis()), motivo);

		this.setServicioEstado(this.getServicioEstado().obtenerServicio(this.getEstado()));
		Estado e= this.getServicioEstado().desactivar(new Timestamp(System.currentTimeMillis()), motivo);
		//Si el nuevo estado es nulo, quiere decir que no se puede cambiar de estado.
		if (e==null){
			container.informUser("Por algúna razón, el Matafuego seleccionado, ya se encuentra Inactivo. "
					+ "Por favor, revisar el listado de Elementos Inactivos del Sistema.");
			return this;
		}

		//Guardo el anterior estado temporalmente, para eliminarlo de Base de Datos.
		Estado old= this.getEstado();
		this.setEstado(e);

		//Actualizo el Matafuego con el nuevo estado.
		container.persistIfNotAlready(this);

		//Elimino el estado anterior.
		container.removeIfNotAlready(old);

		container.informUser("El Matafuego, ha sido desactivado con exito.");
		return this;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
