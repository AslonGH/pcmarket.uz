package uz.pdp.task2.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task2.entity.Attachment;
import uz.pdp.task2.projection.AttachmentInter;

//@RepositoryRestResource(path = "attachment",excerptProjection = AttachmentInter.class)
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
