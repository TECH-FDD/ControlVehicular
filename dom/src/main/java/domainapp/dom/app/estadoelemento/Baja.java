package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.DomainObjectContainer;

public class Baja extends Estado {

	public Baja(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@Override
	public void desactivar(Object elemento,Timestamp fecha, Motivo motivo) {
		this.container.informUser("El elemento a cambiado de estado");
	}

	@javax.inject.Inject
	DomainObjectContainer container;

}