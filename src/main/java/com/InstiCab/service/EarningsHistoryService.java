package com.InstiCab.service;

import com.InstiCab.dto.EarningsHistoryDto;
import com.InstiCab.model.EarningsHistory;
import java.util.List;

public interface EarningsHistoryService {
    void saveEarningsHistory(EarningsHistoryDto earningsHistoryDto);

    EarningsHistory findByEarningId(Long id);

    List<EarningsHistoryDto> findAllEarnings();
}