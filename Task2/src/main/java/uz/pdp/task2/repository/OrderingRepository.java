package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task2.entity.Ordering;
import uz.pdp.task2.projection.OrderingInter;

@RepositoryRestResource(path = "ordering",excerptProjection = OrderingInter.class)
public interface OrderingRepository extends JpaRepository<Ordering,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Integer id);

}
