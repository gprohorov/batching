package edu.pro.batching.model;
/*
  @author   george
  @project   batching
  @class  Employee
  @version  1.0.0 
  @since 18.04.22 - 10.36
*/

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class EmployeeDetail {
    private String name;
    private String email;
    private String country;
    private String team;

    public EmployeeDetail() {
    }

    public EmployeeDetail(String name, String email, String country, String team) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "EmployeeDetail{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
