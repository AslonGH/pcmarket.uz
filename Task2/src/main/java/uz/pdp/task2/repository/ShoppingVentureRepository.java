package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.task2.entity.Product;
import uz.pdp.task2.entity.ShoppingVenture;
import uz.pdp.task2.projection.ShoppingVentureInter;

import java.util.Set;

//@RepositoryRestResource(path = "shoppingVenture",excerptProjection = ShoppingVentureInter.class)
public interface ShoppingVentureRepository extends JpaRepository<ShoppingVenture,Integer> {

}
