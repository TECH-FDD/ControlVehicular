package domainapp.dom.app.estadoelemento;

import java.sql.Timestamp;

public interface IEstado {
	public void desactivar(Object elemento, Timestamp fecha, Motivo motivo);
}