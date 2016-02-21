/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import exception.NoSuchEmployeeException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Employee;

@Stateless
public class EmployeeService 
{
    @PersistenceContext
    private EntityManager em;
    
    
    public void create(Employee entity) 
    {
        em.persist(entity);
    }

    public void update(Employee entity) 
    {
        em.merge(entity);
    }

    public void remove(Employee entity) 
    {
        em.remove(em.merge(entity));
    }

    public Employee find(Object id) 
    {
        return em.find(Employee.class, id);
    }
    
    public Employee findByUserName(String name) throws NoSuchEmployeeException {
        List<Employee> resultList = em.createNamedQuery(Employee.FIND_BY_USERNAME, Employee.class)
                .setParameter("name", name)
                .getResultList();
        if (resultList != null && resultList.size() > 1) {
            throw new IllegalStateException("Employee named " + name + " was found more than once.");
        }
        if (resultList != null && resultList.size() == 1) {
            return resultList.get(0);
        }
        throw new NoSuchEmployeeException("Employee " + name + " doesn't exist.");    
    }
    
    public List<Employee> findAll() {
        return em.createNamedQuery(Employee.FIND_ALL, Employee.class).getResultList();
    }          
    
}
