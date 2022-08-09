package cs.miu.edu.controller;

import cs.miu.edu.dto.ShipperDto;
import cs.miu.edu.dto.Status;
import cs.miu.edu.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shippings")
public class ShippingController {
    @Autowired
    private ShippingService shippingService;

    @PostMapping
    public ResponseEntity<?> submitToShipper(@RequestBody ShipperDto shipperDto){

        return new ResponseEntity<ShipperDto>(shippingService.submitToShipper(shipperDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ShipperDto> getStatus(@RequestParam("shippingCode")String shippingCode){

        return new ResponseEntity<ShipperDto>(shippingService.getShipperStatus(shippingCode), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ShipperDto> updateShippingStatus(@RequestParam("shippingCode") String shipperCode,
                                                           @RequestParam("status")Status status){

        return new ResponseEntity<ShipperDto>(shippingService.updateShipperStatus(shipperCode, status), HttpStatus.OK);
    }


}
