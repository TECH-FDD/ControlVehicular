/*
 *  SIGAFV is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * SIGAFV is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SIGAFV.  If not, see <http://www.gnu.org/licenses/>.
 */
    
package domainapp.dom.app.mantenimiento;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Where;

import domainapp.dom.app.taller.Taller;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Mantenimiento_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "ListarTodos", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.mantenimiento.Mantenimiento "),
		@javax.jdo.annotations.Query(name = "buscarPorTitulo", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.mantenimiento.Mantenimiento "
				+ "WHERE titulo.indexOf(:titulo) >= 0 "),
		@javax.jdo.annotations.Query(name = "buscarPorCodigo", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.dom.app.mantenimiento.Mantenimiento "
				+ "WHERE codigo.indexOf(:codigo) >= 0 ") })
@DomainObject(objectType = "MANTENIMIENTO", bounded = true)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_CHILD)
@MemberGroupLayout(
	     columnSpans={4,4,4,12},
	     left={"General", "Misc"},
	     middle="Detail"
	     
)

public class Mantenimiento {

	private String titulo;
	private String detalle;
	private String codigo;
	private Timestamp fechaCreacion;
	private Timestamp fechaRealizacion;
	private TipoElemento tipoElemento;
	private ObjetoMantenible elemento;
	private Taller taller;
	private BigDecimal costoRepuesto;
	private BigDecimal costoManoObra;
	private BigDecimal costoTotal;
	private Boolean deRutina;

	private EstadoMantenimiento estado;

	public Mantenimiento() {
		super();
	}

	public Mantenimiento(String titulo, String detalle, String codigo,
			Timestamp fechaCreacion, Timestamp fechaRealizacion,
			TipoElemento tipoElemento,ObjetoMantenible elemento,Taller taller, BigDecimal costoRepuesto,
			BigDecimal costoManoObra, BigDecimal costoTotal, Boolean deRutina) {
		super();
		this.titulo = titulo;
		this.detalle = detalle;
		this.codigo = codigo;
		this.fechaCreacion = fechaCreacion;
		this.fechaRealizacion = fechaRealizacion;
		this.tipoElemento = tipoElemento;
		this.elemento= elemento;
		this.taller = taller;
		this.costoRepuesto = costoRepuesto;
		this.costoManoObra = costoManoObra;
		this.costoTotal = costoTotal;
		this.deRutina = deRutina;

		this.estado = new Aceptado(new Timestamp(System.currentTimeMillis()),"Alta de Manenimiento");
	}

	@Persistent
	@Property(editing = Editing.ENABLED)
	@MemberOrder(sequence = "10")
	@Column(allowsNull = "false")
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Persistent
	@Property(editing = Editing.ENABLED)
	@MemberOrder(sequence = "20")
	@Column(allowsNull = "true")
	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	@Persistent
	@Property(editing = Editing.ENABLED)
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
	@Property(editing = Editing.ENABLED)
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
	public TipoElemento getTipoElemento() {
		return tipoElemento;
	}

	public void setTipoElemento(TipoElemento tipoElemento) {
		this.tipoElemento = tipoElemento;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "70")
	@Column(allowsNull = "false")
	public ObjetoMantenible getElemento() {
		return elemento;
	}

	public void setElemento(ObjetoMantenible elemento) {
		this.elemento = elemento;
	}

	@Persistent
	@Property(editing = Editing.ENABLED)
	@MemberOrder(sequence = "80")
	@Column(allowsNull = "false")
	public Taller getTaller() {
		return taller;
	}

	public void setTaller(Taller taller) {
		this.taller = taller;
	}

	@Persistent
	@Property(editing = Editing.ENABLED)
	@MemberOrder(sequence = "90")
	@Column(allowsNull = "false")
	public BigDecimal getCostoRepuesto() {
		return costoRepuesto;
	}

	public void setCostoRepuesto(BigDecimal costoRepuesto) {
		this.costoRepuesto = costoRepuesto;
	}

	@Persistent
	@Property(editing = Editing.ENABLED)
	@MemberOrder(sequence = "100")
	@Column(allowsNull = "false")
	public BigDecimal getCostoManoObra() {
		return costoManoObra;
	}

	public void setCostoManoObra(BigDecimal costoManoObra) {
		this.costoManoObra = costoManoObra;
	}

	@Persistent
	@Property(editing = Editing.ENABLED)
	@MemberOrder(sequence = "110")
	@Column(allowsNull = "false")
	public BigDecimal getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(BigDecimal costoTotal) {
		this.costoTotal = costoTotal;
	}

	@Persistent
	@Property(editing = Editing.ENABLED)
	@MemberOrder(sequence = "120")
	@Column(allowsNull = "true")
	public Boolean getDeRutina() {
		return deRutina;
	}

	public void setDeRutina(Boolean deRutina) {
		this.deRutina = deRutina;
	}

	@Persistent
	@Property(editing = Editing.ENABLED)
	@MemberOrder(sequence = "130")
	@Column(allowsNull = "false")
	public EstadoMantenimiento getEstadoMantenimiento() {
		return estado;
	}

	public void setEstadoMantenimiento(EstadoMantenimiento estado) {
		this.estado = estado;
	}

	@ActionLayout(named="Iniciar")
	public Mantenimiento iniciarProceso(@ParameterLayout(named="Motivo") String motivo) {
		this.getEstadoMantenimiento().iniciarProceso(this,new Timestamp(System.currentTimeMillis()),motivo);
		return this;
	}
	@ActionLayout(named="Aceptado",hidden=Where.NOWHERE)
	public Mantenimiento aceptacion(@ParameterLayout(named="Motivo") String motivo) {
		this.getEstadoMantenimiento().aceptacion(this,new Timestamp(System.currentTimeMillis()),motivo);
		return this;
	}
	
	@ActionLayout(named="Finalizar",hidden=Where.NOWHERE)
	public Mantenimiento finalizarMantenimiento(@ParameterLayout(named="Motivo")  String motivo) {
		this.getEstadoMantenimiento().finalizarMantenimiento(this,new Timestamp(System.currentTimeMillis()),motivo);
		return this;
	}
	
	@ActionLayout(named="Cancelar",hidden=Where.NOWHERE)
	public Mantenimiento cancelarMantenimiento(@ParameterLayout(named="Motivo") String motivo) {
		this.getEstadoMantenimiento().cancelarMantenimiento(this,new Timestamp(System.currentTimeMillis()),motivo);
		return this;
	}

	@Programmatic
	public boolean hideAceptacion(){
		EstadoMantenimiento st=null;
		st =this.getEstadoMantenimiento();
		if (st instanceof Cancelado){
			return false;
		}
		else{
			return true;
		}
	}
	@Programmatic
	public boolean hideIniciarProceso(){
		EstadoMantenimiento st=null;
		st = this.getEstadoMantenimiento();
		if(st instanceof EnProceso || st instanceof Cancelado){
			return true;
		}
		else{
			return false;
		}
	}
	@Programmatic
	public boolean hideFinalizarMantenimiento(){
		EstadoMantenimiento st=null;
		st=this.getEstadoMantenimiento();
		if( st instanceof EnProceso ){
			return false;
		}
		else{
			return true;
		}
	}
	@Programmatic
	public boolean hideCancelarMantenimiento(){
		EstadoMantenimiento st=null;
		st=this.getEstadoMantenimiento();
		if(st instanceof EnProceso){
			return false;
		}else{
			return true;
		}
	}
	
	public String toString() {
		return "Mantenimiento: " + titulo;
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
