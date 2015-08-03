package domainapp.dom.app.mantenimiento;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;

import domainapp.dom.app.taller.Taller;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Mantenimiento_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DomainObject(objectType = "MANTENIMIENTO", bounded = true)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
public class Mantenimiento {
	
	private String titulo;
	private String detalle;
	private String codigo;
	private Timestamp fechaCreacion;
	private Timestamp fechaRealizacion;
	private String elemento;
	private Taller taller;
	private BigDecimal costoRepuesto;
	private BigDecimal costoManoObra;
	private BigDecimal costoTotal;
	private Boolean deRutina;
	//private EstadoMantenimiento estado;
	
	
	public Mantenimiento() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mantenimiento(String titulo, String detalle, String codigo,
			Timestamp fechaCreacion, Timestamp fechaRealizacion,
			String elemento, Taller taller, BigDecimal costoRepuesto,
			BigDecimal costoManoObra, BigDecimal costoTotal, Boolean deRutina) {
		super();
		this.titulo = titulo;
		this.detalle = detalle;
		this.codigo = codigo;
		this.fechaCreacion = fechaCreacion;
		this.fechaRealizacion = fechaRealizacion;
		this.elemento = elemento;
		this.taller = taller;
		this.costoRepuesto = costoRepuesto;
		this.costoManoObra = costoManoObra;
		this.costoTotal = costoTotal;
		this.deRutina = deRutina;
		//this.estado = estado;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "10")
	@Column(allowsNull = "false")
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "20")
	@Column(allowsNull = "true")
	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "30")
	@Column(allowsNull = "true")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "40")
	@Column(allowsNull = "false")
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "50")
	@Column(allowsNull = "false")
	public Timestamp getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Timestamp fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "60")
	@Column(allowsNull = "false")
	public String getElemento() {
		return elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "70")
	@Column(allowsNull = "false")
	public Taller getTaller() {
		return taller;
	}

	public void setTaller(Taller taller) {
		this.taller = taller;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "80")
	@Column(allowsNull = "false")
	public BigDecimal getCostoRepuesto() {
		return costoRepuesto;
	}

	public void setCostoRepuesto(BigDecimal costoRepuesto) {
		this.costoRepuesto = costoRepuesto;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "90")
	@Column(allowsNull = "false")
	public BigDecimal getCostoManoObra() {
		return costoManoObra;
	}

	public void setCostoManoObra(BigDecimal costoManoObra) {
		this.costoManoObra = costoManoObra;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "100")
	@Column(allowsNull = "false")
	public BigDecimal getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(BigDecimal costoTotal) {
		this.costoTotal = costoTotal;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "110")
	@Column(allowsNull = "false")
	public Boolean getDeRutina() {
		return deRutina;
	}

	public void setDeRutina(Boolean deRutina) {
		this.deRutina = deRutina;
	}
	@Override
	public String toString() {
		return "Mantenimiento: "+ titulo ;
	}
	
	
	/*@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "120")
	@Column(allowsNull = "true")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}*/
	
	
}
