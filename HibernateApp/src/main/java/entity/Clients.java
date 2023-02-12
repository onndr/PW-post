package entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
public class Clients {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CLIENT_ID")
    private BigInteger clientId;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "SURNAME")
    private String surname;
    @Basic
    @Column(name = "EMAIL")
    private String email;
    @Basic
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Basic
    @Column(name = "ADDRESSES_ADDRESS_ID")
    private BigInteger addressesAddressId;
    @Basic
    @Column(name = "LOGIN_DATA_ID")
    private BigInteger loginDataId;
    @Basic
    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigInteger getAddressesAddressId() {
        return addressesAddressId;
    }

    public void setAddressesAddressId(BigInteger addressesAddressId) {
        this.addressesAddressId = addressesAddressId;
    }

    public BigInteger getLoginDataId() {
        return loginDataId;
    }

    public void setLoginDataId(BigInteger loginDataId) {
        this.loginDataId = loginDataId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clients clients = (Clients) o;

        if (clientId != null ? !clientId.equals(clients.clientId) : clients.clientId != null) return false;
        if (name != null ? !name.equals(clients.name) : clients.name != null) return false;
        if (surname != null ? !surname.equals(clients.surname) : clients.surname != null) return false;
        if (email != null ? !email.equals(clients.email) : clients.email != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(clients.phoneNumber) : clients.phoneNumber != null) return false;
        if (addressesAddressId != null ? !addressesAddressId.equals(clients.addressesAddressId) : clients.addressesAddressId != null)
            return false;
        if (loginDataId != null ? !loginDataId.equals(clients.loginDataId) : clients.loginDataId != null) return false;
        if (birthDate != null ? !birthDate.equals(clients.birthDate) : clients.birthDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clientId != null ? clientId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (addressesAddressId != null ? addressesAddressId.hashCode() : 0);
        result = 31 * result + (loginDataId != null ? loginDataId.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }
}
