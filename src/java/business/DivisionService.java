/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import exception.NoSuchDivisionException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Division;

/**
 *
 * @author fthom
 */
@Stateless
public class DivisionService {
    
    
    @PersistenceContext
    private EntityManager em;
    
    
    public void create(Division entity) 
    {
        em.persist(entity);
    }

    public void update(Division entity) 
    {
        em.merge(entity);
    }

    public void remove(Division entity) 
    {
        em.remove(em.merge(entity));
    }

    public Division find(Object id) 
    {
        return em.find(Division.class, id);
    }
        
    public Division findByName(String name) throws NoSuchDivisionException {
        List<Division> resultList = em.createNamedQuery(Division.FIND_BY_NAME, Division.class)
                .setParameter("name", name)
                .getResultList();
        if (resultList != null && resultList.size() > 1) {
            throw new IllegalStateException("Division named " + name + " was found more than once.");
        }
        if (resultList != null && resultList.size() == 1) {
            return resultList.get(0);
        }
        throw new NoSuchDivisionException("Division " + name + " doesn't exist.");    
    }
    
    public List<Division> findAll() {
        return em.createNamedQuery(Division.FIND_ALL, Division.class).getResultList();
    }          
}
