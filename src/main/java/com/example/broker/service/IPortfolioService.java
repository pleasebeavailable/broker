package com.example.broker.service;

import com.example.broker.repository.model.Portfolio;
import com.example.broker.web.dto.PortfolioDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPortfolioService {

    PortfolioDTO getPortfolio(long id);

//    PortfolioDTO createPortfolio();

    PortfolioDTO updatePortfolio(long id, Portfolio portfolio);

    ResponseEntity<Object> deletePortfolio(long id);

    List<PortfolioDTO> savePortfolios(List<Portfolio> accountPortfolios);


}
