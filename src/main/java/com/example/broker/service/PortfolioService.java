package com.example.broker.service;

import com.example.broker.repository.model.Portfolio;
import com.example.broker.web.dto.PortfolioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService implements IPortfolioService {

    @Override
    public PortfolioDTO getPortfolio(long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deletePortfolio(long id) {
        return null;
    }

    @Override
    public List<PortfolioDTO> savePortfolios(List<Portfolio> accountPortfolios) {
        return null;
    }

    @Override
    public PortfolioDTO updatePortfolio(long id, Portfolio portfolio) {
        return null;
    }
}
