package com.InstiCab.models;

public class EarningsHistory {
    private Long earningId;
    private float cost;
    private float distanceTravelled;

    public EarningsHistory(Long earningId, float cost, float distanceTravelled) {
        this.earningId = earningId;
        this.cost = cost;
        this.distanceTravelled = distanceTravelled;
    }

    public void setEarningId(Long earningId) {
        this.earningId = earningId;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setDistanceTravelled(float distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public Long getEarningId() {
        return earningId;
    }

    public float getCost() {
        return cost;
    }

    public float getDistanceTravelled() {
        return distanceTravelled;
    }
}
