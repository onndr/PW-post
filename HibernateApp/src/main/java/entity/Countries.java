package entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Countries {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COUNTRY_ID")
    private BigInteger countryId;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "PHONE_NUMBER_PREFIX")
    private String phoneNumberPrefix;

    public BigInteger getCountryId() {
        return countryId;
    }

    public void setCountryId(BigInteger countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumberPrefix() {
        return phoneNumberPrefix;
    }

    public void setPhoneNumberPrefix(String phoneNumberPrefix) {
        this.phoneNumberPrefix = phoneNumberPrefix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Countries countries = (Countries) o;

        if (countryId != null ? !countryId.equals(countries.countryId) : countries.countryId != null) return false;
        if (name != null ? !name.equals(countries.name) : countries.name != null) return false;
        if (phoneNumberPrefix != null ? !phoneNumberPrefix.equals(countries.phoneNumberPrefix) : countries.phoneNumberPrefix != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = countryId != null ? countryId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phoneNumberPrefix != null ? phoneNumberPrefix.hashCode() : 0);
        return result;
    }
}
