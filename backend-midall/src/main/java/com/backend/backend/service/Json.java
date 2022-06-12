package com.backend.backend.service;

import java.util.List;

import com.backend.backend.dto.ProductDTO;
import com.backend.backend.dto.ProductPromotionDTO;
import com.backend.backend.enumerate.ReceivePromotion;
import com.backend.backend.enumerate.TypePromotion;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Json {
	   Integer id;
	   Integer quantidade;
	   Integer total;
	   Integer categoria;
}
