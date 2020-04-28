package hibernate.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String dateToStringVenta(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String fechaActual = formato.format(fecha);
		return fechaActual;
	}
	public static String dateToStringPersona(Date fechaNacimiento) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fechaString= formato.format(fechaNacimiento);
		return fechaString;
	}
}
