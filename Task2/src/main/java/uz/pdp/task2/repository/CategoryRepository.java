package uz.pdp.task2.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2.entity.Category;


//@RepositoryRestResource(path = "category",excerptProjection = CategoryInter.class)
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    //SHU category da name TAKRORLANMASIN, BOSHQA CATEGORIYADA shu name kelishi MUMKIN
    boolean existsByNameAndParentCategoryId(String name, Integer parentCategory_id);

    //PRODUCT REPOSITORYDA,biz özgartirayotgan productdan tashqari, SHU CategoryId LIK PRODUCTNAME BORMI?
    boolean existsByNameAndParentCategoryIdAndIdNot(String name, Integer parentCategory_id, Integer id);

}
