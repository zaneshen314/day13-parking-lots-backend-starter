package org.afs.pakinglot.domain;

public class Car {
    private String plateNumber;

    public Car(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        return plateNumber.equals(car.plateNumber);
    }

    @Override
    public int hashCode() {
        return plateNumber.hashCode();
    }
}
