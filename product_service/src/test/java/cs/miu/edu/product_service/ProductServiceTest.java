package cs.miu.edu.product_service;

import cs.miu.edu.domain.Category;
import cs.miu.edu.domain.Product;
import cs.miu.edu.dto.ProductDto;
import cs.miu.edu.product_repository.ProductsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {


    @Captor
    ArgumentCaptor<Product> productArgumentCaptor;
    private ProductsService productsService;
    @Mock
    private ProductsRepository productsRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productsService= new ProductsService(productsRepository);
    }

    @Test
    public void should_return_list_of_products_when_get_products_is_called(){
        List<Product> products= Arrays.asList(Product.builder()
                        .productName("product1")
                                .productCode("101A")
                                        .build());
        when(productsRepository.findAll()).thenReturn(products);
        List<ProductDto> productDtoList = productsService.getProducts();
        assertThat(productDtoList).isNotNull();
        assertThat(productDtoList.size()).isEqualTo(1);
        assertThat(productDtoList.get(0).getProductCode()).isEqualTo("101A");
        assertThat(productDtoList.get(0).getProductName()).isEqualTo("product1");



    }

    @Test
    public void should_return_product_when_get_products_by_product_code_is_called(){
       Product product= Product.builder()
                .productName("product1")
                .productCode("101A")
                .build();
       String productcode= "101A";
        when(productsRepository.findByProductCode(productcode)).thenReturn(Optional.of(product));
        ProductDto productDto= productsService.getProductByProductCode(productcode);
        assertThat(productDto).isNotNull();
        assertThat(productDto.getProductCode()).isEqualTo("101A");
        assertThat(productDto.getProductName()).isEqualTo("product1");
 }

 @Test
    public void should_return_product_with_product_id_when_save_product_is_called(){
        ProductDto productDto= ProductDto.builder()
                .productName("Product1")
                .productCode("101A")
                .productCategory(Category.FOOD)
                .price(101.0)
                .build();

        Product product= Product.builder()
                .productId(2)
                .productName("Product1")
                .productCode("101A")
                .productCategory(Category.FOOD)
                .price(101.0)
                .build();
        when(productsRepository.save(productArgumentCaptor.capture())).thenReturn(product);
        ProductDto response= productsService.save(productDto);
        assertThat(response).isNotNull();
        assertThat(response.getProductName()).isEqualTo("Product1");


 }

 @Test
 public  void should_return_string_without_vowel_letter_when_eliminate_vowel_is_called(){
        String result = productsService.eliminateVowel("abcde");
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("bcd");
 }

    @Test
    public  void should_return_null_or_empty_when_eliminate_vowel_is_called(){
        String result = productsService.eliminateVowel(null);
        assertThat(result).isNull();
        String emptyResult = productsService.eliminateVowel("");
        assertThat(emptyResult).isNotNull();
        assertThat(emptyResult).isEqualTo("");

    }
    @Test
    public  void should_return_consonent_letter_when_getConsonent_is_called(){
        String result = productsService.getAlphabets("1abc3de");
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("abcde");
    }

}
