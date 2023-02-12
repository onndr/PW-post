create table Z02.COUNTRIES
(
    COUNTRY_ID          NUMBER generated as identity
        constraint COUNTRIES_PK
            primary key,
    NAME                VARCHAR2(30 char) not null,
    PHONE_NUMBER_PREFIX VARCHAR2(5 char)
)
/

create table Z02.ADDRESSES
(
    ADDRESS_ID        NUMBER generated as identity
        constraint ADDRESSES_PK
            primary key,
    TOWN              VARCHAR2(40 char) not null,
    STREET            VARCHAR2(40 char) not null,
    BUILDING_NUMBER   VARCHAR2(5 char)  not null,
    APPARTMENT_NUMBER NUMBER,
    POSTAL_CODE       VARCHAR2(6 char)  not null,
    COUNTRY_ID        NUMBER            not null
        constraint ADDRESSES_COUNTRIES_FK
            references Z02.COUNTRIES
)
/

create table Z02.COOP_NODES
(
    NODE_ID    NUMBER generated as identity
        constraint COOPERATIVE_NODE_PK
            unique,
    NAME       VARCHAR2(30 char),
    ADDRESS_ID NUMBER not null
        constraint COOP_NODES_ADDRESSES_FK
            references Z02.ADDRESSES
)
/

create table Z02.LOGIN_DATA
(
    LOGIN_ID     NUMBER generated as identity
        constraint LOGIN_DATA_PK
            primary key,
    ACCOUNT_TYPE NUMBER             not null
        constraint VALID_ACCOUNT_TYPE
            check (ACCOUNT_TYPE IN (0, 1, 2)),
    LOGIN        VARCHAR2(40 char)  not null,
    PASSWORD     VARCHAR2(200 char) not null
)
/

create table Z02.CLIENTS
(
    CLIENT_ID            NUMBER generated as identity
        constraint CLIENTS_PK
            primary key,
    NAME                 VARCHAR2(40 char)  not null,
    SURNAME              VARCHAR2(40 char)  not null,
    EMAIL                VARCHAR2(100 char) not null,
    PHONE_NUMBER         VARCHAR2(9 char)   not null,
    ADDRESSES_ADDRESS_ID NUMBER             not null
        constraint CLIENTS_ADDRESSES_FK
            references Z02.ADDRESSES,
    LOGIN_DATA_ID        NUMBER
        constraint CLIENTS_LOGIN_DATA_FK
            references Z02.LOGIN_DATA,
    BIRTH_DATE           DATE
)
/

create table Z02.POSITIONS
(
    POSITION_ID    NUMBER generated as identity
        constraint POSITIONS_PK
            primary key,
    NAME           VARCHAR2(40 char) not null,
    MINIMUM_SALARY NUMBER(10, 2)
)
/

create table Z02.SERVICE_PLACES
(
    SP_ID      NUMBER generated as identity
        constraint SERVICE_PLACES_PK
            primary key,
    ADDRESS_ID NUMBER not null
        constraint SP_ADDRESSES_FK
            references Z02.ADDRESSES
)
/

create table Z02.EMPLOYEES
(
    EMPLOYEE_ID      NUMBER generated as identity
        constraint EMPLOYEES_PK
            primary key,
    NAME             VARCHAR2(40 char) not null,
    SURNAME          VARCHAR2(40 char) not null,
    SALARY           NUMBER(10, 2),
    POSITION_ID      NUMBER            not null
        constraint EMPLOYEES_POSITIONS_FK
            references Z02.POSITIONS,
    SERVICE_PLACE_ID NUMBER
        constraint EMPLOYEES_SERVICE_PLACES_FK
            references Z02.SERVICE_PLACES,
    LOGIN_DATA_ID    NUMBER            not null
        constraint EMPLOYEES_LOGIN_DATA_FK
            references Z02.LOGIN_DATA,
    NODE_ID          NUMBER
        constraint EMPLOYEES_NODES_FK
            references Z02.COOP_NODES (NODE_ID)
)
/

create table Z02.EMP_POS_HISTORY
(
    EMPLOYEE_ID NUMBER not null
        constraint EMPLOYEE_EMPLOYEES_FK
            references Z02.EMPLOYEES,
    POSITION_ID NUMBER not null
        constraint EMPLOYEE_POSITIONS_FK
            references Z02.POSITIONS,
    "From"      DATE   not null,
    "To"        DATE,
    ID          NUMBER generated as identity,
    constraint EMPLOYEE_POSITIONS_HISTORY_PK
        primary key (EMPLOYEE_ID, POSITION_ID)
)
/

create table Z02.PACKAGES
(
    PACKAGE_ID       NUMBER generated as identity
        constraint PACKAGES_PK
            primary key,
    PACKAGE_SIZE     VARCHAR2(20 char) not null,
    PACKAGE_PRIORITY VARCHAR2(20 char) not null,
    PACKAGE_WEIGHT   VARCHAR2(20 char),
    TO_ADDRESS_ID    NUMBER            not null
        constraint PACKAGES_ADDRESSES_FKV1
            references Z02.ADDRESSES,
    FROM_ADDRESS_ID  NUMBER            not null
        constraint PACKAGES_ADDRESSES_FK
            references Z02.ADDRESSES,
    SERVICE_PLACE_ID NUMBER
        constraint PACKAGES_SERVICE_PLACES_FK
            references Z02.SERVICE_PLACES,
    RECEIVER_ID      NUMBER
        constraint PACKAGES_CLIENTS_FKV2
            references Z02.CLIENTS,
    SENDER_ID        NUMBER            not null
        constraint PACKAGES_CLIENTS_FK
            references Z02.CLIENTS,
    COURIER_ID       NUMBER
        constraint PACKAGES_COURIERS_FK
            references Z02.EMPLOYEES
)
/

create table Z02.NODES_PACKS_HISTORY
(
    PACKAGE_ID   NUMBER not null
        constraint NODES_PACKS_PACKAGES_FK
            references Z02.PACKAGES,
    COOP_NODE_ID NUMBER not null
        constraint NODES_PACKS_FK
            references Z02.COOP_NODES (NODE_ID),
    IN_DATETIME  DATE   not null,
    OUT_DATETIME DATE   not null,
    ENTRY_ID     NUMBER generated as identity
        constraint NODES_PACKAGES_HISTORY_PK
            primary key,
    DESCRIPTION  VARCHAR2(100 char)
)
/

create table Z02.SP_PACKS_HISTORY
(
    PACKAGE_ID       NUMBER not null
        constraint STATUSES_PACKS_FK
            references Z02.PACKAGES,
    SERVICE_PLACE_ID NUMBER not null
        constraint STATUSES_SP_FK
            references Z02.SERVICE_PLACES,
    STATUS_DATETIME  DATE   not null,
    STATUS_ID        NUMBER generated as identity
        constraint STATUSES_HISTORY_PK
            primary key,
    DESCRIPTION      VARCHAR2(400 char)
)
/

