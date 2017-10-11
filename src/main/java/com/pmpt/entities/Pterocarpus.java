package com.pmpt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_pterocarpus")
public class Pterocarpus implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;
    @Column(name = "ld_name", unique = true, nullable = false, length = 40)
    private String ldName;
    @JsonIgnore
    @ManyToMany()
    @JoinTable(name = "tb_locate_pterocarpus",
            joinColumns = @JoinColumn(name = "pid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lid", referencedColumnName = "id"))
    private List<Locate> locates;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLdName() {
        return ldName;
    }

    public void setLdName(String ldName) {
        this.ldName = ldName;
    }

    public List<Locate> getLocates() {
        return locates;
    }

    public void setLocates(List<Locate> locates) {
        this.locates = locates;
    }

    @Override
    public String toString() {
        return "Pterocarpus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ldName='" + ldName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pterocarpus that = (Pterocarpus) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return ldName != null ? ldName.equals(that.ldName) : that.ldName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ldName != null ? ldName.hashCode() : 0);
        return result;
    }
}
