package com.marianadwarka.service.impl;

import com.marianadwarka.dto.IProcedureDTO;
import com.marianadwarka.dto.ProcedureDTO;
import com.marianadwarka.model.Sale;
import com.marianadwarka.model.SaleDetail;
import com.marianadwarka.repo.IGenericRepo;
import com.marianadwarka.repo.ISaleRepo;
import com.marianadwarka.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {

    private final ISaleRepo repo;


    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return repo;
    }


}
