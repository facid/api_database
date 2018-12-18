package practice.model;

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
 * Организация
 */
@Entity
@Table
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "inn", length = 10, nullable = false)
    private String inn;

    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Office> offices;

    /**
     * Конструктор для hibernate
     */
    public Organization(){

    }

    public Organization(Long id, String name, String fullName, String address, String phone, String inn, String kpp, Boolean isActive){
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.inn = inn;
        this.kpp = kpp;
        this.isActive = isActive;
    }

    public Organization(String name, String fullName, String address, String phone, String inn, String kpp, Boolean isActive){
        this.name = name;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.inn = inn;
        this.kpp = kpp;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public Boolean getIsActive(Boolean isActive){
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Получить офисы организации
     *
     * @return возвращает множество офисов
     */
    public Set<Office> getOffices(){
        if (offices == null){
            offices = new HashSet<>();
        }
        return offices;
    }

    /**
     * Строковое представление объекта
     *
     * @return возвращает строковое представление объекта
     */
    @Override
    public String toString(){
        return "{id:" + id + ";name:" + name + ";full_name:" + fullName
                + ";address:" + address + ";phone:" + phone
                + ";inn:" + inn + ";kpp:" + kpp + ";is_active:" + isActive + "}";
    }
}
