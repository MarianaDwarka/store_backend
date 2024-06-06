package com.marianadwarka.service;

import com.marianadwarka.dto.IProcedureDTO;
import com.marianadwarka.dto.ProcedureDTO;
import com.marianadwarka.model.Sale;

import java.util.List;
import java.util.Map;

public interface ISaleService extends ICRUD<Sale, Integer>{

    List<ProcedureDTO> callProcedure1();
    List<IProcedureDTO> callProcedure2();
    List<ProcedureDTO> callProcedure3();
    void callProcedure4();
    Sale getSaleMostExpensive(); //obtener la venta mayor
    String getBestSellerPerson(); //obtener el nombre del mejor vendedor
    Map<String, Long> getSalesCountBySeller();
    Map<String, Double> getMostSellerProduct(); //el producto mas vendido
}