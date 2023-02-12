package entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "SERVICE_PLACES", schema = "Z02", catalog = "")
public class ServicePlaces {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SP_ID")
    private BigInteger spId;
    @Basic
    @Column(name = "ADDRESS_ID")
    private BigInteger addressId;

    public BigInteger getSpId() {
        return spId;
    }

    public void setSpId(BigInteger spId) {
        this.spId = spId;
    }

    public BigInteger getAddressId() {
        return addressId;
    }

    public void setAddressId(BigInteger addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicePlaces that = (ServicePlaces) o;

        if (spId != null ? !spId.equals(that.spId) : that.spId != null) return false;
        if (addressId != null ? !addressId.equals(that.addressId) : that.addressId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = spId != null ? spId.hashCode() : 0;
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        return result;
    }
}
