package uz.pdp.task2.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2.entity.ShoppingVenture;
import uz.pdp.task2.payload.Result;
import uz.pdp.task2.payload.ShoppingVentureDto;
import uz.pdp.task2.service.ProductService;
import uz.pdp.task2.service.ShoppingVentureService;
import java.util.List;


@RestController
@RequestMapping("/shoppingVenture")
public class ShoppingVentureController {

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingVentureService shoppingVentureService;

    @PostMapping
    public Result addShoppingVenture(@RequestBody ShoppingVentureDto shoppingVentureDto){
        Result result = shoppingVentureService.addShoppingVenture(shoppingVentureDto);
        return result;
    }

    @GetMapping
    public List<ShoppingVenture> getShoppingVenture(){
        List<ShoppingVenture> shoppingVentures = shoppingVentureService.getShoppingVentures();
        return shoppingVentures;
    }

    @GetMapping("/{id}")
    public  ShoppingVenture getShoppingVentureById(@PathVariable Integer id){
        ShoppingVenture shoppingVentureById = shoppingVentureService.getShoppingVentureById(id);
        return shoppingVentureById;
    }

    @PutMapping("/{id}")
    public  Result editShoppingVenture(@RequestBody ShoppingVentureDto shoppingVentureDto, @PathVariable Integer id){
        Result result = shoppingVentureService.editShoppingVenturesById(shoppingVentureDto,id);
        return result;
    }


    @DeleteMapping("/{id}")
    public  Result deleteShoppingVenture(@PathVariable Integer id){
        Result result = shoppingVentureService.deleteShoppingVenture(id);
        return result;
    }

}
