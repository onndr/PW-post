package entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Positions {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "POSITION_ID")
    private BigInteger positionId;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "MINIMUM_SALARY")
    private Byte minimumSalary;

    public BigInteger getPositionId() {
        return positionId;
    }

    public void setPositionId(BigInteger positionId) {
        this.positionId = positionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(Byte minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Positions positions = (Positions) o;

        if (positionId != null ? !positionId.equals(positions.positionId) : positions.positionId != null) return false;
        if (name != null ? !name.equals(positions.name) : positions.name != null) return false;
        if (minimumSalary != null ? !minimumSalary.equals(positions.minimumSalary) : positions.minimumSalary != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = positionId != null ? positionId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (minimumSalary != null ? minimumSalary.hashCode() : 0);
        return result;
    }
}
