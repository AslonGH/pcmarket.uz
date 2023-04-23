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
public class ShoppingVenture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private  Integer count;

    private  Double  subtotal;

    private  Double  total;

    private  String  description;

    @OneToMany
    private Set<Product> products;

}
