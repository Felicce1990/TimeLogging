/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import business.DivisionService;
import business.EmployeeService;
import exception.NoSuchDivisionException;
import exception.NoSuchEmployeeException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Division;
import model.Employee;
import model.Position;

@Named
@SessionScoped
public class EmployeeController implements Serializable {

    private String title;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String position;
    private Division division;
    private int personNumber;
    private Employee currentEmployee;
    private String fullName;

    @EJB
    private EmployeeService service;

    @EJB
    private DivisionService dService;

    public String login() throws NoSuchEmployeeException {
        currentEmployee = service.findByUserNamePassword(username, password);
        FacesContext context = FacesContext.getCurrentInstance();

        if (currentEmployee == null) {
            context.addMessage(null, new FacesMessage("Unbekannter login, erneut versuchen"));
            username = null;
            password = null;
            return null;
        } else {
            switch (currentEmployee.getPosition()) {
                case Position.EMPLOYEE:
                    fillVariables();
                    return "timesheet.xhtml";

                case Position.MANAGER:
                    fillVariables();
                    return "dashboard.xhtml";
            }

        }
        return null;
    }
    
    private void fillVariables()
    {
        this.firstName = currentEmployee.getFirstName();
        this.lastName = currentEmployee.getLastName();
        this.password = currentEmployee.getPassword();
        this.personNumber = currentEmployee.getPersonNumber();
        this.position = currentEmployee.getPosition();
        this.title = currentEmployee.getTitle();
        this.username = currentEmployee.getUsername();
        this.division = currentEmployee.getDivision();
        this.fullName = firstName + " " + lastName;
    }
    
    public String logout()
    {
        currentEmployee = null;
        return "login.xhtml";
    }

    public void addTestUser() throws NoSuchDivisionException {
        Division div = new Division();
        div.setName("Verwaltung");
        dService.create(div);
        System.out.println("### Abteilung hinzugefügt");

        Division d = dService.findByName("Verwaltung");
        System.out.println("### Division: " + d.getName());

        Employee employee = new Employee("Herr", "Felix", "Thomas", "felix", "felix", Position.EMPLOYEE, 123, d);
        service.create(employee);
        System.out.println("### MA angelegt.");
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

}
