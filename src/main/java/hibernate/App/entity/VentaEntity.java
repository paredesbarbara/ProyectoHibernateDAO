package hibernate.App.entity;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "VENTA", uniqueConstraints = { @UniqueConstraint(columnNames = "IDVENTAS") })

public class VentaEntity implements Serializable {

	private static final long serialVersionUID = -1798070786993154676L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDVENTAS", unique = true, nullable = false)
	private Integer ventaID;

	@Column(name = "FECHA", unique = false, nullable = false, length = 100)
	private Date fecha;

	@Column(name = "IMPORTE", unique = false, nullable = false, length = 100)
	private float importe;

	@ManyToOne
	@JoinColumn(name = "IDPERSONA", unique = true, nullable = false)
	private PersonaEntity idPersona;

	public Integer getVentaID() {
		return ventaID;
	}

	public void setVentaID(Integer ventaID) {
		this.ventaID = ventaID;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public PersonaEntity getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(PersonaEntity idPersona) {
		this.idPersona = idPersona;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
