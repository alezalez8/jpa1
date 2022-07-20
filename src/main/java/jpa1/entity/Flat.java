package jpa1.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flat")
public class Flat {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "district_of_city")
    private String districtOfCity;

    @Column(name = "adress")
    private String adress;

    @Column(name = "area_of_flat")
    private double areaOfFlat;

    @Column(name = "count_of_rooms")
    private int countOfRooms;

    @Column(name = "price")
    private int price;

    @Column(name = "used")
    private boolean isUsed;

    public Flat() {
    }

    public Flat(String districtOfCity, String adress, double areaOfFlat, int countOfRooms, int price) {
        this.districtOfCity = districtOfCity;
        this.adress = adress;
        this.areaOfFlat = areaOfFlat;
        this.countOfRooms = countOfRooms;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrictOfCity() {
        return districtOfCity;
    }

    public void setDistrictOfCity(String districtOfCity) {
        this.districtOfCity = districtOfCity;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getCountOfRooms() {
        return countOfRooms;
    }

    public void setCountOfRooms(int countOfRooms) {
        this.countOfRooms = countOfRooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAreaOfFlat() {
        return areaOfFlat;
    }

    public void setAreaOfFlat(double areaOfFlat) {
        this.areaOfFlat = areaOfFlat;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", districtOfCity='" + districtOfCity + '\'' +
                ", adress='" + adress + '\'' +
                ", areaOfFlat=" + areaOfFlat +
                ", countOfRooms=" + countOfRooms +
                ", price=" + price +
                ", isUsed=" + isUsed +
                '}';
    }
}
