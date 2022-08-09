package cs.miu.edu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.JsonPath;
import cs.miu.edu.domain.Category;
import cs.miu.edu.domain.Product;
import cs.miu.edu.product_controller.ProductsController;
import cs.miu.edu.product_repository.ProductsRepository;
import cs.miu.edu.product_service.ProductsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductIntegrationTest {

    private MockMvc mockMvc;


    @Mock
    private ProductsRepository productsRepository;

    ProductsController productsController;

    ProductsService productsService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
         productsService = new ProductsService(productsRepository);
        productsController = new ProductsController(productsService);
        mockMvc = MockMvcBuilders.standaloneSetup(productsController).build();
    }

    @Test
    public void test() throws Exception {


        ResultActions resultActions = this.mockMvc.perform(get("/consonants")
                .queryParam("inputValue", "1dfgab"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("dfgb")));

    }

    @Test
    public void should_return_list_when_request_to_get_products() throws Exception {
        List<Product> products= Arrays.asList(Product.builder()
                .productName("product1")
                .productCode("101A")
                .build(),Product.builder()
                        .productId(1)
                        .productCategory(Category.FOOD)
                        .price(5.3)
                        .availableUnit(3)
                        .productName("IceCream")
                        .productCode("101B")
                        .build()
                );
        when(productsRepository.findAll()).thenReturn(products);

        ResultActions resultActions = this.mockMvc.perform(get("/products"));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].productCode", hasItems("101A", "101B")))
                .andExpect(jsonPath("$[0].productName", is("product1")));
    }

    @Test
    public void should_return_empty_list_when_request_to_get_products() throws Exception {
        when(productsRepository.findAll()).thenReturn(Arrays.asList());
        ResultActions resultActions = this.mockMvc.perform(get("/products"));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void should_return_product_when_get_products_is_invoked() throws Exception {
        Product product = Product.builder()
                .productId(1)
                .productCategory(Category.FOOD)
                .price(5.3)
                .availableUnit(3)
                .productName("IceCream")
                .productCode("101A")
                .build();
        when(productsRepository.findByProductCode("101A")).thenReturn(Optional.of(product));
        ResultActions resultActions = this.mockMvc.perform(get("/products")
                .queryParam("productCode", "101A"));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.productCode", is("101A")))
                .andExpect(jsonPath("$.price", is(5.3)))

                .andExpect(jsonPath("$.productName", is("IceCream")));

    }

    @Test
    public void should_retur_product_when_save_product_is_called() throws Exception {
        Product product = Product.builder()
                .productCategory(Category.FOOD)
                .price(5.3)
                .availableUnit(3)
                .productName("IceCream")
                .productCode("101A")
                .build();
        Product savedProduct = Product.builder()
                .productId(1)
                .productCategory(Category.FOOD)
                .price(5.3)
                .availableUnit(3)
                .productName("IceCream")
                .productCode("101A")
                .build();
        ObjectMapper mapper = new ObjectMapper();
     //   mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(product );
        when(productsRepository.save(product)).thenReturn(savedProduct);
        ResultActions resultActions = this.mockMvc.perform(post("/products")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", is("IceCream")));



    }



}
