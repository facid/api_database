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
 * Тип документа
 */

@Entity
@Table(name = "Document_Type")
public class DocumentType {

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


    @OneToMany(mappedBy = "docType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DocumentData> docData;

    /**
     * Конструктор для hibernate
     */
    public DocumentType(){

    }

    public DocumentType(String name, String code){
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
     * Получить множество docData
     *
     * @return
     */
    public Set<DocumentData> getDocumentData() {
        if (docData == null){
            docData = new HashSet<DocumentData>();
        }
        return docData;
    }

    /**
     * Добавить docData
     *
     * @param docData
     */
    public void addDocData(DocumentData docData){
        getDocumentData().add(docData);
        docData.setDocType(this);
    }

    /**
     * Удалить docData
     *
     * @param docData
     */
    public void removeDocData(DocumentData docData){
        getDocumentData().remove(docData);
        docData.setDocType(null);
    }
}
