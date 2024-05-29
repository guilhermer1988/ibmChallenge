package com.ibmchallengejava.ibmchallenge.controller;

import com.ibmchallengejava.ibmchallenge.model.Account;
import com.ibmchallengejava.ibmchallenge.model.Client;
import com.ibmchallengejava.ibmchallenge.repository.AccountRepository;
import com.ibmchallengejava.ibmchallenge.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    private ClientRepository _clientRepository;
    //
    public ClientController(ClientRepository clientRepository) {
        this._clientRepository = clientRepository;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/Client")
    public ResponseEntity<List<Client>> getClient() {
        List<Client> clients = _clientRepository.findAll();
        return ResponseEntity.ok(clients);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/Client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable long id) {
        Optional<Client> client = _clientRepository.findById(id);
        return client.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/Client")
    public ResponseEntity<Client> createClient(@RequestBody Client newClient) {
        Client newClientResult = _clientRepository.save(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClientResult);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/Client/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable long id, @RequestBody Client newClient) {
        if (!_clientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        newClient.setId(id);
        Client newClientResult = _clientRepository.save(newClient);
        return ResponseEntity.ok(newClientResult);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/Client/{id}")
    public void deleteClient(@PathVariable long id) {
        _clientRepository.deleteById(id);
    }
}
