package by.alexkasko.bytouristbot.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "city")
@EntityListeners(AuditingEntityListener.class)
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Autowired
    public Long getId() {
        return id;
    }

    @Autowired
    public void setId(Long id) {
        this.id = id;
    }

    @Autowired
    public String getName() {
        return name;
    }

    @Autowired
    public void setName(String name) {
        this.name = name;
    }

    @Autowired
    public String getDescription() {
        return description;
    }

    @Autowired
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "City[id="+id+", name='"+name+"', description='"+description+"']";
    }

/*    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        City city = (City) obj;
        return id.equals(city.id) &&
                Objects.equals(name, city.name) &&
                Objects.equals(description, city.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,description);
    }*/

}
