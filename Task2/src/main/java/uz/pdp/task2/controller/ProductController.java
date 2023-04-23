package uz.pdp.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2.entity.Product;
import uz.pdp.task2.payload.ProductDto;
import uz.pdp.task2.payload.Result;
import uz.pdp.task2.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto){
        Result result = productService.addProduct(productDto);
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping
    public HttpEntity<?> getProduct(){
        List<Product> productsList = productService.getProduct();
        return ResponseEntity.ok(productsList);
    }

    @GetMapping("/{id}")
    public  HttpEntity<?> getProductById(@PathVariable Integer id){
        Product productById = productService.getProductById(id);
        return  ResponseEntity.status(productById !=null? HttpStatus.OK:HttpStatus.CONFLICT).body(productById);
    }

    @PutMapping("/{id}")
    public  HttpEntity<?> editProduct(@RequestBody ProductDto productDto, @PathVariable Integer id){
        Result result = productService.editProductById(productDto,id);
        return ResponseEntity.status(result !=null ? 202:409).body(result);
    }

    @DeleteMapping("/{id}")
    public  HttpEntity<?> deleteProduct(@PathVariable Integer id){
        Result result = productService.deleteProduct(id);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }




}
