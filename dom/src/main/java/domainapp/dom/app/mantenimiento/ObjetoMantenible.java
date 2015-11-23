package domainapp.dom.app.mantenimiento;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

@PersistenceCapable
@DomainObject(objectType = "OBJETOMANTENIBLE", bounded = true)
@Inheritance(strategy= InheritanceStrategy.NEW_TABLE)

public abstract class ObjetoMantenible {

	public ObjetoMantenible() {
		super();
	}

	@Override
	public String toString() {
		return "";
	}
}
