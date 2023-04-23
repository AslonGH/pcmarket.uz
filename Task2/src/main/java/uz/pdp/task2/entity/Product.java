package uz.pdp.task2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String  name;

    private boolean isDiscounted;

    private boolean isAvailable;

    private String  description;

    private String  factoryName;

    private boolean isOnlyInCollections;

    private boolean isRecommend;


    // SHU PRODUCT BOSHQA CATEGORYDA HAM BOR EKAN
    @ManyToMany
    private Set<Category> categories;

    @OneToOne
    private Attachment photo;

}
