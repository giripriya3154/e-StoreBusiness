package cs.miu.edu.service;

import cs.miu.edu.domain.Product;
import cs.miu.edu.dto.OrderLineDto;
import cs.miu.edu.dto.OrderResponseDto;
import cs.miu.edu.dto.ProductDto;
import cs.miu.edu.dto.Status;
import cs.miu.edu.mapper.Mapper;
import cs.miu.edu.repository.ProductRepo;
import cs.miu.edu.utils.ProductCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private Mapper mapper;

    @Autowired
    private ProductCodeGenerator productCodeGenerator;

    public ProductDto saveProduct(ProductDto productDto) {
        productDto.setProductCode(productCodeGenerator.productCodeGenerator());

        Product savedProduct = productRepo.save(mapper.mapToProduct(productDto));
        return mapper.mapToDto(savedProduct);
    }

    public void saveAllProducts(List<Product> products) {
        for (Product product : products) {
            productRepo.save(product);
        }
    }

    public List<ProductDto> findAllProducts() {
        List<ProductDto> productDtos = productRepo.findAll().stream()
                .map((Product product) -> mapper.mapToDto(product)).collect(Collectors.toList());
        return productDtos;

    }

    public Optional<ProductDto> findProductByProductCode(String productCode) {
        Optional<Product> productOptional = productRepo.getProductByProductCode(productCode);
        if (productOptional.isEmpty()) {
            return Optional.empty();
        }
        Product product = productOptional.get();
        Optional<ProductDto> productDtoOptional = Optional.of(mapper.mapToDto(product));
        return productDtoOptional;

    }


    //to verify product code and calculate sub total value
    public OrderResponseDto findOrdersPriceByProductCode(List<OrderLineDto> orderLineDtos) {
        List<OrderLineDto> ordersList = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();
        Product product = null;
        double total = 0;

        for (OrderLineDto orderLineDto : orderLineDtos) {

            Optional<Product> productOptional = productRepo.getProductByProductCode(orderLineDto.getProductCode());
            if (productOptional.isEmpty()) {

                errorMessages.add("Code with " + orderLineDto.getProductCode() + " doesnt match with any products");

            } else {
                product = productOptional.get();
                if (orderLineDto.getQuantity() > product.getAvailableUnit()) {
                    errorMessages.add("Code with " + orderLineDto.getProductCode() + " have less product in stock than you required");
                }
                product.setAvailableUnit(product.getAvailableUnit() - orderLineDto.getQuantity());
                products.add(product);
                total = total + orderLineDto.getQuantity() * product.getPrice();
                ordersList.add(OrderLineDto.builder()
                        .productName(product.getProductName())
                        .productCode(product.getProductCode())
                        .unitCost(product.getPrice())
                        .quantity(orderLineDto.getQuantity())
                        .subTotal(orderLineDto.getQuantity() * product.getPrice()).build());
            }
        }

        if (errorMessages.size() > 0) {
            return OrderResponseDto.builder().status(Status.FAILURE)
                    .errorMessages(errorMessages).build();
        }
        productRepo.saveAll(products);
        return OrderResponseDto.builder().status(Status.SUCCESS)
                .orderLines(ordersList)
                .total(new Double(total)).build();

    }

    public OrderResponseDto returnOrderProduct(List<OrderLineDto> orderLineDtos) {
        List<Product> products = new ArrayList<>();
        for (OrderLineDto orderLineDto : orderLineDtos) {
            Product product = productRepo.getProductByProductCode(orderLineDto.getProductCode()).get();
            product.setAvailableUnit(product.getAvailableUnit() + orderLineDto.getQuantity());
            products.add(product);

        }
        productRepo.saveAll(products);
        return OrderResponseDto.builder()
                .status(Status.SUCCESS).build();
    }

}
