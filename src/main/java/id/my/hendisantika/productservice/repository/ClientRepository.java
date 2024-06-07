package id.my.hendisantika.productservice.repository;

import id.my.hendisantika.productservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : product-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/7/24
 * Time: 07:32
 * To change this template use File | Settings | File Templates.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}
