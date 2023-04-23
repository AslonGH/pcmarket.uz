package uz.pdp.task2.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task2.entity.*;
import uz.pdp.task2.payload.OrderingDto;
import uz.pdp.task2.payload.Result;
import uz.pdp.task2.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OrderingService {

    @Autowired
    OrderingRepository orderingRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ShoppingVentureRepository shoppingVentureRepository;

    public Result addOrdering(@RequestBody OrderingDto orderingDto) {

        Ordering ordering=new Ordering();

     boolean exists = orderingRepository.existsByPhoneNumber(orderingDto.getPhoneNumber());
     if (exists) {
         return new Result("Such phoneNumber  exist", false);
     }
     ordering.setPhoneNumber(orderingDto.getPhoneNumber());

        boolean exists1 = orderingRepository.existsByEmail(orderingDto.getEmail());
     if (exists1){
            return new Result("Such email  exist", false);
      }

        Optional<Address> optionalAddress = addressRepository.findById(orderingDto.getAddressOfBuyerId());
     if (!optionalAddress.isPresent())
         return new Result("Such Address  not found", false);

     ordering.setAddressOfBuyer(optionalAddress.get());

        Optional<ShoppingVenture> ventureOptional = shoppingVentureRepository.findById(orderingDto.getShoppingVentureId());
        if (!ventureOptional.isPresent())
            return new Result("Such OptionalVenture  not found", false);

     ordering.setShoppingVenture(ventureOptional.get());
     ordering.setDescription(orderingDto.getDescription());
     ordering.setDetails(orderingDto.getDetails());
     ordering.setNameBuyer(orderingDto.getNameBuyer());
     ordering.setEmail(orderingDto.getEmail());
     orderingRepository.save(ordering);
     return new Result("Ordering saved",true);
    }

    public Ordering getOrderingById(Integer id){
        Optional<Ordering> optionalOrdering = orderingRepository.findById(id);
        return optionalOrdering.orElseGet(Ordering::new);
    }

    public List<Ordering> getOrdering(){
        List<Ordering> orderingList = orderingRepository.findAll();
        return orderingList;
    }

    public Result editOrderingById(OrderingDto orderingDto, Integer id) {

        boolean exists = orderingRepository.existsByPhoneNumberAndIdNot(orderingDto.getPhoneNumber(),id);
        if (exists)
        return new Result("Such phoneNumber already exist",false);

        boolean exists1 = orderingRepository.existsByEmailAndIdNot(orderingDto.getEmail(), id);
        if (exists1){
            return new Result("Such email  exist", false);
        }

    Optional<Ordering> optional = orderingRepository.findById(id);
    if (!optional.isPresent())
    return new Result("Such ordering not found",false);
        Ordering ordering = optional.get();

    ordering.setPhoneNumber(orderingDto.getPhoneNumber());

    Optional<Address> optionalAddress = addressRepository.findById(orderingDto.getAddressOfBuyerId());
    if (!optionalAddress.isPresent())
    return new Result("Such Address  not found", false);

    ordering.setAddressOfBuyer(optionalAddress.get());

    Optional<ShoppingVenture> ventureOptional = shoppingVentureRepository.findById(orderingDto.getShoppingVentureId());
    if (!ventureOptional.isPresent())
    return new Result("Such OptionalVenture  not found", false);

    ordering.setShoppingVenture(ventureOptional.get());
    ordering.setDescription(orderingDto.getDescription());
    ordering.setDetails(orderingDto.getDetails());
    ordering.setNameBuyer(orderingDto.getNameBuyer());
    ordering.setEmail(orderingDto.getEmail());
    orderingRepository.save(ordering);
    return new Result("Ordering edited",true);
}

     public Result deleteOrdering (Integer id){
            try {
                orderingRepository.deleteById(id);
                return new Result("Ordering deleted", true);
            } catch (Exception e) {
                return new Result("Ordering not deleted", false);
            }
     }

}
