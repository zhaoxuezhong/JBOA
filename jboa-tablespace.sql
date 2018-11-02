DROP TABLESPACE JBOADB INCLUDING CONTENTS AND DATAFILES;
DROP USER JBOA CASCADE;

CREATE TABLESPACE JBOADB DATAFILE 'JBOADB.dat' SIZE 5M AUTOEXTEND ON;
CREATE USER jboa IDENTIFIED BY jboa
default tablespace JBOADB;
GRANT dba TO jboa;





