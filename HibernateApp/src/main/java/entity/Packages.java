package entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Packages {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PACKAGE_ID")
    private BigInteger packageId;
    @Basic
    @Column(name = "PACKAGE_SIZE")
    private String packageSize;
    @Basic
    @Column(name = "PACKAGE_PRIORITY")
    private String packagePriority;
    @Basic
    @Column(name = "PACKAGE_WEIGHT")
    private String packageWeight;
    @Basic
    @Column(name = "TO_ADDRESS_ID")
    private BigInteger toAddressId;
    @Basic
    @Column(name = "FROM_ADDRESS_ID")
    private BigInteger fromAddressId;
    @Basic
    @Column(name = "SERVICE_PLACE_ID")
    private BigInteger servicePlaceId;
    @Basic
    @Column(name = "RECEIVER_ID")
    private BigInteger receiverId;
    @Basic
    @Column(name = "SENDER_ID")
    private BigInteger senderId;
    @Basic
    @Column(name = "COURIER_ID")
    private BigInteger courierId;

    public BigInteger getPackageId() {
        return packageId;
    }

    public void setPackageId(BigInteger packageId) {
        this.packageId = packageId;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getPackagePriority() {
        return packagePriority;
    }

    public void setPackagePriority(String packagePriority) {
        this.packagePriority = packagePriority;
    }

    public String getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(String packageWeight) {
        this.packageWeight = packageWeight;
    }

    public BigInteger getToAddressId() {
        return toAddressId;
    }

    public void setToAddressId(BigInteger toAddressId) {
        this.toAddressId = toAddressId;
    }

    public BigInteger getFromAddressId() {
        return fromAddressId;
    }

    public void setFromAddressId(BigInteger fromAddressId) {
        this.fromAddressId = fromAddressId;
    }

    public BigInteger getServicePlaceId() {
        return servicePlaceId;
    }

    public void setServicePlaceId(BigInteger servicePlaceId) {
        this.servicePlaceId = servicePlaceId;
    }

    public BigInteger getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(BigInteger receiverId) {
        this.receiverId = receiverId;
    }

    public BigInteger getSenderId() {
        return senderId;
    }

    public void setSenderId(BigInteger senderId) {
        this.senderId = senderId;
    }

    public BigInteger getCourierId() {
        return courierId;
    }

    public void setCourierId(BigInteger courierId) {
        this.courierId = courierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Packages packages = (Packages) o;

        if (packageId != null ? !packageId.equals(packages.packageId) : packages.packageId != null) return false;
        if (packageSize != null ? !packageSize.equals(packages.packageSize) : packages.packageSize != null)
            return false;
        if (packagePriority != null ? !packagePriority.equals(packages.packagePriority) : packages.packagePriority != null)
            return false;
        if (packageWeight != null ? !packageWeight.equals(packages.packageWeight) : packages.packageWeight != null)
            return false;
        if (toAddressId != null ? !toAddressId.equals(packages.toAddressId) : packages.toAddressId != null)
            return false;
        if (fromAddressId != null ? !fromAddressId.equals(packages.fromAddressId) : packages.fromAddressId != null)
            return false;
        if (servicePlaceId != null ? !servicePlaceId.equals(packages.servicePlaceId) : packages.servicePlaceId != null)
            return false;
        if (receiverId != null ? !receiverId.equals(packages.receiverId) : packages.receiverId != null) return false;
        if (senderId != null ? !senderId.equals(packages.senderId) : packages.senderId != null) return false;
        if (courierId != null ? !courierId.equals(packages.courierId) : packages.courierId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = packageId != null ? packageId.hashCode() : 0;
        result = 31 * result + (packageSize != null ? packageSize.hashCode() : 0);
        result = 31 * result + (packagePriority != null ? packagePriority.hashCode() : 0);
        result = 31 * result + (packageWeight != null ? packageWeight.hashCode() : 0);
        result = 31 * result + (toAddressId != null ? toAddressId.hashCode() : 0);
        result = 31 * result + (fromAddressId != null ? fromAddressId.hashCode() : 0);
        result = 31 * result + (servicePlaceId != null ? servicePlaceId.hashCode() : 0);
        result = 31 * result + (receiverId != null ? receiverId.hashCode() : 0);
        result = 31 * result + (senderId != null ? senderId.hashCode() : 0);
        result = 31 * result + (courierId != null ? courierId.hashCode() : 0);
        return result;
    }
}
