package ru.yandex.practicum.delivery;
public class PerishableParcel extends Parcel {
    protected static final int COST = 3;
    private int timeToLive;
    public PerishableParcel(String description,int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }
    @Override
    protected int getCost() {
        return COST;
    }
    public boolean isExpired(int currentDay){
        return !((sendDay + timeToLive) >= currentDay);
    }
}
