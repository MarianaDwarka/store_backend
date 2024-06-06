package com.marianadwarka.repo;

import com.marianadwarka.dto.IProcedureDTO;
import com.marianadwarka.dto.ProcedureDTO;
import com.marianadwarka.model.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface ISaleRepo extends IGenericRepo<Sale, Integer> {

    @Query(value = "select * from fn_sales()", nativeQuery = true)
    List<Object[]> callProcedure1();

    @Query(value = "select * from fn_sales()", nativeQuery = true)
    List<IProcedureDTO> callProcedure2();

    @Query(name = "Sale.fn_sales", nativeQuery = true)
    List<ProcedureDTO> callProcedure3();

    @Procedure(procedureName = "pr_sales")
    void callProcedure4();

}