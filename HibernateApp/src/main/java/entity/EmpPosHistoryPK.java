package entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.math.BigInteger;

public class EmpPosHistoryPK implements Serializable {
    @Column(name = "EMPLOYEE_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger employeeId;
    @Column(name = "POSITION_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger positionId;

    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    public BigInteger getPositionId() {
        return positionId;
    }

    public void setPositionId(BigInteger positionId) {
        this.positionId = positionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpPosHistoryPK that = (EmpPosHistoryPK) o;

        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (positionId != null ? !positionId.equals(that.positionId) : that.positionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (positionId != null ? positionId.hashCode() : 0);
        return result;
    }
}
