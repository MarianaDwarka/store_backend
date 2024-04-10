package com.marianadwarka.controller;

import com.marianadwarka.model.Client;
import com.marianadwarka.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService service;

    @GetMapping
    public ResponseEntity<List<Client>> readAll() throws Exception{
        List<Client> list = service.readAll();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> readById(@PathVariable("id") Integer id) throws Exception{
        Client obj = service.readById(id);

        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) throws Exception{
        Client obj = service.save(client);

        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable("id") Integer id, @RequestBody Client client) throws Exception{
        Client obj = service.update(client, id);

        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return ResponseEntity.noContent().build();
    }



}
