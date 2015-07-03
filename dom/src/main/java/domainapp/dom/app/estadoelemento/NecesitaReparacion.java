package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

public class NecesitaReparacion extends Estado {

	public NecesitaReparacion(Timestamp fechaCambio, Motivo motivo) {
		super(fechaCambio, motivo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desactivar(Object elemento,Timestamp fecha, Motivo motivo) {
		// TODO Auto-generated method stub

	}
}