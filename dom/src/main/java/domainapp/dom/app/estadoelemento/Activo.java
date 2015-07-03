package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

import org.apache.isis.applib.DomainObjectContainer;

import domainapp.dom.app.matafuego.Matafuego;

public class Activo extends Estado {

	public Activo(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
	}

	@javax.inject.Inject
	DomainObjectContainer container;

	@Override
	public void desactivar(Object elemento, Timestamp fecha, Motivo motivo) {
	}
}