package metier;
import java.util.List;  
import javax.persistence.EntityManager;  
import javax.persistence.PersistenceContext;  
import javax.persistence.criteria.CriteriaQuery;  
public abstract class GenericDAO<T> {  
     private final static String UNIT_NAME = "magasin";  
     @PersistenceContext(unitName = UNIT_NAME)  
     private EntityManager em;  
     private Class<T> entityClass;  
     public GenericDAO(Class<T> entityClass) {  
          this.entityClass = entityClass;  
     }  
     public void save(T entity) {  
          em.persist(entity);  
<<<<<<< HEAD
          em.flush();  
		  system.out.println("juste pour tester");
		  
=======
          //em.flush();  
>>>>>>> dff225b517f8ec88dd4177db6b97fb24ba63b8c6
     }  
	 
     public void update(T entity) {  
          em.merge(entity);  
     }  
     public T find(Long entityID) {  
          return em.find(entityClass, entityID);  
     }  
     @SuppressWarnings({ "unchecked", "rawtypes" })  
     public List<T> findAll() {  
          CriteriaQuery cq = em.getCriteriaBuilder().createQuery();  
          cq.select(cq.from(entityClass));  
          return em.createQuery(cq).getResultList();  
     }  
}  