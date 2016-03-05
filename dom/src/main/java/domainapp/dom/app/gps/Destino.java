/*
 *  SIGAFV is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * SIGAFV is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SIGAFV.  If not, see <http://www.gnu.org/licenses/>.
 */

package domainapp.dom.app.gps;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.util.ObjectContracts;
import org.isisaddons.wicket.gmap3.cpt.applib.Locatable;
import org.isisaddons.wicket.gmap3.cpt.applib.Location;
import org.wicketstuff.gmap.api.GLatLng;
import org.wicketstuff.gmap.geocoder.Geocoder;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "Destino_ID")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DomainObject(objectType = "Destino")
public class Destino implements Locatable, Comparable<Destino> {

	private Double latitud;
	private Double longitud;
	private String direccion;
	private boolean visitado;
	private String descripcion;

	public Destino(final String direccion, final String descripcion) {
		super();
		this.setLocation(direccion);
		this.visitado = false;
		this.descripcion = descripcion;
	}

	@Persistent
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	@Persistent
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	@Persistent
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		setLocation(direccion);
	}

	@Persistent
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Persistent
	@MemberOrder(sequence = "5")
	@Column(allowsNull = "true")
	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	@Override
	@Programmatic
	public Location getLocation() {
		if (latitud == null || longitud == null)
			return new Location();

		return new Location(this.latitud, this.longitud);
	}

	public void setLocation(final String direccion) {
		Geocoder geocoder = new Geocoder();
		try {
			GLatLng glatlng = geocoder.geocode(direccion);
			this.latitud = glatlng.getLat();
			this.longitud = glatlng.getLng();
			this.direccion = direccion;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int compareTo(Destino o) {
		return ObjectContracts.compare(this, o, "descripcion");
	}

	@Override
	public String toString() {
		return "Destino[" + direccion + "]";
	}
}
