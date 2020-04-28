package hibernate.App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.hibernate.Session;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import hibernate.App.dao.PersonaDAO;
import hibernate.App.entity.PersonaEntity;
import hibernate.App.dao.VentaDAO;
import hibernate.App.entity.VentaEntity;
import hibernate.util.DateUtil;

public class TestHibernate {

	public static void main(String[] args) throws ParseException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Scanner scan = new Scanner(System.in);
		PersonaDAO perDAO = new PersonaDAO();
		VentaDAO ventaDAO = new VentaDAO();

		int opcionesMenu = 0;
		opcionesMenu = mostrarMenu(scan);

		while (opcionesMenu != 0) {

			switch (opcionesMenu) {

			case 1:
				listado(perDAO);
				break;
			case 2:
				alta(perDAO, scan);
				break;
			case 3:
				modificar(perDAO, scan);
				break;
			case 4:
				baja(perDAO, scan);
				break;

			case 5:
				venta(ventaDAO, perDAO, scan);
				break;

			case 6:
				listadoVenta(ventaDAO, scan);
				break;

			case 0:
				salir(scan);
				break;
			}
			opcionesMenu = mostrarMenu(scan);

		}

	}

	private static void salir(Scanner scan) {
		// TODO Auto-generated method stub

	}
	// ----------------------------------------- LISTADO DE VENTA ----------------------------------------------
	private static void listadoVenta(VentaDAO ventaDAO, Scanner scan) {
		// TODO Auto-generated method stub

		System.out.println("SISTEMA DE REGISTRO DE VENTAS");
		System.out.println("-----------------------------");

		System.out.println("Ingrese numero ID de persona");
		int personaID = scan.nextInt();
		PersonaEntity perEntity = new PersonaEntity();
		perEntity.setPersonaID(personaID);
		
		List<VentaEntity> ventaList = ventaDAO.listaVenta(perEntity);
		for (VentaEntity ventaEntity : ventaList) {
			System.out.println(" ID | IMPORTE | FECHA  ");

			String fecha = DateUtil.dateToStringVenta(ventaEntity.getFecha());
			System.out.println(ventaEntity.getVentaID() + "       " + ventaEntity.getImporte() + "       " + fecha);
		}

	}
	
	// ----------------------------------------- INGRESAR VENTA ----------------------------------------------
	private static void venta(VentaDAO ventaDAO, PersonaDAO perDAO, Scanner scan) {
		// TODO Auto-generated method stub

		System.out.println("SISTEMA DE VENTAS");
		System.out.println("--------------------");

		PersonaEntity perEntity = new PersonaEntity();
		VentaEntity ventaEntity = new VentaEntity();

		System.out.println("Ingrese numero ID para registrar la venta");
		int personaID = scan.nextInt();
		perEntity = perDAO.buscarPersona(personaID);

		if (perEntity != null) {
			System.out.println("Nombre de ID: " + perEntity.getNombre());

			System.out.println("Ingrese importe de la venta");
			ventaEntity.setIdPersona(perEntity);

			float importe = scan.nextFloat();
			ventaEntity.setImporte(importe);
			
			
			Date fecha = new Date();
			ventaEntity.setFecha(fecha);
			
			ventaDAO.insertVenta(ventaEntity);
			
			System.out.println("La venta se cargo exitosamente");
		} else {

			System.out.println("No existe el ID ingresado" + personaID + "\n");
		}
	}
	// ----------------------------------------- LISTADO DE PERSONAS ----------------------------------------------
	private static void listado(PersonaDAO perDAO) {
		// TODO Auto-generated method stub

		System.out.println("LISTADO");
		System.out.println("-------");
		System.out.println(" NOMBRE | EDAD |  FECHA DE NACIMIENTO  ");

		for (PersonaEntity perEntity : perDAO.listado()) {
			String fechaNacimiento = DateUtil.dateToStringPersona(perEntity.getFechaNacimiento());
			System.out.println(perEntity.getPersonaID() + " " + perEntity.getNombre() + " " + perEntity.getEdad() + " "
					+ fechaNacimiento);

			System.out.println("-----------------------------------------------");

		}
	}
	
	// ----------------------------------------- ALTA DE PERSONAS ----------------------------------------------
	private static void alta(PersonaDAO perDAO, Scanner scan) throws ParseException {
		// TODO Auto-generated method stub

		PersonaEntity perEntity = new PersonaEntity();
		System.out.println("ALTA DE CLIENTES");
		System.out.println("----------------");

		System.out.println("Ingrese nombre");
		String nombre = scan.next();

		System.out.println("Ingrese edad");
		int edad = scan.nextInt();

		System.out.println("Ingrese fecha de naciemiento aaaa-MM-dd");
		String fechaString = scan.next();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaNacimiento = formato.parse(fechaString);

		perEntity.setFechaNacimiento(fechaNacimiento);

		perEntity.setNombre(nombre);
		perEntity.setEdad(edad);
		perEntity.setFechaNacimiento(fechaNacimiento);

		perDAO.insertModificaPersona(perEntity);
		System.out.println("Alta cargada exitosamente");

	}
	// ----------------------------------------- MODIFICAR LISTADO PERSONAS ---------------------------------------------
	private static void modificar(PersonaDAO perDAO, Scanner scan) throws ParseException {
		// TODO Auto-generated method stub

		PersonaEntity perEntity = new PersonaEntity();
		System.out.println("MODIFICACION DEL REGISTRO DE CLIENTES");
		System.out.println("-------------------------------------");

		System.out.println("Ingrese ID a modificar");
		int personaID = scan.nextInt();
		perEntity = perDAO.buscarPersona(personaID);

		System.out.println("Seleccione la opcion que desea modificar");
		System.out.println("Opcion 1 : Nombre");
		System.out.println("Opcion 2 : Edad ");
		System.out.println("Opcion 3 : Fecha de Nacimiento ");
		int opcion = 0;
		opcion = scan.nextInt();

		switch (opcion) {

		case 1:
			System.out.println("Ingrese nuevo nombre");
			String nombreNuevo = scan.next();
			perEntity.setNombre(nombreNuevo);
			perDAO.insertModificaPersona(perEntity);
			System.out.println("El nombre ha sido modificado");
			break;

		case 2:
			System.out.println("Ingrese nueva edad");
			int edadNueva = scan.nextInt();
			perEntity.setEdad(edadNueva);
			perDAO.insertModificaPersona(perEntity);
			System.out.println("La edad ha sido modificada");
			break;

		case 3:
			System.out.println("Ingrese nueva fecha de nacimiento");
			String fechaNueva = scan.next();
			SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
			Date fechaNacimiento = formato.parse(fechaNueva);

			perEntity.setFechaNacimiento(fechaNacimiento);
			perDAO.insertModificaPersona(perEntity);
			System.out.println("La fecha ha sido modificada");
			break;

		default:
			break;
		}
		opcion = mostrarMenu(scan);

		System.out.println("-----------------------------------------------");

	}
	// ----------------------------------------- BAJA DE PERSONAS ----------------------------------------------
	private static void baja(PersonaDAO perDAO, Scanner scan) {
		// TODO Auto-generated method stub

		PersonaEntity perEntity = new PersonaEntity();
		System.out.println("BAJA DE CLIENTES");
		System.out.println("----------------");

		System.out.println("Ingrese ID");
		int personaID = scan.nextInt();
		perEntity = perDAO.buscarPersona(personaID);

		perDAO.deletePersona(perEntity);

		System.out.println("El registro ha sido eliminado");
		System.out.println("-----------------------------------------------");

	}
	// ----------------------------------------- MENU ----------------------------------------------
	private static int mostrarMenu(Scanner scan) {
		// TODO Auto-generated method stub
		System.out.println("BIENVENIDO AL SISTEMA DE BASE DE DATOS");
		System.out.println("---------------------------------------------");

		System.out.println("1) Listado");
		System.out.println("2) Dar de alta");
		System.out.println("3) Modificar usuario");
		System.out.println("4) Dar de baja");
		System.out.println("5) Venta");
		System.out.println("6) Listado de Ventas");
		System.out.println("0) Salir");
		int opMenu = 0;
		opMenu = scan.nextInt();
		return opMenu;
	}

}
