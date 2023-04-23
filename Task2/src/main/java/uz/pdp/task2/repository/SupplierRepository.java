package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task2.entity.Supplier;
import uz.pdp.task2.projection.SupplierInter;

@RepositoryRestResource(path = "supplier",excerptProjection = SupplierInter.class)
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}
