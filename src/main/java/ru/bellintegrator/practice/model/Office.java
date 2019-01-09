package ru.bellintegrator.practice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

/**
 * Офис
 */

@Entity
@Table
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "org_id", nullable = false, insertable = false, updatable = false)
    private Long orgId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "is_active")
    private Boolean isActive;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees;

    /**
     * Конструктор для hibernate
     */
    public Office(){

    }

    /**
     * Конструктор для update()
     */
    public Office(Long id, String name, String phone, String address, Boolean isActive){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
    }

    /**
     * Конструктор для save()
     */
    public Office(Long id, Long orgId, String name, String phone, String address, Boolean isActive){
        this.id = id;
        this.orgId = orgId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isActive = isActive;
    }

    /**
     * Конструктор для filter()
     */
    public Office(Long id, String name, Boolean isActive){
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId){
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsActive(){
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Получить organization
     *
     * @return
     */
    public Organization getOrganization(){
        return organization;
    }

    /**
     * Изменить organization
     *
     * @param organization
     */
    public void setOrganization(Organization organization){
        this.organization = organization;
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
    public void addEmpolyee(Employee employee){
        getEmployees().add(employee);
        employee.setOffice(this);
    }

    /**
     * Удалить employee
     *
     * @param employee
     */
    public void removeEmployee(Employee employee){
        getEmployees().remove(employee);
        employee.setOffice(null);
    }

    @Override
    public String toString(){
        return "{id:" + id + ";orgId:" + orgId + ";name:" + name + ";address:" +
                address + ";phone:" + phone + ";is_active:" + isActive + "}";
    }
}
