package de.menkalian.apus.server.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "predefined", schema = "apus")
public class PredefinedEntity {
    private int id;
    private String nameDe;
    private String nameEn;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_de")
    public String getNameDe() {
        return nameDe;
    }

    public void setNameDe(String nameDe) {
        this.nameDe = nameDe;
    }

    @Basic
    @Column(name = "name_en")
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameDe != null ? nameDe.hashCode() : 0);
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PredefinedEntity that = (PredefinedEntity) o;

        if (id != that.id) return false;
        if (nameDe != null ? !nameDe.equals(that.nameDe) : that.nameDe != null) return false;
        return nameEn != null ? nameEn.equals(that.nameEn) : that.nameEn == null;
    }
}
