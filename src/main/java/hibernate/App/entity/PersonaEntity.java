package hibernate.App.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PERSONA", uniqueConstraints = { @UniqueConstraint(columnNames = "IDPERSONA") })

public class PersonaEntity implements Serializable {
	private static final long serialVersionUID = -1798070786993154676L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPERSONA", unique = true, nullable = false)
	private Integer personaID;

	@Column(name = "NOMBRE", unique = false, nullable = false, length = 100)
	private String nombre;

	@Column(name = "EDAD", unique = false, nullable = false, length = 100)
	private int edad;

	@Column(name = "FECHA_NACIMIENTO", unique = false, nullable = false, length = 100)
	private Date fechaNacimiento;

	public Integer getPersonaID() {
		return personaID;
	}

	public void setPersonaID(Integer personaID) {
		this.personaID = personaID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
