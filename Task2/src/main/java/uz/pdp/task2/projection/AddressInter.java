package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.Attachment;


@Projection(types = Attachment.class)
public interface AddressInter {

     Integer getId();

     String getHouseNumber();

     String getStreet();

}
