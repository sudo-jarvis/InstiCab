package com.InstiCab.service;

import com.InstiCab.models.EarningsHistory;
import org.springframework.stereotype.Service;

@Service
public interface EarningsHistoryService {
    void saveEarning(EarningsHistory earning);
}
