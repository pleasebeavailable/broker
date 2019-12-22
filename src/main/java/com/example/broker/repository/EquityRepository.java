package com.example.broker.repository;

import com.example.broker.repository.model.Equity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquityRepository extends JpaRepository<Equity, Long> {

    List<Equity> findById(long id);
}
