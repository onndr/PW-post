-- TEST 1 -> test for joins
-- get full data about package by id including:
-- info about sender
-- info about receiver
-- package stats (weight, priority, size)
SELECT p.PACKAGE_ID, p.PACKAGE_SIZE, p.PACKAGE_PRIORITY,
    t.street, t.BUILDING_NUMBER, t.town, t.POSTAL_CODE,
    fr.street, fr.BUILDING_NUMBER, fr.town, fr.POSTAL_CODE,
    res.name, res.surname, res.PHONE_NUMBER,
    sen.name, sen.surname, sen.PHONE_NUMBER
FROM Packages p
    JOIN Clients sen ON (p.SENDER_ID = sen.CLIENT_ID)
    JOIN Clients res ON (p.RECEIVER_ID = res.CLIENT_ID)
    JOIN Addresses t ON (p.TO_ADDRESS_ID = t.ADDRESS_ID)
    JOIN Addresses fr ON (p.FROM_ADDRESS_ID = fr.ADDRESS_ID)
WHERE p.PACKAGE_ID = :id;

-- get full data about all packages
SELECT p.PACKAGE_ID, p.PACKAGE_SIZE, p.PACKAGE_PRIORITY,
       t.street, t.BUILDING_NUMBER, t.town, t.POSTAL_CODE,
       fr.street, fr.BUILDING_NUMBER, fr.town, fr.POSTAL_CODE,
       res.name, res.surname, res.PHONE_NUMBER,
       sen.name, sen.surname, sen.PHONE_NUMBER
FROM Packages p
         JOIN Clients sen ON (p.SENDER_ID = sen.CLIENT_ID)
         JOIN Clients res ON (p.RECEIVER_ID = res.CLIENT_ID)
         JOIN Addresses t ON (p.TO_ADDRESS_ID = t.ADDRESS_ID)
         JOIN Addresses fr ON (p.FROM_ADDRESS_ID = fr.ADDRESS_ID);

-- outer join for employees and service points and left join for service points and addresses
-- shows from which town each employee works
SELECT e.employee_id, sp.address_id, a.town, a.postal_code
FROM EMPLOYEES e
    FULL OUTER JOIN SERVICE_PLACES sp ON (e.SERVICE_PLACE_ID = sp.SP_ID)
    LEFT JOIN ADDRESSES a on sp.ADDRESS_ID = a.ADDRESS_ID;

-- TEST 2 -> test for filtering data
-- get full data about packages which priority is set to Standard
SELECT p.PACKAGE_ID, p.PACKAGE_SIZE, p.PACKAGE_PRIORITY,
       t.street, t.BUILDING_NUMBER, t.town, t.POSTAL_CODE,
       fr.street, fr.BUILDING_NUMBER, fr.town, fr.POSTAL_CODE,
       res.name, res.surname, res.PHONE_NUMBER,
       sen.name, sen.surname, sen.PHONE_NUMBER
FROM Packages p
         JOIN Clients sen ON (p.SENDER_ID = sen.CLIENT_ID)
         JOIN Clients res ON (p.RECEIVER_ID = res.CLIENT_ID)
         JOIN Addresses t ON (p.TO_ADDRESS_ID = t.ADDRESS_ID)
         JOIN Addresses fr ON (p.FROM_ADDRESS_ID = fr.ADDRESS_ID)
WHERE p.PACKAGE_PRIORITY like 'Standard';

-- TEST 3 -> test for GROUP BY
-- shows how many packages of each priority there are
SELECT p.PACKAGE_PRIORITY, count(*) count
FROM Packages p
GROUP BY p.PACKAGE_PRIORITY
ORDER BY count DESC;

-- TEST 4 -> test function package_travel_time_in_days
-- count package travel time
-- works only for those packages that have records in sp_packs_history
select package_travel_time_in_days(8) from dual;

-- TEST 5 -> test for function avg_package_travel_time_in_days
-- count average package travel time in days
select avg_package_travel_time_in_days() from dual;

-- TEST 6 -> test for procedure change_package_courier
DECLARE
    v_courier_id NUMBER;
begin
    -- this must work
    SELECT COURIER_ID INTO v_courier_id FROM PACKAGES WHERE PACKAGE_ID = 1;
    dbms_output.put_line('Courier id before the change: ' || v_courier_id);
    change_package_courier(1, 22);
    commit;
    SELECT COURIER_ID INTO v_courier_id FROM PACKAGES WHERE PACKAGE_ID = 1;
    dbms_output.put_line('Courier id after the change: ' || v_courier_id);
    -- this must not work because 43 is id of admin, not courier
    -- so there should be error (raised by trigger like in test 8)
    change_package_courier(1, 43);
end;

-- TEST 7 -> test for procedure change_employees_status
DECLARE
    v_pos_id NUMBER;
BEGIN
    SELECT POSITION_ID INTO v_pos_id FROM employees WHERE EMPLOYEE_ID = 62;
    dbms_output.put_line('Position id before the change: ' || v_pos_id);
    change_employees_position(62, 22);
    COMMIT;
    SELECT POSITION_ID INTO v_pos_id FROM employees WHERE EMPLOYEE_ID = 62;
    DBMS_OUTPUT.PUT_LINE('Position id after the change: ' || v_pos_id);
END;

-- TEST 8 -> showcase for trigger tg_set_courier
-- check if the new courier's id on package is valid
begin
    change_package_courier(1, 43);
    commit;
end;

-- TEST 9 -> showcase for trigger tg_add_login_data
-- check if the login is already not taken
begin
    insert into LOGIN_DATA (ACCOUNT_TYPE, LOGIN, PASSWORD) values (0, 'test_login', 324234324);
    -- second one causes error thanks to trigger
    insert into LOGIN_DATA (ACCOUNT_TYPE, LOGIN, PASSWORD) values (0, 'test_login', 324234324);
end;