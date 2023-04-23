package uz.pdp.task2.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.task2.entity.Attachment;
import uz.pdp.task2.entity.AttachmentContent;

@Projection(types = AttachmentContent.class)
public interface AttachmentContentInter {

     Integer getId();

     byte[] getBytes();


     Attachment getPhoto();
}
