package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2.entity.AttachmentContent;
import java.util.Optional;


//@RepositoryRestResource(path = "attachmentContent",excerptProjection = AttachmentContentInter.class)
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {

    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);

}
