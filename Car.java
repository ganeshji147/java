public class Car {
    String brand;
    int year;

    // Constructor
    public Car(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    // Method
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }



    public static void main(String[] args) {
        Car car1 = new Car("Toyota", 2020);
        Car car2 = new Car("Scorpio", 2025);
        Car car3 = new Car("Maruti", 2024);


        car1.displayInfo();
        car2.displayInfo();
        car3.displayInfo();
    }
}