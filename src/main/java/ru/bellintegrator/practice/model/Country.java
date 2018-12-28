package ru.bellintegrator.practice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

/**
 * Страна
 */

@Entity
@Table
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "code", length = 2)
    private String code;


    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees;

    /**
     * Конструктор для hibernate
     */
    public Country(){

    }

    public Country(String name, String code){
        this.name = name;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Получить employees
     *
     * @return
     */
    public Set<Employee> getEmployees(){
        if (employees == null){
            employees = new HashSet<>();
        }
        return employees;
    }

    /**
     * Добавить employee
     *
     * @param employee
     */
    public void addEmployee(Employee employee){
        getEmployees().add(employee);
        employee.setCountry(this);
    }

    /**
     * Удалить employee
     *
     * @param employee
     */
    public void removeEmployee(Employee employee){
        getEmployees().remove(employee);
        employee.setCountry(null);
    }
}
