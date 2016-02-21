/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import business.DivisionService;
import business.EmployeeService;
import exception.NoSuchDivisionException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Division;
import model.Employee;

@Named
@SessionScoped
public class EmployeeController implements Serializable {

    private String name;
    private String username;
    private String password;
    private String role;
    private Division division;

    @EJB
    private EmployeeService service;

    @EJB
    private DivisionService dService;

    @PostConstruct
    public void init() {
        // new Employee for Login

        Division division = new Division();
        division.setName("Verwaltung");
        dService.create(division);
        System.out.println("### Abteilung hinzugefügt");

        try {
            Division d = dService.findByName("Verwaltung");
            System.out.println("### Division: " + d.getName());
            Employee employee = new Employee("Felix Thomas", "felix", "felix", "mitarbeiter", 123, d);
            service.create(employee);

        } catch (NoSuchDivisionException ex) {
            System.out.println("### No Division found");
        }
        
        System.out.println("### postcontruct: Added a Division and an Employee");
    }

    public void findAll() {

        List<Employee> employee = service.findAll();
        for (int i = 0; i < employee.size(); i++) {
            System.out.println("###" + employee.get(i).getLastName());
        }
    }

    public void test() {
        System.out.println("### Test Methode.");
    }

}
