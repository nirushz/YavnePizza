package il.co.nnz.yavnepizza;

/**
 * Created by User on 20/09/2016.
 */
public class Pizza {

    private long id;
    private String name, phone, address;
    private Double lat, lng;
    private int thumbnail;

    public Pizza(long id, String name, String phone, String address, int thumbnail, Double lat, Double lng) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.thumbnail = thumbnail;
        this.lat = lat;
        this.lng = lng;

    }

    public Pizza(long id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Pizza(long id, String name, String phone, String address, int thumbnail) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return name;
    }
}
