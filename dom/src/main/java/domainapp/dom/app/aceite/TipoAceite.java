package domainapp.dom.app.aceite;

public enum TipoAceite {
	Sintetico ("Sintético"), SemiSintetico("Semi-Sintético"), Mineral("Mineral");

	private String nombre;

	TipoAceite(String nombre){
		this.nombre=nombre;
	}

	public String getNombre(){
		return this.nombre;
	}

	public String toString(){
		return nombre;
	}
}
