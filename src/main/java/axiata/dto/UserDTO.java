/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package axiata.dto;

import java.util.Date;

/**
 *
 * @author admin
 */
public class UserDTO {

    private String username;
    private String email;
    private String employeeNumber;
    private String birthPlace;
    private Date birthDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "username=" + username + ", email=" + email + ", employeeNumber=" + employeeNumber + ", birthPlace=" + birthPlace + ", birthDate=" + birthDate + '}';
    }
    

}
