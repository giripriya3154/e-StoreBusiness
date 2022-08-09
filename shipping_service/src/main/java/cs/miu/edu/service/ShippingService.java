package cs.miu.edu.service;


import cs.miu.edu.domain.Shipper;
import cs.miu.edu.dto.ShipperDto;
import cs.miu.edu.dto.Status;
import cs.miu.edu.exceptions.ApiException;
import cs.miu.edu.repository.ShippingRepo;
import cs.miu.edu.utils.ShippingCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShippingService {
    @Autowired
    private ShippingRepo shippingRepo;

    @Autowired
    private ShippingCodeGenerator shippingCodeGenerator;


    public ShipperDto submitToShipper(ShipperDto shipperDto) {
        String shippingCode = shippingCodeGenerator.shippingCodeGenerator();
        Shipper shipper = Shipper.builder()
                .orderId(shipperDto.getOrderId())
                .shippingStatus(Status.SHIPPED)
                .shippingCode(shippingCode).build();
        shippingRepo.save(shipper);
        shipperDto.setShippingCode(shippingCode);
        shipperDto.setStatus(Status.SHIPPED);
        return shipperDto;
    }

    public ShipperDto getShipperStatus(String shipperCode) {
        Shipper shipper =shippingRepo.findAllByShippingCode(shipperCode);
        Optional.ofNullable(shipper).orElseThrow(()-> ApiException.builder().error("Invalid Shipper Code").build());
        return ShipperDto.builder().status(shipper.shippingStatus).build();
    }

    public ShipperDto updateShipperStatus(String shipperCode, Status status) {
        Shipper shipper =shippingRepo.findAllByShippingCode(shipperCode);
        Optional.ofNullable(shipper).orElseThrow(()-> ApiException.builder().error("Invalid Shipper Code").build());
        shipper.setShippingStatus(status);
        shippingRepo.save(shipper);
        return ShipperDto.builder().status(status).build();
    }
}
