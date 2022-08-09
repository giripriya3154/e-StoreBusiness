package cs.miu.edu.product_controller;

import cs.miu.edu.domain.Results;
import cs.miu.edu.dto.ProductDto;
import cs.miu.edu.product_service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }


    @GetMapping("/products")
    public ResponseEntity<?> getProducts(String productCode) {
        if(productCode== null){
            ResponseEntity<?> response= new ResponseEntity<>(productsService.getProducts(), HttpStatus.OK);
            return response;
        }
        ResponseEntity<?> response= new ResponseEntity<>(productsService.getProductByProductCode(productCode), HttpStatus.OK);
        return response;
    }



    public Double addNumbers(double firstNumber, double secondNumber) {
       double result =  productsService.addNumbers(firstNumber, secondNumber);
       return result;
    }

    @GetMapping("/consonants")
    public Results getConsonent(@RequestParam("inputValue") String inputString) {
        String stringWithOnlyConsonent = productsService.getAlphabets(inputString);
        String stringWithoutVowel = productsService.eliminateVowel(stringWithOnlyConsonent);
        return Results.builder().result(stringWithoutVowel).build();
    }

    @PostMapping("/products")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto) {
        ResponseEntity<?> response = new ResponseEntity<>(productsService.save(productDto),HttpStatus.OK);
        return  response;
    }
}
