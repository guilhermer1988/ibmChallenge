package com.ibmchallengejava.ibmchallenge.service;

import com.ibmchallengejava.ibmchallenge.model.Account;
import com.ibmchallengejava.ibmchallenge.model.Client;
import com.ibmchallengejava.ibmchallenge.repository.AccountRepository;
import com.ibmchallengejava.ibmchallenge.repository.ClientRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public Client findByEmail(String email) {
        Optional<Client> client = clientRepository.findByEmail(email);
        return client.orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public void insert(Client client) {
        clientRepository.save(client);
    }

    public void delete(Client client) {
        try {
            clientRepository.delete(client);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Client not found");
        }
    }

    public Client createClient(@NotNull @Valid Client client) {
        Client newClient = clientRepository.save(client);

        Account account = client.getAccount();
        account.setClient(newClient);
        accountRepository.save(account);

        return newClient;
    }

    public Client updateClient(Long id, @NotNull @Valid Client client) {
        Client clientEntity = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));

        clientEntity.setName(client.getName());
        clientEntity.setAge(client.getAge());
        clientEntity.setEmail(client.getEmail());
        clientEntity.getAccount().setAccountNumber(client.getAccount().getAccountNumber());
        clientEntity.getAccount().setAccountBalance(client.getAccount().getAccountBalance());

        Client newClient = clientRepository.save(clientEntity);

        return newClient;
    }


}