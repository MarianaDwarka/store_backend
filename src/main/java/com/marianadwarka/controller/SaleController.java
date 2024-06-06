package com.marianadwarka.controller;

import com.marianadwarka.dto.*;
import com.marianadwarka.model.Sale;
import com.marianadwarka.service.ISaleService;
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
import java.util.Map;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final ISaleService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<GenericResponseRecord<SaleDTO>> readAll() throws Exception{
        List<SaleDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(list)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<SaleDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        SaleDTO dto = convertToDto(service.readById(id));

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(dto)));
    }

    @PostMapping
    public ResponseEntity<SaleDTO> save(@Valid @RequestBody SaleDTO dto) throws Exception{
        Sale obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody SaleDTO dto) throws Exception{
        //dto.setIdSale(id);
        Sale obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    /////////////////////////
    @GetMapping("/resume")
    public ResponseEntity<List<ProcedureDTO>> getSaleResume1(){
        return ResponseEntity.ok(service.callProcedure1());
    }

    @GetMapping("/resume2")
    public ResponseEntity<List<IProcedureDTO>> getSaleResume2(){
        return ResponseEntity.ok(service.callProcedure2());
    }

    @GetMapping("/resume3")
    public ResponseEntity<List<ProcedureDTO>> getSaleResume3(){
        return ResponseEntity.ok(service.callProcedure3());
    }


    @GetMapping("/resume4")
    public ResponseEntity<Void> getSaleResume4(){
        service.callProcedure4();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mostexpensive")
    public ResponseEntity<SaleDTO> getMostExpensive(){
        SaleDTO dto = convertToDto(service.getSaleMostExpensive());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/bestseller")
    public ResponseEntity<String> getBestSeller(){
        String person = service.getBestSellerPerson();

        return ResponseEntity.ok(person);
    }

    @GetMapping("/sellercount")
    public ResponseEntity<Map<String, Long>> getSellerCount(){
        Map<String, Long> byUser = service.getSalesCountBySeller();

        return ResponseEntity.ok(byUser);
    }

    @GetMapping("/bestproduct")
    public ResponseEntity<Map<String, Double>> getBestProduct(){
        Map<String, Double> byProduct = service.getMostSellerProduct();

        return ResponseEntity.ok(byProduct);
    }

    ////////////////////////////
    private SaleDTO convertToDto(Sale obj){
        return modelMapper.map(obj, SaleDTO.class);
    }

    private Sale convertToEntity(SaleDTO dto){
        return modelMapper.map(dto, Sale.class);
    }


}
