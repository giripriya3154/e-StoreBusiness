package cs.miu.edu.service;

import cs.miu.edu.clients.AccountFeignClient;
import cs.miu.edu.clients.ShippingFeignClient;
import cs.miu.edu.clients.TransactionFeignClient;
import cs.miu.edu.domain.OrderClass;
import cs.miu.edu.domain.OrderLine;
import cs.miu.edu.dto.*;
import cs.miu.edu.clients.ProductFeignClient;
import cs.miu.edu.repository.OrderRepo;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductFeignClient productFeignClient;
    @Autowired
    AccountFeignClient accountFeignClient;
    @Autowired
    TransactionFeignClient transactionFeignClient;
    @Autowired
    ShippingFeignClient shippingFeignClient;


    public OrderResponseDto purchaseOrder(List<OrderLineDto> orderDtoList, String purchaseMethod, String emailId) {
        ResponseEntity<OrderResponseDto> response = productFeignClient.supplyProduct(orderDtoList);
        OrderResponseDto orderResponseDto = response.getBody();
        if (orderResponseDto.getErrorMessages() != null) {
            return orderResponseDto;
        }

        PurchaseDto purchaseDto = PurchaseDto.builder()
                .emailAddress(emailId)
                .balance(orderResponseDto.getTotal())
                .purchaseMethod(purchaseMethod)
                .build();
        ResponseEntity<AccountResponseDto> paymentResponse = accountFeignClient.purchaseOrder(purchaseDto);
        AccountResponseDto accountResponseDto = paymentResponse.getBody();
        if (accountResponseDto.getStatus() == Status.FAILURE) {

            productFeignClient.returnBack(orderDtoList);
            return OrderResponseDto.builder()
                    .status(Status.FAILURE)
                    .errorMessages(Arrays.asList("Something is wrong ")).build();
        }
        List<OrderLine> orderLines = new ArrayList<>();
        for (OrderLineDto orderLineDto : orderDtoList) {
            OrderLine orderLine = OrderLine.builder()
                    .productCode(orderLineDto.getProductCode())
                    .quantity(orderLineDto.getQuantity())
                    .build();
            orderLines.add(orderLine);
        }

        OrderClass orderClass = OrderClass.builder()
                .accountId(accountResponseDto.getAccountId())
                .orderLines(orderLines)
                .build();
        OrderClass order = orderRepo.save(orderClass);
        TransactionDto transactionDto = TransactionDto.builder()
                .total(purchaseDto.getBalance())
                .paymentMethod(purchaseMethod.toUpperCase())
                .orderId(order.getId())
                .build();
        ResponseEntity<TransactionDto> transactionResponse = accountFeignClient.saveTransaction(transactionDto);
        ResponseEntity<ShipperDto> shipperDtoResponseEntity=shippingFeignClient.submitToShipper(ShipperDto.builder().orderId(order.getId())
                .build());
        ShipperDto shipperDto= shipperDtoResponseEntity.getBody();
        orderResponseDto.setShipperCode(shipperDto.getShippingCode());
        orderResponseDto.setTransactionCode(transactionResponse.getBody().getTransactionCode());
        return orderResponseDto;

    }
}
