package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task2.entity.Address;
import uz.pdp.task2.projection.AddressInter;


@RepositoryRestResource(path = "address",excerptProjection = AddressInter.class)
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
