package entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "LOGIN_DATA", schema = "Z02", catalog = "")
public class LoginData {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "LOGIN_ID")
    private BigInteger loginId;
    @Basic
    @Column(name = "ACCOUNT_TYPE")
    private BigInteger accountType;
    @Basic
    @Column(name = "LOGIN")
    private String login;
    @Basic
    @Column(name = "PASSWORD")
    private String password;

    public BigInteger getLoginId() {
        return loginId;
    }

    public void setLoginId(BigInteger loginId) {
        this.loginId = loginId;
    }

    public BigInteger getAccountType() {
        return accountType;
    }

    public void setAccountType(BigInteger accountType) {
        this.accountType = accountType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginData loginData = (LoginData) o;

        if (loginId != null ? !loginId.equals(loginData.loginId) : loginData.loginId != null) return false;
        if (accountType != null ? !accountType.equals(loginData.accountType) : loginData.accountType != null)
            return false;
        if (login != null ? !login.equals(loginData.login) : loginData.login != null) return false;
        if (password != null ? !password.equals(loginData.password) : loginData.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = loginId != null ? loginId.hashCode() : 0;
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
