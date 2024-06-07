package id.my.hendisantika.productservice.controller;

import id.my.hendisantika.productservice.dto.ClientDTO;
import id.my.hendisantika.productservice.service.ClientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> find(@PathVariable Long id) {
        ClientDTO clientFounded = clientServices.findById(id);

        return ResponseEntity.ok().body(clientFounded);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO dto) {
        ClientDTO newClient = clientServices.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(newClient);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto) {
        ClientDTO newClient = clientServices.update(id, dto);

        return ResponseEntity.ok().body(newClient);
    }
}
