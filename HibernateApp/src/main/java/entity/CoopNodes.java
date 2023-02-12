package entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "COOP_NODES", schema = "Z02")
public class CoopNodes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NODE_ID")
    private BigInteger nodeId;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "ADDRESS_ID")
    private BigInteger addressId;

    public BigInteger getNodeId() {
        return nodeId;
    }

    public void setNodeId(BigInteger nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        CoopNodes coopNodes = (CoopNodes) o;

        if (nodeId != null ? !nodeId.equals(coopNodes.nodeId) : coopNodes.nodeId != null) return false;
        if (name != null ? !name.equals(coopNodes.name) : coopNodes.name != null) return false;
        if (addressId != null ? !addressId.equals(coopNodes.addressId) : coopNodes.addressId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nodeId != null ? nodeId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        return result;
    }
}
