package ec.edu.ups.sd64.evarest.dao;

import java.util.List;

import ec.edu.ups.sd64.evarest.model.General;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class GeneralDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(General gen) {
		em.persist(gen);
	}
	
	public void update(General gen) {
		em.merge(gen);
	}
	
	public void remove(int codigo) {
		General gen = em.find(General.class, codigo);
		if(gen != null) {
			em.remove(gen);
		}
	}
	
	public General read(int id) {
		return em.find(General.class, id);
	}
	
	public List<General> getAll(){
		String jpql = "SELECT g FROM General g";
		Query q = em.createQuery(jpql,General.class);
		return q.getResultList();
	}
	
	public General getPorExtra(String caracteristica) {
		String jpql = "SELECT g FROM General g WHERE g.caracteristica = :caracteristica";
		Query q = em.createQuery(jpql, General.class);
        q.setParameter("caracteristica", caracteristica);
        List<General> generales = q.getResultList();
        if (!generales.isEmpty()) {
            return generales.get(0);
        }
        return null;
		
	}

}
