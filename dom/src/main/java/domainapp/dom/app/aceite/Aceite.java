package domainapp.dom.app.aceite;

import java.sql.Timestamp;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Where;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="Aceite_ID")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER,
        column="version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(
				name = "ListarTodos", language = "JDOQL",
				value = "SELECT "
				+ "FROM domainapp.dom.app.aceite.Aceite "
				+ "WHERE activo == true"),
		@javax.jdo.annotations.Query(
				name = "Buscar_Nombre", language = "JDOQL",
				value = "SELECT "
				+ "FROM domainapp.dom.app.Aceite "
				+ "WHERE nombre.indexOf(:nombre) >= 0 && activo == true"),
		@javax.jdo.annotations.Query(
				name = "Buscar_Marca", language = "JDOQL",
				value = "SELECT "
				+ "FROM domainapp.dom.app.Aceite "
				+ "WHERE marca.indexOf(:marca) >= 0 && activo == true"),
		@javax.jdo.annotations.Query(
				name = "Buscar_Codigo", language = "JDOQL",
				value = "SELECT "
				+ "FROM domainapp.dom.app.Aceite "
				+ "WHERE codigo.indexOf(:codigo) >= 0 && activo == true"),
		@javax.jdo.annotations.Query(
				name = "Buscar_Tipo", language = "JDOQL",
				value = "SELECT "
				+ "FROM domainapp.dom.app.Aceite "
				+ "WHERE tipoAceite.indexOf(:tipoAceite) >= 0 && activo == true")
})

@DomainObject(objectType = "ACEITE",bounded=true)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Aceite {
	private String nombre;
	private String marca;
	private String codigo;
	private String descripcion;
	private TipoAceite tipoAceite;
	private int duracion;
	private Timestamp fechaAlta;
	private boolean activo;

	public Aceite(String nombre, String marca, String codigo,
			String descripcion, TipoAceite tipoAceite, int duracion, Timestamp fechaAlta, boolean activo) {
		super();
		this.nombre = nombre;
		this.marca = marca;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.tipoAceite = tipoAceite;
		this.duracion = duracion;
		this.fechaAlta= fechaAlta;
		this.activo=activo;
	}

	public Aceite() {
		super();
	}

	@MemberOrder(sequence="20")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property(editing=Editing.DISABLED)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@MemberOrder(sequence="10")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property(editing=Editing.DISABLED)
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@MemberOrder(sequence="30")
	@javax.jdo.annotations.Column(allowsNull = "true")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@MemberOrder(sequence="40")
	@javax.jdo.annotations.Column(allowsNull = "true")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@MemberOrder(sequence="50")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property(editing=Editing.DISABLED)
	public TipoAceite getTipoAceite() {
		return tipoAceite;
	}

	public void setTipoAceite(TipoAceite tipoAceite) {
		this.tipoAceite = tipoAceite;
	}

	@MemberOrder(sequence="60")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@MemberOrder(sequence="70")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Property(editing=Editing.DISABLED)
	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Property(hidden=Where.EVERYWHERE)
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return marca + " "+ nombre;
	}

	@ActionLayout(named="Eliminar")
	public Aceite delete(){
		this.container.warnUser("El Aceite, ha sido desactivado con exito.");
		this.setActivo(false);
		return this;
	}

	@Programmatic
	public boolean hideDelete() {
		if (!isActivo())
			return true;
		else
			return false;
	}
	@javax.inject.Inject
    DomainObjectContainer container;
}
