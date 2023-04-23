package uz.pdp.task2.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2.entity.Category;
import uz.pdp.task2.payload.CategoryDto;
import uz.pdp.task2.payload.Result;
import uz.pdp.task2.service.CategoryService;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    // BEAN ICHIDAGI IKKITA CLASSNI @Autowired QILIB CHAQIRSA BÖLADI
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public HttpEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping
    public  HttpEntity<?> getCategory(){
       List<Category> categoryList = categoryService.getCategory();
       return ResponseEntity.ok(categoryList);
    }


    @GetMapping("/{id}")
    public  HttpEntity<?> getCategory(@PathVariable Integer id){
        Category categoryById = categoryService.getCategoryById(id);
        return  ResponseEntity.status(categoryById !=null ? HttpStatus.OK:HttpStatus.CONFLICT).body(categoryById);
    }


    @PutMapping("/{id}")
    public  Result editCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer id){
        Result result = categoryService.editCategoryById(categoryDto,id);
        return result;
    }


    @DeleteMapping("/{id}")
    public  Result deleteCategory(@PathVariable Integer id){
        Result result = categoryService.deleteCategory(id);
        return result;
    }

}
