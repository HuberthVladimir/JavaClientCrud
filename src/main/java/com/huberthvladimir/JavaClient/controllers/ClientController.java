package com.huberthvladimir.JavaClient.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.huberthvladimir.JavaClient.dto.ClientDTO;
import com.huberthvladimir.JavaClient.services.ClientService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
        Page<ClientDTO> clients = clientService.findAll(pageable);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        ClientDTO client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO entity) {
        ClientDTO client = clientService.insert(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id,
            @Valid @RequestBody ClientDTO entity) {
        ClientDTO client = clientService.update(id, entity);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
