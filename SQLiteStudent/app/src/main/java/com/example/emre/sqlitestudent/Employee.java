package com.example.emre.sqlitestudent;

/**
 * Created by emre on 5.5.2017.
 */

public class Employee {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private Double gpa;
    private String gender;
    private Long sinif;

    public Employee() {
    }

    public Employee(String name, String surname, Integer age, Double gpa, String gender,Long sinif) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gpa = gpa;
        this.gender = gender;
        this.sinif=sinif;


    }

    public Employee(Long id, String name, String surname, Integer age, Double gpa, String gender, Long sinif) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gpa = gpa;
        this.gender = gender;
        this.sinif=sinif;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getSinif() {
        return sinif;
    }

    public void setSinif(Long sinif ) {
        this.sinif = sinif;
    }




}

