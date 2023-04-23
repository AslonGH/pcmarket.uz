package uz.pdp.task2.payload;
import lombok.Data;


@Data
public class OrderingDto {

    private String  nameBuyer;

    private String  phoneNumber;

    private String  email;

    private String  details;

    private String  description;

    private Integer addressOfBuyerId;

    private Integer shoppingVentureId;

}
