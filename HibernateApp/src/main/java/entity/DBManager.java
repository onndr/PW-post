package entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import org.springframework.security.crypto.password.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.*;

public class DBManager {
    public Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("PW is my favorite uni", 13, 65536, 128);
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;
    public DBManager (){
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }
    public BigInteger addClient(String firstName, String lastName, String email, String phoneNumber, BigInteger addressId, LocalDate birthDate, BigInteger loginId){
        try {
            transaction.begin();
            Clients client = new Clients();
            client.setName(firstName);
            client.setSurname(lastName);
            client.setEmail(email);
            client.setPhoneNumber(phoneNumber);
            client.setAddressesAddressId(addressId);
            client.setBirthDate(birthDate);
            client.setLoginDataId(loginId);
            entityManager.persist(client);
            transaction.commit();
            return client.getClientId();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
    public BigInteger addAddress(String town, String street, String buildingNumber, String postalCode, BigInteger countryId){
        try {
            transaction.begin();
            Addresses address = new Addresses();
            address.setTown(town);
            address.setStreet(street);
            address.setBuildingNumber(buildingNumber);
            address.setPostalCode(postalCode);
            address.setCountryId(countryId);
            entityManager.persist(address);
            transaction.commit();
            return address.getAddressId();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
    public BigInteger addPackage(
            BigInteger senderId, BigInteger receiverId,
            BigInteger senderAddressId, BigInteger receiverAddressId,
            String packageSize, String priority, String weight){
        try {
            transaction.begin();
            Packages parcel = new Packages();
            parcel.setPackagePriority(priority);
            parcel.setToAddressId(receiverAddressId);
            parcel.setFromAddressId(senderAddressId);
            parcel.setReceiverId(receiverId);
            parcel.setSenderId(senderId);
            parcel.setPackageSize(packageSize);
            parcel.setPackageWeight(weight);
            entityManager.persist(parcel);
            transaction.commit();
            addSPPacksHistory(parcel.getPackageId(), "Waiting for courier.");
            return parcel.getPackageId();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
    public Packages getPackageById(BigInteger id){
        String s = "SELECT p FROM Packages p WHERE p.packageId = :id";
        TypedQuery<Packages> packageById = entityManager.createQuery(s, Packages.class);
        packageById.setParameter("id", id);
        return packageById.getSingleResult();
    }
    public List<Packages> getAllPackages(){
        String s = "SELECT p FROM Packages p";
        TypedQuery<Packages> allPackages = entityManager.createQuery(s, Packages.class);
        return allPackages.getResultList();
    }
    public List getFullPackageInfo(BigInteger id){
        String s = "SELECT p.packageId, p.packageSize, p.packagePriority, " +
                "to.street, to.buildingNumber, to.town, to.postalCode, " +
                "fr.street, fr.buildingNumber, fr.town, fr.postalCode," +
                "res.name, res.surname, res.phoneNumber, " +
                "sen.name, sen.surname, sen.phoneNumber, " +
                "sp.description, sp.statusDatetime " +
                "FROM Packages p " +
                "JOIN Clients sen ON (p.senderId = sen.clientId) " +
                "JOIN Clients res ON (p.receiverId = res.clientId) " +
                "JOIN Addresses to ON (p.toAddressId = to.addressId) " +
                "JOIN Addresses fr ON (p.fromAddressId = fr.addressId) " +
                "LEFT JOIN SpPacksHistory sp ON (p.packageId = sp.packageId) " +
                "WHERE p.packageId = :id " +
                "ORDER BY sp.statusId DESC " +
                "FETCH FIRST 1 ROW ONLY";
        Query q = entityManager.createQuery(s);
        q.setParameter("id", id);
        return q.getResultList();
    }
    public List getAllPackagesInfo(){
        String s = "" +
                "SELECT p.id, cl.name, cl.surname, cl2.name, cl2.surname " +
                "FROM Packages p " +
                "JOIN Clients cl ON (p.senderId=cl.clientId) " +
                "JOIN Clients cl2 ON (p.receiverId=cl2.clientId)";
        Query q = entityManager.createQuery(s);
        return q.getResultList();
    }
    public List getAllPackagesInfoForClient(BigInteger clientId){
        String s = "" +
                "SELECT p.id, cl2.name, cl2.surname " +
                "FROM Packages p " +
                "JOIN Clients cl ON (p.senderId=cl.clientId) " +
                "JOIN Clients cl2 ON (p.receiverId=cl2.clientId)" +
                "WHERE cl.clientId = :clientId";
        Query q = entityManager.createQuery(s);
        q.setParameter("clientId", clientId);
        return q.getResultList();
    }
    public List getAllPackagesInfoForCourier(BigInteger employeeId){
        String s = "" +
                "SELECT p.id, cl.name, cl.surname, cl2.name, cl2.surname " +
                "FROM Packages p " +
                "JOIN Clients cl ON (p.senderId=cl.clientId) " +
                "JOIN Clients cl2 ON (p.receiverId=cl2.clientId)" +
                "JOIN Employees e ON (p.courierId=e.employeeId)" +
                "WHERE e.employeeId = :employeeId";
        Query q = entityManager.createQuery(s);
        q.setParameter("employeeId", employeeId);
        return q.getResultList();
    }

    public BigInteger addLoginData(String login, String password, int accType){
        try {
            transaction.begin();
            LoginData ld = new LoginData();
            ld.setLogin(login);
            ld.setPassword(Integer.toString(password.hashCode()));
            ld.setAccountType(BigInteger.valueOf(accType));
            entityManager.persist(ld);
            transaction.commit();
            return ld.getLoginId();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public BigInteger addSPPacksHistory(BigInteger packageId, String description){
        try {
            transaction.begin();
            SpPacksHistory spph = new SpPacksHistory();
            spph.setPackageId(packageId);
            spph.setDescription(description);
            spph.setStatusDatetime(LocalDate.now());
            entityManager.persist(spph);
            transaction.commit();
            return spph.getStatusId();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public BigInteger addPackageToCourier(Packages a_package, BigInteger courierId){
        try {
            transaction.begin();
            a_package.setCourierId(courierId);
            entityManager.persist(a_package);
            transaction.commit();
            return a_package.getPackageId();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public static void main(String[] args) {
        DBManager db = new DBManager();
    }

    public BigInteger getCountryIdByName(String countryName) {
        String s = "SELECT distinct c FROM Countries c WHERE name = :countryName";
        TypedQuery<Countries> countryByName = entityManager.createQuery(s, Countries.class);
        countryByName.setParameter("countryName", countryName);
        return countryByName.getSingleResult().getCountryId();
    }

    public Clients getClient(BigInteger clientId) {
        String s = "SELECT distinct cl FROM Clients cl WHERE cl.clientId = :id";
        TypedQuery<Clients> clientById = entityManager.createQuery(s, Clients.class);
        clientById.setParameter("id", clientId);
        return clientById.getSingleResult();
    }

    public String getPasswordByLogin(String login) {
        String s = "SELECT distinct l FROM LoginData l WHERE login = :login";
        TypedQuery<LoginData> passwordByLogin = entityManager.createQuery(s, LoginData.class);
        passwordByLogin.setParameter("login", login);
        return passwordByLogin.getSingleResult().getPassword();
    }

    public BigInteger getAccountTypeByLogin(String login) {
        String s = "SELECT distinct l FROM LoginData l WHERE login = :login";
        TypedQuery<LoginData> accTypeByLogin = entityManager.createQuery(s, LoginData.class);
        accTypeByLogin.setParameter("login", login);
        return accTypeByLogin.getSingleResult().getAccountType();
    }

    public Clients getClientByLogin(String login) {
        String s = "SELECT distinct cl " +
                "FROM Clients cl join LoginData ld on (cl.loginDataId = ld.loginId) " +
                "WHERE ld.login = :login";
        TypedQuery<Clients> clientByLogin = entityManager.createQuery(s, Clients.class);
        clientByLogin.setParameter("login", login);
        return clientByLogin.getSingleResult();
    }

    public Employees getEmployeeByLogin(String login) {
        String s = "SELECT distinct em " +
                "FROM Employees em join LoginData ld on (em.loginDataId = ld.loginId) " +
                "WHERE ld.login = :login";
        TypedQuery<Employees> employeeByLogin = entityManager.createQuery(s, Employees.class);
        employeeByLogin.setParameter("login", login);
        return employeeByLogin.getSingleResult();
    }

    public List getAllCouriersInfo(){
        String s = "" +
                "SELECT e.employeeId, e.name, e.surname " +
                "FROM Employees e Join Positions p on (e.positionId = p.positionId)" +
                "WHERE p.name = 'Courier'";
        Query q = entityManager.createQuery(s);
        return q.getResultList();
    }
}
