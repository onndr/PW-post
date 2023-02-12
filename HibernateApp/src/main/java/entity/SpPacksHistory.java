package entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "SP_PACKS_HISTORY", schema = "Z02")
public class SpPacksHistory {
    @Basic
    @Column(name = "PACKAGE_ID")
    private BigInteger packageId;
    @Basic
    @Column(name = "SERVICE_PLACE_ID")
    private BigInteger servicePlaceId;
    @Basic
    @Column(name = "STATUS_DATETIME")
    private LocalDate statusDatetime;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "STATUS_ID")
    private BigInteger statusId;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    public BigInteger getPackageId() {
        return packageId;
    }

    public void setPackageId(BigInteger packageId) {
        this.packageId = packageId;
    }

    public BigInteger getServicePlaceId() {
        return servicePlaceId;
    }

    public void setServicePlaceId(BigInteger servicePlaceId) {
        this.servicePlaceId = servicePlaceId;
    }

    public LocalDate getStatusDatetime() {
        return statusDatetime;
    }

    public void setStatusDatetime(LocalDate statusDatetime) {
        this.statusDatetime = statusDatetime;
    }

    public BigInteger getStatusId() {
        return statusId;
    }

    public void setStatusId(BigInteger statusId) {
        this.statusId = statusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPacksHistory that = (SpPacksHistory) o;

        if (packageId != null ? !packageId.equals(that.packageId) : that.packageId != null) return false;
        if (servicePlaceId != null ? !servicePlaceId.equals(that.servicePlaceId) : that.servicePlaceId != null)
            return false;
        if (statusDatetime != null ? !statusDatetime.equals(that.statusDatetime) : that.statusDatetime != null)
            return false;
        if (statusId != null ? !statusId.equals(that.statusId) : that.statusId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = packageId != null ? packageId.hashCode() : 0;
        result = 31 * result + (servicePlaceId != null ? servicePlaceId.hashCode() : 0);
        result = 31 * result + (statusDatetime != null ? statusDatetime.hashCode() : 0);
        result = 31 * result + (statusId != null ? statusId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
