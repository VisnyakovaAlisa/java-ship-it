package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel>{
    private final int maxWeight;
    private  List<T> parcels = new ArrayList<>();

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }
    public void addParcel(T parcelNew){
        int currentWeight = 0;
        for (T parcel : parcels){
            currentWeight += parcel.getWeight();
        }
        if (currentWeight + parcelNew.getWeight() <= maxWeight) { 
            parcels.add(parcelNew);
        } else{
            System.out.println("Превышен максимальный вес коробки");
        }
    }
    public List<T> getAllParcels() {
        return parcels;
    }
}