package cs.miu.edu.controller;

import cs.miu.edu.dto.OrderLineDto;
import cs.miu.edu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PutMapping ("/purchaseOrder")
    public ResponseEntity<?> verifyAndPurchaseOrder(@RequestBody List<OrderLineDto> orderDtos , @RequestParam(value = "purchaseMethod",required = false) String purchaseMethod, @RequestParam("emailId") String emailId){
       ResponseEntity<?> response= new ResponseEntity<>(orderService.purchaseOrder(orderDtos,purchaseMethod, emailId), HttpStatus.OK);
       return response;
    }




}
