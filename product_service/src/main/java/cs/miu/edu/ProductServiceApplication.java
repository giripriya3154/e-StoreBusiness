package cs.miu.edu;

import cs.miu.edu.domain.Category;
import cs.miu.edu.domain.Product;
import cs.miu.edu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication implements CommandLineRunner {
    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    public void loadData() {
        List<Product> productList = Arrays.asList(Product.builder()
                        .vendor("Prakash Dahal")
                        .productCode("A101")
                        .availableUnit(50)
                        .productName("Vanilla Cake")
                        .price(10.2)
                        .productCategory(Category.FOOD)
                        .build(),
                Product.builder()
                        .vendor("Priya Giri")
                        .productCode("A102")
                        .availableUnit(40)
                        .productName("Gucchi Shoes")
                        .price(15.0)
                        .productCategory(Category.APPAREL)
                        .build(),
                Product.builder()
                        .vendor("Sudip Sherestha")
                        .productCode("A103")
                        .availableUnit(43)
                        .productName("Bulb ")
                        .price(2.99)
                        .productCategory(Category.ELECTRONICS)
                        .build(),
                Product.builder()
                        .vendor("Sudip Sherestha")
                        .productCode("A104")
                        .availableUnit(60)
                        .productName("Wire")
                        .price(13.0)
                        .productCategory(Category.ELECTRONICS)
                        .build(),
                Product.builder()
                        .vendor("Rasmi bk")
                        .productCode("A105")
                        .availableUnit(40)
                        .productName("Social Studies")
                        .price(18.0)
                        .productCategory(Category.APPAREL)
                        .build()
                );
        productService.saveAllProducts(productList);

    }
}
