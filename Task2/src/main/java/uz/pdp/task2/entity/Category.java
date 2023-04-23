package uz.pdp.task2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;

    @Column(nullable = false)
    private String   name;

    private String   description;

    @ManyToOne
    private Category parentCategory;

    @ManyToMany
    private List<Attachment> photos;


}
