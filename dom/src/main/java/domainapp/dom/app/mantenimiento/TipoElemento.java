package domainapp.dom.app.mantenimiento;


public enum TipoElemento {
	
		VEHICULO , GPS, MATAFUEGO;
		
		
		private String nombre;

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		
	
}
