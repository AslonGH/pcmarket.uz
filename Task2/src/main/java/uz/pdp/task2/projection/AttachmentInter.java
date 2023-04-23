package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.Attachment;

@Projection(types = Attachment.class)
public interface AttachmentInter {

     Integer getId();

     String  getName();

     long   getSize();

     String getContentType();

}
