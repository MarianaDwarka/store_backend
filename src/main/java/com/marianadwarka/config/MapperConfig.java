package com.marianadwarka.config;

import com.marianadwarka.dto.CategoryDTO;
import com.marianadwarka.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("categoryMapper")
    public ModelMapper categoryMapper(){
        ModelMapper mapper = new ModelMapper();

        //Handle Missmatches

        //Lectura
        mapper.createTypeMap(Category.class, CategoryDTO.class)
                .addMapping(Category::getName, (dest, v) -> dest.setNameofCategory((String) v));

        //Escritura
        mapper.createTypeMap(CategoryDTO.class, Category.class)
                .addMapping(CategoryDTO::getNameofCategory, (dest, v) -> dest.setName((String) v));

        return mapper;
    }

    @Bean("productMapper")
    public ModelMapper productMapper(){
        ModelMapper mapper = new ModelMapper();

        //Lectura
        /*mapper.createTypeMap(Product.class, ProductDTO.class)
                .addMapping(e -> e.getCategory().getIdCategory(), (dest, v) -> dest.setIdCategoria((Integer) v));*/
        //Escritura
        /*mapper.createTypeMap(ProductDTO.class, Product.class)
                .addMapping(ProductDTO::getIdCategoria, (dest, v)-> dest.getCategory().setIdCategory((Integer) v));*/

        return mapper;
    }

    @Bean("defaultMapper")
    public ModelMapper defaultMapper(){
        return new ModelMapper();
    }
}

