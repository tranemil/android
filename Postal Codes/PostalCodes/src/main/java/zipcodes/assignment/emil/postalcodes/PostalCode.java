package zipcodes.assignment.emil.postalcodes;

public class PostalCode {
    private int id;
    private String country;
    private String postalCode;
    private String province;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String city;
    private double latitude;
    private double longitude;


    public PostalCode(int id, String country, String postalCode, String city, String province, double latitude, double longitude) {
        this.id = id;
        this.postalCode = postalCode;
        this.country = country;
        this.city = city;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toString() {
        return getId() + " " + getCountry() + " " + getPostalCode()+ " " + getProvince() + " " + getLatitude() + " " + getLongitude();
    }
}
