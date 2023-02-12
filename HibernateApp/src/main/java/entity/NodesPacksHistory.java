package entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "NODES_PACKS_HISTORY", schema = "Z02", catalog = "")
public class NodesPacksHistory {
    @Basic
    @Column(name = "PACKAGE_ID")
    private BigInteger packageId;
    @Basic
    @Column(name = "COOP_NODE_ID")
    private BigInteger coopNodeId;
    @Basic
    @Column(name = "IN_DATETIME")
    private Date inDatetime;
    @Basic
    @Column(name = "OUT_DATETIME")
    private Date outDatetime;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ENTRY_ID")
    private BigInteger entryId;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    public BigInteger getPackageId() {
        return packageId;
    }

    public void setPackageId(BigInteger packageId) {
        this.packageId = packageId;
    }

    public BigInteger getCoopNodeId() {
        return coopNodeId;
    }

    public void setCoopNodeId(BigInteger coopNodeId) {
        this.coopNodeId = coopNodeId;
    }

    public Date getInDatetime() {
        return inDatetime;
    }

    public void setInDatetime(Date inDatetime) {
        this.inDatetime = inDatetime;
    }

    public Date getOutDatetime() {
        return outDatetime;
    }

    public void setOutDatetime(Date outDatetime) {
        this.outDatetime = outDatetime;
    }

    public BigInteger getEntryId() {
        return entryId;
    }

    public void setEntryId(BigInteger entryId) {
        this.entryId = entryId;
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

        NodesPacksHistory that = (NodesPacksHistory) o;

        if (packageId != null ? !packageId.equals(that.packageId) : that.packageId != null) return false;
        if (coopNodeId != null ? !coopNodeId.equals(that.coopNodeId) : that.coopNodeId != null) return false;
        if (inDatetime != null ? !inDatetime.equals(that.inDatetime) : that.inDatetime != null) return false;
        if (outDatetime != null ? !outDatetime.equals(that.outDatetime) : that.outDatetime != null) return false;
        if (entryId != null ? !entryId.equals(that.entryId) : that.entryId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = packageId != null ? packageId.hashCode() : 0;
        result = 31 * result + (coopNodeId != null ? coopNodeId.hashCode() : 0);
        result = 31 * result + (inDatetime != null ? inDatetime.hashCode() : 0);
        result = 31 * result + (outDatetime != null ? outDatetime.hashCode() : 0);
        result = 31 * result + (entryId != null ? entryId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
