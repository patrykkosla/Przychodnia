package magazyn;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;

@MappedSuperclass

public abstract class Persone implements Serializable {


    @Basic
    protected String username; 
    
    @Basic
    protected String lastName;

    @Basic
    protected String firstName;
    
    @Basic
    protected String password;

    public Persone(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firsName) {
        this.firstName = firsName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}

