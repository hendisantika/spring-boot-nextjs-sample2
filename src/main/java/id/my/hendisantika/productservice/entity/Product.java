package id.my.hendisantika.productservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : product-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/7/24
 * Time: 09:51
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id //gera um ID
    @GeneratedValue(strategy = GenerationType.AUTO) //gera um valor para um ID
    private Integer id;

    @Column(nullable = false) //torna obrigatório esse campo ser preenchido
    private String name;

    @Column(nullable = false) //campos nao pode ser nulo
    private Integer price;

    private String description;
}
