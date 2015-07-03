package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.DomainObjectContainer;

public class Inactivo extends Estado {

	public Inactivo(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@javax.inject.Inject
	DomainObjectContainer container;

	@Override
	public void desactivar(Object elemento) {
		// TODO Auto-generated method stub

	}
}