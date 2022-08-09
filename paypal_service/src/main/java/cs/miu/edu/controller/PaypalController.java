package cs.miu.edu.controller;

import cs.miu.edu.domain.Paypal;
import cs.miu.edu.dto.PaypalDto;
import cs.miu.edu.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paypals")
public class PaypalController {

    @Autowired
    private PaypalService paypalService;

    @PostMapping
    public ResponseEntity<?> savePaypal(@RequestBody PaypalDto paypalDto){
        ResponseEntity<?> response= new ResponseEntity<>(paypalService.savePaypal(paypalDto), HttpStatus.OK);
        return response;
    }


    @PutMapping("verify-purchase")
    public ResponseEntity<?> verifyPurchase(@RequestBody PaypalDto paypalDto){
        ResponseEntity<?> response= new ResponseEntity<>(paypalService.verifyPurchase(paypalDto), HttpStatus.OK);
        return response;
    }


}
