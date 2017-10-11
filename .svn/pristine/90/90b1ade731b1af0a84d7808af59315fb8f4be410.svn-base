package com.pmpt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "Locate")
@Table(name = "tb_locate")
public class Locate implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", unique = true, length = 15)
    private String name;
    @ManyToMany()
    @JsonIgnore
    private List<Pterocarpus> pterocarpuses;

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

    public List<Pterocarpus> getPterocarpuses() {
        return pterocarpuses;
    }

    public void setPterocarpuses(List<Pterocarpus> pterocarpuses) {
        this.pterocarpuses = pterocarpuses;
    }

    @Override
    public String toString() {
        return "Locate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pterocarpuses=" + pterocarpuses +
                '}';
    }


}