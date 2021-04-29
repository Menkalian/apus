package de.menkalian.apus.server.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item", schema = "apus")
public class ItemEntity {
    private int id;
    private ListEntity list;
    private Short custom;
    private String value;
    private Integer amount;
    private String comment;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "custom", columnDefinition = "TINYINT")
    public Short getCustom() {
        return custom;
    }

    public void setCustom(Short custom) {
        this.custom = custom;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "comment", columnDefinition = "TEXT")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (custom != null ? custom.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemEntity that = (ItemEntity) o;

        if (id != that.id) return false;
        if (custom != null ? !custom.equals(that.custom) : that.custom != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;
    }

    @ManyToOne(targetEntity = ListEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", referencedColumnName = "id")
    public ListEntity getList() {
        return list;
    }

    public void setList(ListEntity list) {
        this.list = list;
    }
}
