drop database  if exists amnila ;
-- Création de la base de données Amnila
CREATE DATABASE amnila;

-- Utilisation de la base de données
USE amnila;


CREATE TABLE users (
  id int(5) NOT NULL,
  name varchar(50) NOT NULL,
  firstname varchar(50) NOT NULL,
  role enum('admin','proprietaire','locataire') NOT NULL,
  email varchar(100) NOT NULL,
  password varchar(255) NOT NULL
);

-- Table Propriétaire
CREATE TABLE Proprietaire (
    IDproprietaire int(5)  auto_increment PRIMARY KEY,
    NomP VARCHAR(20) NOT NULL,
    PrenomP VARCHAR(20) NOT NULL,
    Adresse_email_P VARCHAR(50) NOT NULL,
    PasswordP VARCHAR(20) NOT NULL,
    TelephoneP CHAR(10) NOT NULL,
    RIB CHAR(25) NOT NULL
);

-- Table Locataire
CREATE TABLE Locataire (
    IDlocataire  int(5)  auto_increment PRIMARY KEY,
    NomL VARCHAR(20) NOT NULL,
    PrenomL VARCHAR(20) NOT NULL,
    Adresse_email_L VARCHAR(50) NOT NULL,
    PasswordL VARCHAR(20) NOT NULL,
    TelephoneL CHAR(10) NOT NULL
);

-- Table Région
CREATE TABLE Region (
    IDregion  int(5)  auto_increment PRIMARY KEY,
    NomR VARCHAR(50) NOT NULL,
    DepartementR VARCHAR(50) NOT NULL
);

insert into Proprietaire (Idproprietaire, NomP, PrenomP, Adresse_email_P, PasswordP, TelephoneP, RIB) Values ( null, 'Doctor', 'Who', 'a@gmail.com', '123', '0769936511', '0123456789');

-- Table Station
CREATE TABLE Station (
    IDStation  int(5)  auto_increment  PRIMARY KEY,
    NomS VARCHAR(50) NOT NULL,
    IDregion int(5) NOT NULL,
    FOREIGN KEY (IDregion) REFERENCES Region(IDregion)
);

-- Table Activité
CREATE TABLE Activite (
    IDStation int(5) not null,
    IDactivite int(5) not null,
    NomA VARCHAR(50) NOT NULL,
    PrixA CHAR(10) NOT NULL,
    DescriptionA VARCHAR(50),
    PRIMARY KEY (IDStation, IDactivite),
    FOREIGN KEY (IDStation) REFERENCES Station(IDStation)
);

-- Table Administrateur
CREATE TABLE Administrateur (
    IDadministrateur  int(5)  auto_increment PRIMARY KEY,
    NomA VARCHAR(20) NOT NULL,
    PrenomA VARCHAR(20) NOT NULL,
    UsernameA VARCHAR(20) NOT NULL,
    PasswordA VARCHAR(20) NOT NULL
);


-- Table Appartement
CREATE TABLE Appartement (
    IDappartement  int(5)  auto_increment PRIMARY KEY,
    Type_d_appartementA VARCHAR(10) NOT NULL,
    RueA VARCHAR(50) NOT NULL,
    Code_PostalA CHAR(8) NOT NULL,
    VilleA VARCHAR(20) NOT NULL,
    N__immeubleA CHAR(5) NOT NULL,
    SurfaceA VARCHAR(10) NOT NULL,
    Prix_journalier DECIMAL(15,2) NOT NULL,
    IDproprietaire int(5) NOT NULL,
    IDregion int(5) NOT NULL,
    FOREIGN KEY (IDproprietaire) REFERENCES Proprietaire(IDproprietaire),
    FOREIGN KEY (IDregion) REFERENCES Region(IDregion)
);

-- Table Réservation
CREATE TABLE Reservation (
    IDreservation  int(5)  auto_increment PRIMARY KEY,
    Date_de_finR DATE NOT NULL,
    StatutR VARCHAR(20) NOT NULL,
    Date_de_debutR DATE NOT NULL,
    IDappartement int(5) NOT NULL,
    IDlocataire int(5) NOT NULL,
    FOREIGN KEY (IDappartement) REFERENCES Appartement(IDappartement),
    FOREIGN KEY (IDlocataire) REFERENCES Locataire(IDlocataire)
);

-- Table Equipement
CREATE TABLE Equipement (
    IDequipement  int(5)  auto_increment PRIMARY KEY,
    Type_d_equipement VARCHAR(20) NOT NULL,
    Detail_appart VARCHAR(50),
    IDappartement int(5) NOT NULL,
    FOREIGN KEY (IDappartement) REFERENCES Appartement(IDappartement)
);

-- Table Photo
CREATE TABLE Photo (
    IDphoto  int(5)  auto_increment PRIMARY KEY,
    Chemin VARCHAR(50) NOT NULL,
    DescriptionP VARCHAR(50),
    IDappartement int(5) NOT NULL,
    FOREIGN KEY (IDappartement) REFERENCES Appartement(IDappartement)
);

-- Table Contrat
CREATE TABLE Contrat (
    IDcontrat INT AUTO_INCREMENT PRIMARY KEY,
    Date_de_signatureC DATE NOT NULL,
    Date_de_debutC DATE NOT NULL,
    Date_de_finC DATE NOT NULL,
    Arrhes_payees VARCHAR(10) NOT NULL,
    Solde_payee VARCHAR(10) NOT NULL,
    Caution_versee VARCHAR(10) NOT NULL,
    IDappartement int(5) NOT NULL,
    IDreservation int(5) NOT NULL,
    FOREIGN KEY (IDappartement) REFERENCES Appartement(IDappartement),
    FOREIGN KEY (IDreservation) REFERENCES Reservation(IDreservation)
);
