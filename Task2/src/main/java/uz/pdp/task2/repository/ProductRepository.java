package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2.entity.Product;


// @RepositoryRestResource(path = "product",excerptProjection = ProductInter.class)
public interface ProductRepository extends JpaRepository<Product,Integer> {

      boolean existsByNameAndPhotoId(String name, Integer photo_id);

      boolean existsByNameAndPhotoIdNot(String name, Integer photo_id);

   /*
    // SHU category da name TAKRORLANMASIN, BOSHQA CATEGORIYADA shu name kelishi MUMKIN
    boolean  existsByNameAndCategoryId(String name, Integer category_id);

    // PRODUCT REPOSITORYDA,biz özgartirayotgan productdan tashqari, SHU CategoryId LIK PRODUCTNAME BORMI?
    boolean  existsByNameAndCategoryIdAndIdNot(String name, Integer category_id, Integer id);

    boolean existsByNameAndCategories(String name, List<Category> categories);

    boolean existsByNameAndCategoriesNot(String name, List<Category> categories);
*/

}
