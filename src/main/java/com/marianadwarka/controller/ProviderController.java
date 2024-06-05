package com.marianadwarka.controller;

import com.marianadwarka.dto.GenericResponse;
import com.marianadwarka.dto.GenericResponseRecord;
import com.marianadwarka.dto.ProviderDTO;
import com.marianadwarka.model.Provider;
import com.marianadwarka.service.IProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final IProviderService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponseRecord<ProviderDTO>> readAll() throws Exception{
        List<ProviderDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(list)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ProviderDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        ProviderDTO dto = convertToDto(service.readById(id));

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(dto)));
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> save(@Valid @RequestBody ProviderDTO dto) throws Exception{
        Provider obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ProviderDTO dto) throws Exception{
        //dto.setIdProvider(id);
        Provider obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    ////////////////////////////
    private ProviderDTO convertToDto(Provider obj){
        return modelMapper.map(obj, ProviderDTO.class);
    }

    private Provider convertToEntity(ProviderDTO dto){
        return modelMapper.map(dto, Provider.class);
    }


}