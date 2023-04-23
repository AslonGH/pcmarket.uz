package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task2.entity.Product;
import uz.pdp.task2.entity.ShoppingVenture;
import uz.pdp.task2.payload.Result;
import uz.pdp.task2.payload.ShoppingVentureDto;
import uz.pdp.task2.repository.ProductRepository;
import uz.pdp.task2.repository.ShoppingVentureRepository;
import java.util.*;

@Service
public class ShoppingVentureService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShoppingVentureRepository shoppingVentureRepository;


    public Result addShoppingVenture(@RequestBody ShoppingVentureDto shoppingVentureDto) {

        ShoppingVenture shoppingVenture=new ShoppingVenture();

        Set<Product>productList=new HashSet<>();

        List<Integer> productsIds = shoppingVentureDto.getProductsIds();
        for (Integer productsId : productsIds)
        {

            Optional<Product> optionalProduct = productRepository.findById(productsId);
            if (!optionalProduct.isPresent())
                return new Result("Product not found", false);
            productList.add(optionalProduct.get());
        }
        shoppingVenture.setProducts(productList);
        shoppingVenture.setCount(shoppingVentureDto.getCount());
        shoppingVenture.setDescription(shoppingVentureDto.getDescription());
        shoppingVenture.setSubtotal(shoppingVentureDto.getSubtotal());
        shoppingVenture.setTotal(shoppingVentureDto.getTotal());
        try {
            shoppingVentureRepository.save(shoppingVenture);
        }catch (Exception e){
            return new Result("ShoppingVenture not added, Product already used", false);
        }
        return new Result("ShoppingVenture added", true);
    }

    public ShoppingVenture getShoppingVentureById(Integer id){
        Optional<ShoppingVenture> optional = shoppingVentureRepository.findById(id);
        if (!optional.isPresent())
        return new ShoppingVenture();
        ShoppingVenture shoppingVenture = optional.get();
        return shoppingVenture;
    }

    public List<ShoppingVenture> getShoppingVentures(){
        List<ShoppingVenture> shoppingVentures = shoppingVentureRepository.findAll();
        return shoppingVentures;
    }

    public Result editShoppingVenturesById(ShoppingVentureDto shoppingVentureDto, Integer id) {

        Optional<ShoppingVenture> optional = shoppingVentureRepository.findById(id);
        if (!optional.isPresent())
            return new Result("shoppingVenture not found", false);
        ShoppingVenture shoppingVenture = optional.get();

        Set<Product> productList = new HashSet<>();

        List<Integer> productsIds = shoppingVentureDto.getProductsIds();
        for (Integer productsId : productsIds) {
            Optional<Product> optionalProduct = productRepository.findById(productsId);
            if (!optionalProduct.isPresent())
                return new Result("Product not found", false);
            productList.add(optionalProduct.get());
        }

            shoppingVenture.setProducts(productList);
            shoppingVenture.setCount(shoppingVentureDto.getCount());
            shoppingVenture.setDescription(shoppingVentureDto.getDescription());
            shoppingVenture.setSubtotal(shoppingVentureDto.getSubtotal());
            shoppingVenture.setTotal(shoppingVentureDto.getTotal());

        try {
             shoppingVentureRepository.save(shoppingVenture);
         }catch (Exception e){
             return new Result("ShoppingVenture not edited,Product already used", false);
         }
         return new Result("ShoppingVenture edited", true);
     }

     public Result deleteShoppingVenture(Integer id){
            try {
                shoppingVentureRepository.deleteById(id);
                return new Result("shoppingVentures deleted", true);
            } catch (Exception e) {
                return new Result("shoppingVentures not deleted", false);
            }
        }

    }
