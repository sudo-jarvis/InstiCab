package com.InstiCab.service.Implementation;

import com.InstiCab.dao.PassengerDAO;
import com.InstiCab.models.Passenger;
import com.InstiCab.service.PassengerService;
import com.InstiCab.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerDAO passengerDAO;
    private final UserService userService;

    public PassengerServiceImpl(PassengerDAO passengerDAO,UserService userService){
        this.passengerDAO = passengerDAO;
        this.userService = userService;
    }
    @Override
    public void savePassenger(String username) {
        passengerDAO.savePassenger(username);
    }

    @Override
    public Long getLoggedInPassengerId() {
        String username = userService.findLoggedInUsername();
        return passengerDAO.getPassengerByUsername(username).getPassengerId();
    }

    @Override
    public Passenger getPassengerByPassengerId(Long passengerId) {
        return passengerDAO.getPassengerByPassengerId(passengerId);
    }

    @Override
    public Long getLoggedPassengerIdByUsername(String username) {
        return passengerDAO.getPassengerByUsername(username).getPassengerId();
    }
}
