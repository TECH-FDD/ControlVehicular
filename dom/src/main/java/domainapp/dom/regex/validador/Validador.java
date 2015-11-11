package domainapp.dom.regex.validador;

//import java.util.Calendar;

public class Validador {

	/**
	 * Se valida campos donde solo se admite caracteres de A-Z y numeros del 0 al 9.
	 */
	public static final class ValidacionAlfanumerico{ 	
		private ValidacionAlfanumerico() { }
		public static final String ADMITIDOS = "[a-z,A-Z,0-9,ñ,Ñ, ]+"; 
	}
	
	/**
	 * Se valida campos donde solo se admitiran numeros
	 */
	public static final class ValidacionNumerica{ 	
		private ValidacionNumerica() { }
		public static final String ADMITIDOS = "[0-9]+"; 
	}
	
	/**
	 * Validacion que solo admite numeros del 0 a 9, ademas del signo + para el codigo del pais
	 */
	public static final class ValidacionTel {
		private ValidacionTel() {}
		public static final String ADMITIDOS = "[+]?[0-9 -]*";
	}
	/**
	 * Validacion encargada de admitir una estructura correspondiente al email. La ausencia de
	 * algunos elementos que forman la misma, sera suficiente para indicar que no cumple con el formato
	 * adecuado.
	 */
	public static final class ValidacionEmail {
		private ValidacionEmail() {}
		public static final String ADMITIDOS = "(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+";
	}
	/**
	 * Validacion que simplemente esta encargada de admitir letras minusculas y mayusculas.
	 */
	public static final class ValidacionLetras {
		private ValidacionLetras() {}
		public static final String ADMITIDOS ="[A-Z,a-z,ñ,Ñ, ,]+";
	}
	
}
