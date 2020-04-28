package hibernate.App.dao;

import java.util.List;
import hibernate.App.entity.PersonaEntity;
import hibernate.App.entity.VentaEntity;
import java.util.ArrayList;
import org.hibernate.Session;
import hibernate.App.HibernateUtil;

public class VentaDAO {



	public void insertVenta(VentaEntity ventEntity) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(ventEntity);
		session.getTransaction().commit();

		HibernateUtil.shutdown();

	}

	@SuppressWarnings("unchecked")

	public List<VentaEntity> listaVenta(PersonaEntity perEntity) {

		Session sesion = HibernateUtil.getSessionFactory().openSession();

		List<VentaEntity> ventaList = new ArrayList<VentaEntity>();

		ventaList = sesion.createQuery("FROM VentaEntity WHERE IDPERSONA=" + perEntity.getPersonaID()).list();

		sesion.close();

		HibernateUtil.shutdown();
		return ventaList;
	}

}

