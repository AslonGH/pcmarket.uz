package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.Address;
import uz.pdp.task2.entity.Ordering;
import uz.pdp.task2.entity.ShoppingVenture;

@Projection(types = Ordering.class)
public interface OrderingInter {

    Integer getId();
    String  getNameBuyer();
    String  getPhoneNumber();
    String  getEmail();
    String  getDetails();
    String  getDescription();

    Address getAddressOfBuyer();
    ShoppingVenture getShoppingVenture();

}
