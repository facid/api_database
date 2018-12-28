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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Работник
 */

@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "office_id", nullable = false, insertable = false, updatable = false)
    private Long officeId;

    @Column(name = "doc_data_id", nullable = false, insertable = false, updatable = false)
    private Long docDataId;

    @Column(name = "country_id", nullable = false, insertable = false, updatable = false)
    private Long countryId;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "second_name", length = 50)
    private String secondName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "position", length = 50, nullable = false)
    private String position;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "is_identified")
    private Boolean isIdentified;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "doc_data_id")
    private DocumentData docData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    /**
     * Конструктор для hibernate
     */
    public Employee(){

    }

    public Employee(Long id, Long officeId, Long docDataId, Long countryId, String firstName,
                    String secondName, String lastName, String position, String phone, Boolean isIdentified){
        this.id = id;
        this.officeId = officeId;
        this.docDataId = docDataId;
        this.countryId = countryId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.position = position;
        this.phone = phone;
        this.isIdentified = isIdentified;
    }

    public Employee(Long officeId, Long docDataId, Long countryId, String firstName,
                    String secondName, String lastName, String position, String phone, Boolean isIdentified){
        this.officeId = officeId;
        this.docDataId = docDataId;
        this.countryId = countryId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.position = position;
        this.phone = phone;
        this.isIdentified = isIdentified;
    }

    public Employee(Long id, String firstName, String secondName, String lastName, String position){
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean isIdentified) {
        this.isIdentified = isIdentified;
    }

    /**
     * Получить office
     *
     * @return
     */
    public Office getOffice(){
        return office;
    }

    /**
     * Изменить office
     *
     * @param office
     */
    public void setOffice(Office office){
        this.office = office;
    }

    /**
     * Получить docData
     *
     * @return
     */
    public DocumentData getDocData(){
        return docData;
    }

    /**
     * Изменить docData
     * @param docData
     * @return
     */
    public void setDocData(DocumentData docData){
        this.docData = docData;
    }

    /**
     * Получить country
     *
     * @return
     */
    public Country getCountry(){
        return country;
    }

    /**
     * Изменить country
     * @param country
     * @return
     */
    public void setCountry(Country country){
        this.country = country;
    }

    @Override
    public String toString(){
        return "{id:" + id + ";officeId:" + officeId + ";docDataId:" + docDataId + ";countryId:" + countryId
                + ";firstName:" + firstName + ";secondName:" + secondName + ";lastName:" + lastName
                + ";position:" + position + ";phone:" + phone + ";isIdentified:" + isIdentified + "}";
    }
}
