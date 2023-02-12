package entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Employees {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "EMPLOYEE_ID")
    private BigInteger employeeId;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "SURNAME")
    private String surname;
    @Basic
    @Column(name = "SALARY")
    private Byte salary;
    @Basic
    @Column(name = "POSITION_ID")
    private BigInteger positionId;
    @Basic
    @Column(name = "SERVICE_PLACE_ID")
    private BigInteger servicePlaceId;
    @Basic
    @Column(name = "LOGIN_DATA_ID")
    private BigInteger loginDataId;
    @Basic
    @Column(name = "NODE_ID")
    private BigInteger nodeId;

    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Byte getSalary() {
        return salary;
    }

    public void setSalary(Byte salary) {
        this.salary = salary;
    }

    public BigInteger getPositionId() {
        return positionId;
    }

    public void setPositionId(BigInteger positionId) {
        this.positionId = positionId;
    }

    public BigInteger getServicePlaceId() {
        return servicePlaceId;
    }

    public void setServicePlaceId(BigInteger servicePlaceId) {
        this.servicePlaceId = servicePlaceId;
    }

    public BigInteger getLoginDataId() {
        return loginDataId;
    }

    public void setLoginDataId(BigInteger loginDataId) {
        this.loginDataId = loginDataId;
    }

    public BigInteger getNodeId() {
        return nodeId;
    }

    public void setNodeId(BigInteger nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employees employees = (Employees) o;

        if (employeeId != null ? !employeeId.equals(employees.employeeId) : employees.employeeId != null) return false;
        if (name != null ? !name.equals(employees.name) : employees.name != null) return false;
        if (surname != null ? !surname.equals(employees.surname) : employees.surname != null) return false;
        if (salary != null ? !salary.equals(employees.salary) : employees.salary != null) return false;
        if (positionId != null ? !positionId.equals(employees.positionId) : employees.positionId != null) return false;
        if (servicePlaceId != null ? !servicePlaceId.equals(employees.servicePlaceId) : employees.servicePlaceId != null)
            return false;
        if (loginDataId != null ? !loginDataId.equals(employees.loginDataId) : employees.loginDataId != null)
            return false;
        if (nodeId != null ? !nodeId.equals(employees.nodeId) : employees.nodeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (positionId != null ? positionId.hashCode() : 0);
        result = 31 * result + (servicePlaceId != null ? servicePlaceId.hashCode() : 0);
        result = 31 * result + (loginDataId != null ? loginDataId.hashCode() : 0);
        result = 31 * result + (nodeId != null ? nodeId.hashCode() : 0);
        return result;
    }
}
