package id.my.hendisantika.productservice.service;

import id.my.hendisantika.productservice.dto.ClientDTO;
import id.my.hendisantika.productservice.entity.Client;
import id.my.hendisantika.productservice.exception.ResourceNotFoundException;
import id.my.hendisantika.productservice.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClientServices {

    private final ClientRepository clientRepository;

    public List<ClientDTO> findAll() {
        List<Client> list = clientRepository.findAll();
        List<ClientDTO> listDto = list.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());

        return listDto;
    }

    public ClientDTO findById(Long id) {
        Optional<Client> optional = clientRepository.findById(id);
        Client entity = optional.orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        return new ClientDTO(entity);
    }

    public ClientDTO insert(ClientDTO data) {
        Client entity = new Client();
        copyToEntity(data, entity);
        clientRepository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client entity = clientRepository.getOne(id);
            copyToEntity(dto, entity);
            repository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found");
        }
    }
}
