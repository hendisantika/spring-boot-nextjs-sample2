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

import static java.util.stream.Collectors.toList;

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

        return list.stream().map(client -> ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .birthDate(client.getBirthDate())
                .cpf(client.getCpf())
                .children(client.getChildren())
                .income(client.getIncome())
                .build()).collect(toList());
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
            clientRepository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found");
        }
    }

    private void copyToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

    public void delete(Long id) {
        try {
            if (!clientRepository.existsById(id)) {
                throw new ResourceNotFoundException("Id not found");
            }
            clientRepository.deleteById(id);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found");
        }
    }
}
