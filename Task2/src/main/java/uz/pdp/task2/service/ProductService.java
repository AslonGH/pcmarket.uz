package uz.pdp.task2.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task2.entity.Attachment;
import uz.pdp.task2.entity.Category;
import uz.pdp.task2.entity.Product;
import uz.pdp.task2.payload.ProductDto;
import uz.pdp.task2.payload.Result;
import uz.pdp.task2.repository.AttachmentRepository;
import uz.pdp.task2.repository.CategoryRepository;
import uz.pdp.task2.repository.ProductRepository;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;


    public Result addProduct(@RequestBody ProductDto productDto) {

        boolean exists = productRepository.existsByNameAndPhotoId(productDto.getName(), productDto.getPhotoId());
        if (exists)
        return new Result("Such Product with thies Photo already exist",false);


        Product product=new Product();
      Set<Category> categories=new HashSet<>();

     /*   boolean exists = productRepository.existsByNameAndCategories(productDto.getName(), categories);
        if (exists) {
            return new Result("Bunday maxsulot ushbu kategoriyada mavjud",false);
    }*/

     List<Integer> categoryIds = productDto.getCategoryIds();
     for (Integer categoryId : categoryIds) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent())
            return new Result("Bunday Categoriya mavjud emas", false);
        categories.add(optionalCategory.get());

     /*
     boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(),categoryId);
     if (exists) {
         return new Result("Bunday maxsulot ushbu kategoriyada mavjud", false);
     }*/

    }
    product.setCategories(categories);

        // PHOTO TEKSHIRISH
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday photo mavjud emas",false);
    product.setPhoto(optionalAttachment.get());
    product.setName(productDto.getName());
    product.setDiscounted(productDto.isDiscounted());
    product.setDescription(productDto.getDescription());
    product.setAvailable(productDto.isAvailable());
    product.setOnlyInCollections(productDto.isOnlyInCollections());
    product.setFactoryName(productDto.getFactoryName());
    product.setRecommend(productDto.isRecommend());
    productRepository.save(product);
        return new Result("Product saved",true);
    }

    public Product getProductById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
        return new Product();
        Product product = optionalProduct.get();
        return product;
    }

    public List<Product> getProduct(){
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Result editProductById(ProductDto productDto, Integer id) {

        boolean exists = productRepository.existsByNameAndPhotoIdNot(productDto.getName(), productDto.getPhotoId());
        if (exists)
            return new Result("Such Product with thies Photo already exist",false);

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Product not found", false);
        Product product = optionalProduct.get();

        Set<Category>categories=new HashSet<>();
        List<Integer> categoryIds = productDto.getCategoryIds();
        for (Integer categoryId : categoryIds) {

            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (!optionalCategory.isPresent())
                return new Result("Bunday Categoriya mavjud emas", false);
            categories.add(optionalCategory.get());
 /*  boolean exists = productRepository.existsByNameAndCategoryIdAndIdNot(productDto.getName(), categoryId, id);
            // boolean exists = productRepository.existsByNameAndCategoriesNot(productDto.getName(),categories);
            if (exists) {
                return new Result("Bunday maxsulot ushbu kategoriyalarda mavjud", false);
}*/
        }
        product.setCategories(categories);

            //PHOTO TEKSHIRISH
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
            if (!optionalAttachment.isPresent())
                return new Result("Bunday photo mavjud emas", false);

        product.setPhoto(optionalAttachment.get());
        product.setName(productDto.getName());
        product.setDiscounted(productDto.isDiscounted());
        product.setDescription(productDto.getDescription());
        product.setAvailable(productDto.isAvailable());
        product.setOnlyInCollections(productDto.isOnlyInCollections());
        product.setFactoryName(productDto.getFactoryName());
        product.setRecommend(productDto.isRecommend());

        productRepository.save(product);
        return new Result("Product edited", true);
     }

     public Result deleteProduct (Integer id){
            try {
                productRepository.deleteById(id);
                return new Result("Product deleted", true);
            } catch (Exception e) {
                return new Result("Product not deleted", false);
            }
        }

    }
