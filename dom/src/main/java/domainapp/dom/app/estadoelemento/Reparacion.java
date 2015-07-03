package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.DomainObjectContainer;

public class Reparacion extends Estado {

	public Reparacion(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@javax.inject.Inject
	DomainObjectContainer container;

	@Override
	public void desactivar(Object elemento,Timestamp fecha, Motivo motivo) {
		// TODO Auto-generated method stub

	}
}