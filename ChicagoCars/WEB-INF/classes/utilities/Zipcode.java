package utilities;

public class Zipcode implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	private String zipCode;
    private String city;
	private String State;
	private float Latitude;
	private float Longitude;
	private String Classification;
	private int Population;
	
	public Zipcode(){
		
	}
	
	public Zipcode(String zipCode,String city,String State){
		this.zipCode = zipCode;
		this.city = city;
		this.State = State;
	}
	
	public Zipcode(String zipCode,String city, String State,float Latitude,float Longitude, String Classification, int Population){
		this.zipCode = zipCode;
		this.city = city;
		this.State = State;
		this.Latitude = Latitude;
		this.Longitude = Longitude;
		this.Classification = Classification;
		this.Population = Population;
    }

	public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

	public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

	public String getState() {
        return State;
    }
    public void setState(String State) {
        this.State = State;
    }

	public float getLatitude() {
        return Latitude;
    }
    public void setLatitude(float Latitude) {
        this.Latitude = Latitude;
    }

	public String getClassification() {
        return Classification;
    }
    public void setClassification(String Classification) {
        this.Classification = Classification;
    }

	public float getLongitude() {
        return Longitude;
    }
    public void setLongitude(float Longitude) {
        this.Longitude = Longitude;
    }

	public int getPopulation() {
        return Population;
    }
    public void setPopulation(int Population) {
        this.Population = Population;
    }
}
