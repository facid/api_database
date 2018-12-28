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
import java.time.LocalDate;

/**
 * Данные документа
 */

@Entity
@Table(name = "Document_Data")
public class DocumentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    @Column(name = "doc_type_id", nullable = false, insertable = false, updatable = false)
    private Long docTypeId;

    @Column(name = "doc_number", length = 10)
    private String docNumber;

    @Column(name = "date")
    private LocalDate date;


    @OneToOne(mappedBy = "docData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type_id")
    private DocumentType docType;

    /**
     * Конструктор для hibernate
     */
    public DocumentData(){

    }

    public DocumentData(Long id, Long docTypeId, String docNumber, LocalDate date){
        this.id = id;
        this.docTypeId = docTypeId;
        this.docNumber = docNumber;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Long getDocTypeId() {
        return docTypeId;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Получить employee
     *
     * @return
     */
    public Employee getEmployee(){
        return employee;
    }

    /**
     * Изменить employee
     *
     * @return
     */
    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    /**
     * Получить docType
     *
     * @return
     */
    public DocumentType getDocType(){
        return docType;
    }

    /**
     * Изменить docType
     *
     * @param docType
     */
    public void setDocType(DocumentType docType){
        this.docType = docType;
    }
}
