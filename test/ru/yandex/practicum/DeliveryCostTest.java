package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeliveryCostTest {

    @Test
    void shouldCalculateStandardParcelCost() {
        StandardParcel parcel = new StandardParcel("Телефон", 10, "Москва", 1);
        assertEquals(20, parcel.calculateDeliveryCost());
    }

    @Test
    void shouldCalculatePerishableParcelCost() {
        PerishableParcel parcel = new PerishableParcel("Торт", 10, "Владивосток", 1, 2);
        assertEquals(30, parcel.calculateDeliveryCost());
    }

    @Test
    void shouldCalculateFragileParcelCost() {
        FragileParcel parcel = new FragileParcel("Ваза", 3, "Москва", 1);
        assertEquals(12, parcel.calculateDeliveryCost());
    }

    @Test
    void shouldNotBeExpiredOnLastValidDay() {
        PerishableParcel parcel = new PerishableParcel("Йогурт", 1, "Москва", 1, 3);
        assertFalse(parcel.isExpired(4));
    }

    @Test
    void shouldBeExpiredOnNextDay() {
        PerishableParcel parcel = new PerishableParcel("Йогурт", 1, "Москва", 1, 3);
        assertTrue(parcel.isExpired(5));
    }

    @Test
    void shouldAddParcelWhenWeightIsWithinLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(15);
        box.addParcel(new StandardParcel("Книга", 10, "Москва", 1));
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void shouldNotAddParcelWhenWeightExceedsLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(15);
        box.addParcel(new StandardParcel("Книга 1", 10, "Москва", 1));
        box.addParcel(new StandardParcel("Книга 2", 10, "Москва", 1));
        assertEquals(1, box.getAllParcels().size());
    }
    @Test
    void shouldAddParcelWhenWeightEqualBoundary() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(15);
        box.addParcel(new StandardParcel("Книга 1", 10, "Москва", 1));
        box.addParcel(new StandardParcel("Книга 2", 5, "Москва", 1));
        assertEquals(2, box.getAllParcels().size());
    }
}