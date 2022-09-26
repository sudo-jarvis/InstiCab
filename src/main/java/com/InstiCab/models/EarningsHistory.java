package com.InstiCab.models;

public class EarningsHistory {
    private Long earningId;

    public void setEarningId(Long earningId) {
        this.earningId = earningId;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setDistanceTravelled(float distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    private float cost;

    public Long getEarningId() {
        return earningId;
    }

    public float getCost() {
        return cost;
    }

    public float getDistanceTravelled() {
        return distanceTravelled;
    }

    private float distanceTravelled;
}
