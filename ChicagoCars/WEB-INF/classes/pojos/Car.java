package pojos;

public class Car {
	String carID, carName, carType, manufacturer, transmission, filename, location;
	int number_of_seats;
    float price, ratingID;
	String airConditioning;
	public Car(String carID, String carName, String carType,
			String manufacturer, String transmission, int number_of_seats,
			float ratingID, String airConditioning, float price, String filename, String location) {
		super();
		this.carID = carID;
		this.carName = carName;
		this.carType = carType;
		this.manufacturer = manufacturer;
		this.transmission = transmission;
		this.number_of_seats = number_of_seats;
		this.ratingID = ratingID;
		this.airConditioning = airConditioning;
		this.price = price;
        this.filename=filename;
        this.location=location;
        
	}
    public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCarID() {
		return carID;
	}
	public void setCarID(String carID) {
		this.carID = carID;
	}
    public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public int getNumber_of_seats() {
		return number_of_seats;
	}
	public void setNumber_of_seats(int number_of_seats) {
		this.number_of_seats = number_of_seats;
	}
	public float getRatingID() {
		return ratingID;
	}
	public void setRatingID(float ratingID) {
		this.ratingID = ratingID;
	}
	public String isAirConditioning() {
		return airConditioning;
	}
	public void setAirConditioning(String airConditioning) {
		this.airConditioning = airConditioning;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}	
}