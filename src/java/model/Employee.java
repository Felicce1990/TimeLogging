
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
    @NamedQuery(name = Employee.FIND_ALL, query = "select e from Employee e order by e.lastName asc"),
    @NamedQuery(name = Employee.FIND_BY_USERNAME, query = "select e from Employee e where e.username=:username"),
    @NamedQuery(name = Employee.FIND_BY_USERNAME_PASSWORD, query = "select e from Employee e where e.username=:username AND e.password=:password")
})
public class Employee implements Serializable {

    public static final String FIND_ALL = "Employee.findAll";
    public static final String FIND_BY_USERNAME = "Employee.findByUsername";
    public static final String FIND_BY_USERNAME_PASSWORD = "Employee.findByUsernamePassword";

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String title;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String position;
    private int personNumber;
    
    @ManyToOne
    @JoinColumn(name = "DIVISION_ID")
    private Division division;

    public Employee()
    {
    }

    public Employee(String title, String firstname, String lastname, String username, String password, String position, int personNumber, Division division) {
        this.title = title;
        this.firstName = firstname;        
        this.lastName = lastname;
        this.username = username;
        this.password = password;
        this.position = position;
        this.personNumber = personNumber;
        this.division = division;
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

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Model[ id=" + id + " ]";
    }
    
}
