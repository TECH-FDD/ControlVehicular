package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.DomainObjectContainer;

public class Activo extends Estado {

	public Activo(Timestamp fechaCambio, String motivo,
			DomainObjectContainer container) {
		super(fechaCambio, motivo);
		this.container = container;
	}

	@javax.inject.Inject
	DomainObjectContainer container;

	@Override
	public void desactivar() {
		// TODO Auto-generated method stub

	}
}