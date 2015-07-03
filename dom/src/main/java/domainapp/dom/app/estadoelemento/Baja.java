package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.DomainObjectContainer;

public class Baja extends Estado {

	public Baja(Timestamp fechaCambio, String motivo,
			DomainObjectContainer container) {
		super(fechaCambio, motivo);
		this.container = container;
	}

	@Override
	public void desactivar() {
		this.container.informUser("El elemento a cambiado de estado");
	}

	@javax.inject.Inject
	DomainObjectContainer container;

}