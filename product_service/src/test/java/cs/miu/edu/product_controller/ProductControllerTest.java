package cs.miu.edu.product_controller;
import cs.miu.edu.domain.Category;
import cs.miu.edu.domain.Results;
import cs.miu.edu.dto.ProductDto;
import cs.miu.edu.product_service.ProductsService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductsService productsService;

    private ProductsController controller;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        controller = new ProductsController(productsService);
    }

    @Test
    public void should_add_numbers_and_return_correct_result(){
        when(productsService.addNumbers(anyDouble(), anyDouble())).thenReturn(11.9);
       // when(productsService.addNumbers(2, 3)).thenReturn(5.0);
        double result = controller.addNumbers(4.9, 7.0);
        assertThat(result).isEqualTo(11.9);
        double result2 = controller.addNumbers(2,3);
        assertThat(result2).isEqualTo(11.9);
    }

    @Test
    public void should_return_consonent_string_without_vowel_letters_when_eliminate_vowel_is_called(){
        String inputString = "1abcde";
        String result= "bcd";
        when(productsService.eliminateVowel(inputString)).thenReturn("1bcd");
        when(productsService.getAlphabets("1bcd")).thenReturn("bcd");

        when(productsService.getAlphabets("1abcde")).thenReturn("abcde");
        when(productsService.eliminateVowel("abcde")).thenReturn("bcd");

        Results response = controller.getConsonent(inputString);
        assertThat(response).isNotNull();
        assertThat(response.result).isEqualTo(result);
    }



    @Test
    public void should_return_all_products_when_get_product_is_called(){


        List<ProductDto>  productDtoList = Arrays.asList(ProductDto.builder()
                .productName("Product 1")
                .productCode("101A").build());

        when(productsService.getProducts()).thenReturn(productDtoList);
        ResponseEntity<?> response = controller.getProducts(null);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<ProductDto> products = (List<ProductDto>) response.getBody();
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(1);
        assertThat(products.get(0).getProductCode()).isEqualTo("101A");
        assertThat(products.get(0).getProductName()).isEqualTo("Product 1");
    }

    @Test
    public void should_return_product_when_save_product_is_called(){

        ProductDto productDto = ProductDto.builder()
                .productCategory(Category.FOOD)
                .price(5.3)
                .availableUnit(3)
                .productName("IceCream")
                .productCode("101A")
                .build();
        when(productsService.save(productDto)).thenReturn(productDto);
        ResponseEntity<?> response = controller.saveProduct(productDto);
        assertThat(response).isNotNull();
        ProductDto productDto1 = (ProductDto) response.getBody();
        assertThat(productDto1).isNotNull();
        assertThat(productDto1.getProductName()).isEqualTo("IceCream");
        assertThat(productDto1.getProductCode()).isEqualTo("101A");


    }

    @Test
    public void should_return_specific_product_when_get_product_by_id_is_called_with_id(){
        ProductDto productDto= ProductDto.builder()
                .productName("Product 1")
                .productCode("101A").build();
        String productCode = "101A";
       when(productsService.getProductByProductCode(productCode)).thenReturn(productDto);
//        ProductDto product = controller.getProducts(productCode);
        ResponseEntity<?> response = controller.getProducts(productCode);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        ProductDto product = (ProductDto) response.getBody();
        assertThat(product).isNotNull();
        assertThat(product.getProductCode()).isEqualTo("101A");
        assertThat(product.getProductName()).isEqualTo("Product 1");


    }



}
