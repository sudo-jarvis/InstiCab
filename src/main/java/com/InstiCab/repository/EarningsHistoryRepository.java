package com.InstiCab.repository;

import com.InstiCab.model.EarningsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EarningsHistoryRepository extends JpaRepository<EarningsHistory,Long> {
    EarningsHistory findByEarningId(Long id);
}
