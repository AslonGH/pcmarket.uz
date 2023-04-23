package uz.pdp.task2.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2.entity.Ordering;
import uz.pdp.task2.payload.OrderingDto;
import uz.pdp.task2.payload.Result;
import uz.pdp.task2.service.OrderingService;
import java.util.List;

@RestController
@RequestMapping("/ordering")
public class OrderingController {

    @Autowired
    OrderingService orderingService;

    @PostMapping
    public HttpEntity<?> addOrdering(@RequestBody OrderingDto orderingDto){
        Result result = orderingService.addOrdering(orderingDto);
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping
    public HttpEntity<?> getOrdering(){
        List<Ordering> orderingList = orderingService.getOrdering();
        return ResponseEntity.ok(orderingList);
    }


    @GetMapping("/{id}")
    public  HttpEntity<?>  getOrderingById(@PathVariable Integer id){
        Ordering orderingById = orderingService.getOrderingById(id);
        return ResponseEntity.status(orderingById !=null ? HttpStatus.OK:HttpStatus.CONFLICT).body(orderingById);
    }


    @PutMapping("/{id}")
    public  Result editOrdering(@RequestBody OrderingDto orderingDto, @PathVariable Integer id){
        Result result = orderingService.editOrderingById(orderingDto,id);
        return result;
    }


    @DeleteMapping("/{id}")
    public  Result deleteOrdering(@PathVariable Integer id){
        Result result = orderingService.deleteOrdering(id);
        return result;
    }

}
