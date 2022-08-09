package cs.miu.edu.product_service;

import cs.miu.edu.domain.Product;
import cs.miu.edu.dto.ProductDto;
import cs.miu.edu.product_repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public List<ProductDto> getProducts() {
        List<Product> products = productsRepository.findAll();
        List<ProductDto> productDtoList = products.stream()
                .map((Product product) -> ProductDto.builder()
                        .productId(product.getProductId())
                        .availableUnit(product.getAvailableUnit())
                        .productCode(product.getProductCode())
                        .productCategory(product.getProductCategory())
                        .productName(product.getProductName())
                        .vendor(product.getVendor())
                        .price(product.getPrice())
                        .build()).collect(Collectors.toList());
        return productDtoList;
    }

    public ProductDto getProductByProductCode(String productCode) {
        Optional<Product> optionalProduct = productsRepository.findByProductCode(productCode);
        if (optionalProduct.isEmpty()) {
            return null;
        }
        Product product = optionalProduct.get();
        ProductDto productDto = ProductDto.builder()
                .productId(product.getProductId())
                .availableUnit(product.getAvailableUnit())
                .productCode(product.getProductCode())
                .productCategory(product.getProductCategory())
                .productName(product.getProductName())
                .vendor(product.getVendor())
                .price(product.getPrice())
                .build();
        return productDto;
    }

    public ProductDto save(ProductDto productDto) {
        Product product = Product.builder()
                .availableUnit(productDto.getAvailableUnit())
                .productCode(productDto.getProductCode())
                .productCategory(productDto.getProductCategory())
                .productName(productDto.getProductName())
                .vendor(productDto.getVendor())
                .price(productDto.getPrice())
                .build();
        Product saveProduct = productsRepository.save(product);
        ProductDto productDto1 = ProductDto.builder()
                .availableUnit(product.getAvailableUnit())
                .productCode(product.getProductCode())
                .productCategory(product.getProductCategory())
                .productName(product.getProductName())
                .vendor(product.getVendor())
                .price(product.getPrice())
                .build();

        return productDto1;
    }

    public Double addNumbers(double v, double v1) {
        return null;
    }

    public String eliminateVowel(String inputString) {
        if(inputString == null) return  null;
        String stringWithoutVowel = "";
        for (int i = 0; i < inputString.length(); i++) {
            Character currentChar = inputString.charAt(i);
            if (!(currentChar == 'a' || currentChar == 'e' || currentChar == 'i'
                    || currentChar == 'o' || currentChar == 'u')) {
                stringWithoutVowel = stringWithoutVowel + currentChar;
            }
        }
        return stringWithoutVowel;
    }

    public String getAlphabets(String inputString) {
        String stringOfAlphabets = "";
        for (int i = 0; i < inputString.length(); i++){
            Character currentCharacter = inputString.charAt(i);
            if(currentCharacter >= 97 && currentCharacter <= 122){
                stringOfAlphabets = stringOfAlphabets + currentCharacter;
            }
        }
        return  stringOfAlphabets;
    }
}
