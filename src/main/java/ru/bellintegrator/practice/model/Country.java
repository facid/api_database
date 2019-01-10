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
    private String citizenshipName;

    @Column(name = "code", length = 2)
    private String citizenshipCode;


    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees;

    /**
     * Конструктор для hibernate
     */
    public Country(){

    }

    /**
     * Конструктор для отображения всех стран
     *
     * @param citizenshipName
     * @param citizenshipCode
     */
    public Country(String citizenshipName, String citizenshipCode){
        this.citizenshipName = citizenshipName;
        this.citizenshipCode = citizenshipCode;
    }

    /**
     * Конструктор для сохранения employee
     *
     * @param citizenshipCode
     */
    public Country(String citizenshipCode){
        this.citizenshipCode = citizenshipCode;
    }

    public Long getId() {
        return id;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
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

    @Override
    public String toString(){
        return "{id:" + id + ";name:" + citizenshipName + ";code:" + citizenshipCode + "}";
    }
}
