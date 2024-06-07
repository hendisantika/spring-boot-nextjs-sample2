package id.my.hendisantika.productservice.controller;

import id.my.hendisantika.productservice.entity.Product;
import id.my.hendisantika.productservice.repository.ProductRepository;
import id.my.hendisantika.productservice.util.ResponseHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : product-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/7/24
 * Time: 09:54
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findProductByID(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            return ResponseHandle.generate("Product not found :/", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Product product) {
        if (product.getName() == null) {
            return ResponseHandle.generate("You must provide a name for the product", HttpStatus.BAD_REQUEST);
        }

        if (product.getPrice() == null) {
            return ResponseHandle.generate("You must inform the price of the product", HttpStatus.BAD_REQUEST);
        }

        Product newProduct = productRepository.save(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Product product) {
        Optional<Product> oldProduct = productRepository.findById(id);

        if (oldProduct.isEmpty()) {
            return ResponseHandle.generate("Product not found :/", HttpStatus.NOT_FOUND);
        }

        if (product.getName() == null) {
            return ResponseHandle.generate("You must provide a name for the product", HttpStatus.BAD_REQUEST);
        }

        if (product.getPrice() == null) {
            return ResponseHandle.generate("You must inform the price of the product", HttpStatus.BAD_REQUEST);
        }

        Product updateProduct = oldProduct.get();

        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setDescription(product.getDescription());

        productRepository.save(updateProduct);

        return ResponseEntity.noContent().build();
    }
}
