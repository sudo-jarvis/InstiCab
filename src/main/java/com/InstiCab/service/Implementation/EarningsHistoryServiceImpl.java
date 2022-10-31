package com.InstiCab.service.Implementation;

import com.InstiCab.dao.DriverDAO;
import com.InstiCab.dao.EarningsHistoryDAO;
import com.InstiCab.models.EarningsHistory;
import com.InstiCab.service.EarningsHistoryService;
import com.InstiCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EarningsHistoryServiceImpl implements EarningsHistoryService {
    private final EarningsHistoryDAO earningsHistoryDAO;

    @Autowired
    public EarningsHistoryServiceImpl(EarningsHistoryDAO earningsHistoryDAO){
        this.earningsHistoryDAO = earningsHistoryDAO;
    }
    @Override
    public void saveEarning(EarningsHistory earning) {
        earningsHistoryDAO.saveEarning(earning);
    }

    @Override
    public List<EarningsHistory> getEarningHistory(Long driverId) {
        return earningsHistoryDAO.getEarningHistory(driverId);
    }
}
