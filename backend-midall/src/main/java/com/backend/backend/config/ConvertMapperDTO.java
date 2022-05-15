package com.backend.backend.config;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.SourceGetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.backend.dto.CategoryDTO;
import com.backend.backend.dto.ProductDTO;
import com.backend.backend.dto.ProductPromotionDTO;
import com.backend.backend.repository.ProductRepository;

@Configuration
public class ConvertMapperDTO {
    @Bean
    public ModelMapper modelMapper() {
    	
    	
        return new ModelMapper();
    }

   
}