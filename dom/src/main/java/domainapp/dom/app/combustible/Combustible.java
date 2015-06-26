package domainapp.dom.app.combustible;

import java.math.BigDecimal;

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

import domainapp.dom.app.combustible.TipoCombustible;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Combustible_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.combustible.Combustible "
				+ "WHERE activo == true"),
		@javax.jdo.annotations.Query(name = "ListarInactivos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.combustible.Combustible "
				+ "WHERE activo == false"),
		@javax.jdo.annotations.Query(name = "buscarPorNombre", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.combustible.Combustible "
				+ "WHERE nombre.indexOf(:nombre) >= 0 && activo == true"),
		@javax.jdo.annotations.Query(name = "buscarPorCodigo", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.combustible.Combustible "
				+ "WHERE codigo.indexOf(:codigo) >= 0 && activo == true") })
@DomainObject(objectType = "COMBUSTIBLE",bounded=true)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Combustible {

	private String nombre;
	private String empresa;
	private String codigo;
	private String descripcion;
	private String categoria;
	private BigDecimal precioLitro;
	private BigDecimal precioAnterior;
	private BigDecimal porcentajeAumento;
	private int octanaje;
	private TipoCombustible tipoCombustible;
	private boolean activo;

	public Combustible() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Combustible(String nombre, String empresa, String codigo,
			String descripcion, String categoria, BigDecimal precioLitro,
			BigDecimal precioAnterior, BigDecimal porcentajeAumento,
			int octanaje, TipoCombustible tipoCombustible) {
		super();
		this.nombre = nombre;
		this.empresa = empresa;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.precioLitro = precioLitro;
		this.precioAnterior = precioAnterior;
		this.porcentajeAumento = porcentajeAumento;
		this.octanaje = octanaje;
		this.tipoCombustible = tipoCombustible;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "10")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "20")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "30")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Persistent
	@MemberOrder(sequence = "40")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Persistent
	@MemberOrder(sequence = "50")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Persistent
	@MemberOrder(sequence = "60")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public BigDecimal getPrecioLitro() {
		return precioLitro;
	}

	public void setPrecioLitro(BigDecimal precioLitro) {
		this.precioLitro = precioLitro;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "70")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public BigDecimal getPrecioAnterior() {
		return precioAnterior;
	}

	public void setPrecioAnterior(BigDecimal precioAnterior) {
		this.precioAnterior = precioAnterior;
	}

	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "80")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public BigDecimal getPorcentajeAumento() {
		return porcentajeAumento;
	}

	public void setPorcentajeAumento(BigDecimal porcentajeAumento) {
		this.porcentajeAumento = porcentajeAumento;
	}

	@Persistent
	@MemberOrder(sequence = "90")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public int getOctanaje() {
		return octanaje;
	}

	public void setOctanaje(int octanaje) {
		this.octanaje = octanaje;
	}

	@Persistent
	@MemberOrder(sequence = "100")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public TipoCombustible getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(TipoCombustible tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	@Property(editing=Editing.DISABLED)
	@MemberOrder(sequence="110")
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	@ActionLayout(named="Eliminar combustible")
	public Combustible deleteCombustible() {
		this.setActivo(false);
		this.container.informUser("El combustible ha sido eliminado de manera exitosa");
		return this;
	}
	@Override
	public String toString() {
		return "Combustible: " + nombre;
	}

	@javax.inject.Inject
	DomainObjectContainer container;

}