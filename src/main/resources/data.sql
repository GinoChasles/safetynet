INSERT INTO person (first_name, last_name, address, city, zip, phone, email)
VALUES ('John', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'jaboyd@.com'),
       ('Jacob', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6513', 'drk@.com'),
       ('Tenley', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'tenz@.com'),
       ('Roger', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'jaboyd@.com'),
       ('Felicia', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6544', 'jaboyd@.com'),
       ('Jonanathan', 'Marrack', '29 15th St', 'Culver', '97451', '841-874-6513', 'drk@.com'),
       ('Tessa', 'Carman', '834 Binoc Ave', 'Culver', '97451', '841-874-6512', 'tenz@.com'),
       ('Peter', 'Duncan', '644 Gershwin Cir', 'Culver', '97451', '841-874-6512', 'jaboyd@.com'),
       ('Foster', 'Shepard', '748 Townings Dr', 'Culver', '97451', '841-874-6544', 'jaboyd@.com'),
       ('Tony', 'Cooper', '112 Steppes Pl', 'Culver', '97451', '841-874-6874', 'tcoop@ymail.com'),
       ('Lily', 'Cooper', '489 Manchester St', 'Culver', '97451', '841-874-9845', 'lily@.com'),
       ('Sophia', 'Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7878', 'soph@.com'),
       ('Warren', 'Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7512', 'ward@.com'),
       ('Zach', 'Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7512', 'zarc@.com'),
       ('Reginold', 'Walker', '908 73rd St', 'Culver', '97451', '841-874-8547', 'reg@.com'),
       ('Jamie', 'Peters', '908 73rd St', 'Culver', '97451', '841-874-7462', 'jpeter@.com'),
       ('Ron', 'Peters', '112 Steppes Pl', 'Culver', '97451', '841-874-8888', 'jpeter@.com'),
       ('Allison', 'Boyd', '112 Steppes Pl', 'Culver', '97451', '841-874-9888', 'aly@imail.com'),
       ('Brian', 'Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'bstel@.com'),
       ('Shawna', 'Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'ssanw@.com '),
       ('Kendrik', 'Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'bstel@.com '),
       ('Clive', 'Ferguson', '748 Townings Dr', 'Culver', '97451', '841-874-6741', 'clivfd@ymail.com'),
       ('Eric', 'Cadigan', '951 LoneTree Rd', 'Culver', '97451', '841-874-7458', 'gramps@.com');

INSERT INTO person (id, first_name, last_name, address, city, zip, phone, email)
VALUES (1,'John', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'jaboyd@.com');
insert into fire_station values (1, '1509 Culver St', 3, 1);

INSERT INTO firestation(address, station)
VALUES ('1509 Culver St', 3),
       ('29 15th St', 2),
       ('834 Binoc Ave', 3),
       ('644 Gershwin Cir', 1),
       ('748 Townings Dr', 3),
       ('112 Steppes Pl', 3),
       ('489 Manchester St', 4),
       ('892 Downing Ct', 2),
       ('908 73rd St', 1),
       ('112 Steppes Pl', 4),
       ('947 E. Rose Dr', 1),
       ('748 Townings Dr', 3),
       ('951 LoneTree Rd', 2);

INSERT INTO medicalrecord()
VALUES ('John', 'Boyd', '
03/06/1984', ['aznol:350mg', 'hydrapermazol:100mg'], ['nillacilan']),
       ('Jacob', 'Boyd', '03/06/1989', ['pharmacol:5000mg', 'terazine:10mg', 'noznazol:250mg'], []),
       ('Tenley', 'Boyd', '02/18/2012', [], ['peanut']),
       ('Roger', 'Boyd', '
09/06/2017', [], []),
       ('Felicia', 'Boyd', '01/08/1986', ['tetracyclaz:650mg'], ['xilliathal']),
       ('Jonanathan', 'Marrack', '01/03/1989', [], []),
       ('Tessa', 'Carman', '
02/18/2012', [], []),
       ('Peter', 'Duncan', '09/06/2000', [], ['shellfish']),
       ('Foster', 'Shepard', '
01/08/1980', [], []),
       ('Tony', 'Cooper', '03/06/1994', ['hydrapermazol:300mg', 'dodoxadin:30mg]', ['shellfish']),
       ('Lily', 'Cooper', '
03/06/1994', [], []),
       ('Sophia', 'Zemicks', '03/06/1988',
           ['aznol:60mg', 'hydrapermazol:900mg', 'pharmacol:5000mg', 'terazine:500mg'], ['peanut', 'shellfish',
        'aznol']),
       ('Warren', 'Zemicks', '
03/06/1985', [], []),
       ('Zach', 'Zemicks', '03/06/2017', [], []),
       ('Reginold', 'Walker', '
08/30/1979', ['thradox:700mg'], ['illisoxian']),
       ('Jamie', 'Peters', '
03/06/1982', [], []),
       ('Ron', 'Peters', '04/06/1965', [], []),
       ('Allison', 'Boyd', '
03/15/1965', ['aznol:200mg'], ['nillacilan']),
       ('Brian', 'Stelzer', '
12/06/1975', ['ibupurin:200mg', 'hydrapermazol:400mg'], ['nillacilan']),
       ('Shawna', 'Stelzer', '07/08/1980', [], []),
       ('Kendrik', 'Stelzer', '
03/06/2014', ['noxidian:100mg', 'pharmacol:2500mg'], []),
       ('Clive', 'Ferguson', '03/06/1994', [], []),
       ('Eric', 'Cadigan', '
08/06/1945', ['tradoxidine:400mg'], []);
        
