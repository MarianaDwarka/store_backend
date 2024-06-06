package com.marianadwarka.repo;

import com.marianadwarka.dto.IProcedureDTO;
import com.marianadwarka.dto.ProcedureDTO;
import com.marianadwarka.model.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface ISaleRepo extends IGenericRepo<Sale, Integer> {



}