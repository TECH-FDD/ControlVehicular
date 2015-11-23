package domainapp.dom.app.mantenimiento;

import java.sql.Timestamp;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;

@PersistenceCapable
@Discriminator(strategy=DiscriminatorStrategy.CLASS_NAME)
public abstract class EstadoMantenimiento {

	private Timestamp fechaCambio;
	private String motivo;

	public EstadoMantenimiento(Timestamp fechaCambio, String motivo) {
		super();
		this.fechaCambio = fechaCambio;
		this.motivo = motivo;
	}

	public EstadoMantenimiento() {
		super();
	}
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Timestamp getFechaCambio() {
		return fechaCambio;
	}
	public void setFechaCambio(Timestamp fechaCambio) {
		this.fechaCambio = fechaCambio;
	}
	
	@Persistent
	@Property(editing = Editing.DISABLED)
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	
	public abstract void iniciarProceso(Mantenimiento m,Timestamp fechaCambio,String motivo);
	public abstract void aceptacion(Mantenimiento m,Timestamp fechaCambio,String motivo);
	public abstract void finalizarMantenimiento(Mantenimiento m,Timestamp fechaCambio,String motivo);
	public abstract void cancelarMantenimiento(Mantenimiento m,Timestamp fechaCambio,String motivo);
	

	
}
