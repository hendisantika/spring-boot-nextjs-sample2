package id.my.hendisantika.productservice;

import id.my.hendisantika.productservice.entity.Product;
import id.my.hendisantika.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootNextjsSample2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNextjsSample2Application.class, args);
    }

    @Bean
    public CommandLineRunner initData(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(1, "Buku Sinar Dunia", 2500, "Alat Tulis Kantor"));
            productRepository.save(new Product(2, "Pulpen Boxy", 3500, "Alat Tulis Kantor"));
            productRepository.save(new Product(3, "Pulpen Pilot", 2500, "Alat Tulis Kantor"));
            productRepository.save(new Product(4, "Pulpen Snowman", 2500, "Alat Tulis Kantor"));
            productRepository.save(new Product(5, "Patlot 2B", 2500, "Alat Tulis Kantor"));
            productRepository.save(new Product(6, "Panyeukeutan", 1500, "Alat Tulis Kantor"));
            productRepository.save(new Product(7, "Penghapus", 1500, "Alat Tulis Kantor"));
            productRepository.save(new Product(8, "Sampul Buku", 2500, "Alat Tulis Kantor"));
            productRepository.save(new Product(9, "Spidol Snowman", 2500, "Alat Tulis Kantor"));
            productRepository.save(new Product(10, "Tinta Refill Spidol Whiteboard", 2500, "Alat Tulis Kantor"));
        };
    }
}
