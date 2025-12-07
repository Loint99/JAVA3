package com.lab7.entity;

import java.util.Date;

public class Employee {
    private String id;
    private String password;
    private String fullname;
    private String photo;
    private boolean gender;
    private Date birthday;
    private double salary;
    private String email;
    private String departId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public boolean isGender() { return gender; }
    public void setGender(boolean gender) { this.gender = gender; }

    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartId() { return departId; }
    public void setDepartId(String departId) { this.departId = departId; }
}
