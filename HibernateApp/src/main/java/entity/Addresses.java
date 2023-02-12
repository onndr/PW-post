package entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Addresses {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ADDRESS_ID")
    private BigInteger addressId;
    @Basic
    @Column(name = "TOWN")
    private String town;
    @Basic
    @Column(name = "STREET")
    private String street;
    @Basic
    @Column(name = "BUILDING_NUMBER")
    private String buildingNumber;
    @Basic
    @Column(name = "APPARTMENT_NUMBER")
    private BigInteger appartmentNumber;
    @Basic
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Basic
    @Column(name = "COUNTRY_ID")
    private BigInteger countryId;

    public BigInteger getAddressId() {
        return addressId;
    }

    public void setAddressId(BigInteger addressId) {
        this.addressId = addressId;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public BigInteger getAppartmentNumber() {
        return appartmentNumber;
    }

    public void setAppartmentNumber(BigInteger appartmentNumber) {
        this.appartmentNumber = appartmentNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public BigInteger getCountryId() {
        return countryId;
    }

    public void setCountryId(BigInteger countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Addresses addresses = (Addresses) o;

        if (addressId != null ? !addressId.equals(addresses.addressId) : addresses.addressId != null) return false;
        if (town != null ? !town.equals(addresses.town) : addresses.town != null) return false;
        if (street != null ? !street.equals(addresses.street) : addresses.street != null) return false;
        if (buildingNumber != null ? !buildingNumber.equals(addresses.buildingNumber) : addresses.buildingNumber != null)
            return false;
        if (appartmentNumber != null ? !appartmentNumber.equals(addresses.appartmentNumber) : addresses.appartmentNumber != null)
            return false;
        if (postalCode != null ? !postalCode.equals(addresses.postalCode) : addresses.postalCode != null) return false;
        if (countryId != null ? !countryId.equals(addresses.countryId) : addresses.countryId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressId != null ? addressId.hashCode() : 0;
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (buildingNumber != null ? buildingNumber.hashCode() : 0);
        result = 31 * result + (appartmentNumber != null ? appartmentNumber.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        return result;
    }
}
