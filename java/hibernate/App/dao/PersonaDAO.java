package hibernate.App.dao;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import hibernate.App.entity.PersonaEntity;
import hibernate.App.HibernateUtil;

public class PersonaDAO {

	// insertar o modificar persona

	public void insertModificaPersona(PersonaEntity perEntity) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(perEntity);
		session.delete(perEntity);
		session.getTransaction().commit();

		HibernateUtil.shutdown();

	}

	// get persona
	public PersonaEntity buscarPersona(int personaID) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		PersonaEntity perEntity = (PersonaEntity) session.createQuery(" FROM PersonaEntity WHERE IDPERSONA=" + personaID)
				.uniqueResult();
		HibernateUtil.shutdown();
		return perEntity;
	}

	// dar de baja personas
	public void deletePersona(PersonaEntity perEntity) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(perEntity);
		session.getTransaction().commit();

		HibernateUtil.shutdown();
	}

	
	// listar personas
	
	@SuppressWarnings("unchecked")
		public List<PersonaEntity> listado() {

			Session sesion = HibernateUtil.getSessionFactory().openSession();

			List<PersonaEntity> listarPersona = new ArrayList<PersonaEntity>();

			listarPersona = sesion.createQuery("From PersonaEntity").list();
			
			sesion.close();

			HibernateUtil.shutdown();
			return listarPersona;
	}}
