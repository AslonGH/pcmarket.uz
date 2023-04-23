package uz.pdp.task2.payload;
import lombok.Data;
import java.util.List;


@Data
public class ShoppingVentureDto {

    private  Integer count;

    private  Double  subtotal;

    private  Double  total;

    private  String  description;

    private  List<Integer> productsIds;

}
