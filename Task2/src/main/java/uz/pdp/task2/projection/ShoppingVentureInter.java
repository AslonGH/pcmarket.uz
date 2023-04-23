package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.Product;
import uz.pdp.task2.entity.ShoppingVenture;

import java.util.List;

@Projection(types = ShoppingVenture.class)
public interface ShoppingVentureInter {

    Integer getId();
    Integer getCount();
    Integer getSubtotal();
    Integer getTotal();
    String  getDescription();

    List<Product> getProduct();
}
