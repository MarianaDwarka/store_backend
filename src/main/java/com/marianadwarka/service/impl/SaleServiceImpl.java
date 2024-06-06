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

    @Override
    public List<ProcedureDTO> callProcedure1() {
        List<ProcedureDTO> procedureDTOList = new ArrayList<>();
        repo.callProcedure1().forEach(det -> {
            ProcedureDTO procedureDTO = new ProcedureDTO();
            procedureDTO.setQuantityfn((Integer) det[0]);
            procedureDTO.setDatetimefn((String) det[1]);

            procedureDTOList.add(procedureDTO);
        });

        return procedureDTOList;
    }

    @Override
    public List<IProcedureDTO> callProcedure2() {
        return repo.callProcedure2();
    }

    @Override
    public List<ProcedureDTO> callProcedure3() {
        return repo.callProcedure3();
    }
    @Override
    public void callProcedure4() {
        repo.callProcedure4();
    }

    @Override
    public Sale getSaleMostExpensive() {
        return repo.findAll()
                .stream()
                .max(Comparator.comparing(Sale::getTotal))
                .orElse(new Sale());
    }

    @Override
    public String getBestSellerPerson() {
        Map<String, Double> byUser = repo.findAll()
                .stream()
                .collect(groupingBy(s -> s.getUser().getUsername(), summingDouble(Sale::getTotal)));

        return Collections.max(byUser.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
    }

    @Override
    public Map<String, Long> getSalesCountBySeller() {
        Map<String, Long> byUser = repo.findAll()
                .stream()
                .collect(groupingBy(s -> s.getUser().getUsername(), counting()));

        return byUser;

    }

    @Override
    public Map<String, Double> getMostSellerProduct() {
        Stream<Sale> saleStream = repo.findAll().stream();
        Stream<List<SaleDetail>> lsStream = saleStream.map(Sale::getDetails); //sale -> sale.getDetails()

        //[ [det1,det2],   [det3, det4],  [det5, det6, det7] ....               ]
        //[det1, det2, det3, det4, det5 ....] Stream<SaleDetail>
        Stream<SaleDetail> streamDetail = lsStream.flatMap(Collection::stream); //list -> list.stream()

        Map<String, Double> byProduct = streamDetail
                .collect(groupingBy(d -> d.getProduct().getName(), summingDouble(SaleDetail::getQuantity)));

        return byProduct.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new  //() -> new HashMap<>()
                ));
    }
}
