package domainapp.dom.app.gps;

import java.sql.Timestamp;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Persistent;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;

@javax.jdo.annotations.PersistenceCapable
public class BajaGps {

	private Gps gps;
	private Timestamp fechaBaja;
	private String razonBaja;

	@Persistent
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Timestamp getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Timestamp fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@Persistent
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public String getRazonBaja() {
		return razonBaja;
	}

	public void setRazonBaja(String razonBaja) {
		this.razonBaja = razonBaja;
	}

	@Persistent
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "Gps")
	public Gps getGps() {
		return gps;
	}

	public void setGps(Gps gps) {
		this.gps = gps;
	}

	public BajaGps(Gps gps, Timestamp fechaBaja, String razonBaja) {
		super();
		this.gps = gps;
		this.fechaBaja = fechaBaja;
		this.razonBaja = razonBaja;
	}

	@Override
	public String toString() {
		return razonBaja;
	}

	public BajaGps() {
		super();
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
