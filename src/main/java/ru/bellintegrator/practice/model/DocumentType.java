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
    private String docName;

    @Column(name = "code", length = 2)
    private String docCode;


    @OneToMany(mappedBy = "docType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DocumentData> docData;

    /**
     * Конструктор для hibernate
     */
    public DocumentType(){

    }

    /**
     * Конструктор для обновления и сохранения docData
     *
     * @param docName
     * @param docCode
     */
    public DocumentType(String docName, String docCode){
        this.docName = docName;
        this.docCode = docCode;
    }

    public Long getId() {
        return id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String name) {
        this.docName = name;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String code) {
        this.docCode = code;
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

    @Override
    public String toString(){
        return "{id:" + id + ";name:" + docName + ";code:" + docCode + "}";
    }
}
