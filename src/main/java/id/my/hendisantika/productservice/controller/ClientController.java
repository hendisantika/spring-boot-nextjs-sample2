package id.my.hendisantika.productservice.controller;

import id.my.hendisantika.productservice.dto.ClientDTO;
import id.my.hendisantika.productservice.service.ClientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : product-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/7/24
 * Time: 07:38
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping(value = "/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientServices clientServices;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<ClientDTO> clientList = clientServices.findAll();
        return ResponseEntity.ok().body(clientList);
    }
}
