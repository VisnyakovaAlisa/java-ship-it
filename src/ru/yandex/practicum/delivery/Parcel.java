package ru.yandex.practicum.delivery;

public abstract class Parcel {
    public String description;
    public int weight;
    public String deliveryAddress;
    public int sendDay;

    Parcel(String description,int weight, String deliveryAddress, int sendDay){
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }
    public void packageItem(){
        System.out.println("Посылка " + description + " упакована");
    }
    public void deliver(){
        System.out.println("Посылка " + description + " доставлена по адресу " + deliveryAddress);
    }
    public int calculateDeliveryCost(){
        return weight * getCost();
    }
    public int getWeight(){
        return weight;
    }
    protected abstract int getCost();
}
