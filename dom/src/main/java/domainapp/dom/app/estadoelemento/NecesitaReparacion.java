package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

public class NecesitaReparacion extends Estado {

	public NecesitaReparacion(Timestamp fechaCambio, String motivo) {
		super(fechaCambio, motivo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desactivar() {
		// TODO Auto-generated method stub

	}
}