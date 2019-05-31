/*============================================================================*/
/* DBMS: PostgreSQL 10*/
/* Created on : 2019. 05. 29. 14:58:05                                           */
/*============================================================================*/


/*============================================================================*/
/*                                  TABLES                                    */
/*============================================================================*/
CREATE TABLE LOCATION (
  ID                          VARCHAR(40) NOT NULL,
  ADDRESS_PRIMARY             VARCHAR(40) NOT NULL,
  ADDRESS_SECONDARY           VARCHAR(40),
  COUNTRY                     VARCHAR(40) NOT NULL,
  TOWN                        VARCHAR(40) NOT NULL,
  POSTAL_CODE                 VARCHAR(40),
  MANAGER_NAME                VARCHAR(40),
  PHONE                       VARCHAR(40),
CONSTRAINT PK_LOCATION PRIMARY KEY (ID)
)
;

CREATE TABLE MARKETPLACE (
  ID                       INTEGER NOT NULL,
  MARKETPLACE_NAME         VARCHAR(40),
CONSTRAINT PK_MARKETPLACE PRIMARY KEY (ID)
)
;

CREATE TABLE LISTINGSTATUS (
  ID                       INTEGER NOT NULL,
  STATUS_NAME              VARCHAR(40),
CONSTRAINT PK_LISTINGSTATUS PRIMARY KEY (ID)
)
;

CREATE TABLE LISTING (
  ID                       VARCHAR(40) NOT NULL,
  TITLE                    TEXT NOT NULL,
  DESCRIPTION              TEXT NOT NULL,
  LISTING_PRICE            DECIMAL(10,2) NOT NULL,
  CURRENCY                 VARCHAR(3) NOT NULL,
  QUANTITY                 INTEGER NOT NULL,
  UPLOAD_TIME              DATE NOT NULL,
  OWNER_EMAIL_ADDRESS      VARCHAR(40) NOT NULL,
  LISTING_STATUS           INTEGER NOT NULL,
  MARKETPLACE              INTEGER NOT NULL,
  LOCATION_ID              VARCHAR(40) NOT NULL,
CONSTRAINT PK_LISTING PRIMARY KEY (ID)
)
;

/*============================================================================*/
/*                               FOREIGN KEYS                                 */
/*============================================================================*/
ALTER TABLE LISTING
    ADD CONSTRAINT FK_REFERENCE_2
        FOREIGN KEY (MARKETPLACE)
            REFERENCES MARKETPLACE (ID)
 ;

ALTER TABLE LISTING
    ADD CONSTRAINT FK_REFERENCE_3
        FOREIGN KEY (LOCATION_ID)
            REFERENCES LOCATION (ID)
 ;

ALTER TABLE LISTING
    ADD CONSTRAINT FK_REFERENCE_1
        FOREIGN KEY (LISTING_STATUS)
            REFERENCES LISTINGSTATUS (ID)
 ;


