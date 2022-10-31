package com.InstiCab.service;

import com.InstiCab.models.EarningsHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EarningsHistoryService {
    void saveEarning(EarningsHistory earning);

    List<EarningsHistory> getEarningHistory(Long driverId);
}
