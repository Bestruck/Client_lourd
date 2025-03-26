-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 26 mars 2025 à 17:42
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `amnila`
--

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

CREATE TABLE `activite` (
  `idActivite` int(5) NOT NULL,
  `IDStation` int(5) NOT NULL,
  `nomActivite` varchar(50) NOT NULL,
  `prixA` decimal(15,2) NOT NULL,
  `descriptionA` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `activite`
--

INSERT INTO `activite` (`idActivite`, `IDStation`, `nomActivite`, `prixA`, `descriptionA`) VALUES
(0, 4, 'ski', 300.00, 'ski cool');

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE `administrateur` (
  `idAdministrateur` int(5) NOT NULL,
  `nomA` varchar(20) NOT NULL,
  `prenomA` varchar(20) NOT NULL,
  `usernameA` varchar(20) NOT NULL,
  `passwordA` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`idAdministrateur`, `nomA`, `prenomA`, `usernameA`, `passwordA`) VALUES
(1, 'Oswald', 'Clara', 'a@gmail.com', '123');

-- --------------------------------------------------------

--
-- Structure de la table `appartement`
--

CREATE TABLE `appartement` (
  `idAppartement` int(5) NOT NULL,
  `typeDappartementA` varchar(10) NOT NULL,
  `rueA` varchar(50) NOT NULL,
  `codePostalA` char(8) NOT NULL,
  `villeA` varchar(20) NOT NULL,
  `nImmeubleA` char(5) NOT NULL,
  `surfaceA` varchar(10) NOT NULL,
  `prixJournalier` decimal(15,2) NOT NULL,
  `idProprietaire` int(5) NOT NULL,
  `idRegion` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `appartement`
--

INSERT INTO `appartement` (`idAppartement`, `typeDappartementA`, `rueA`, `codePostalA`, `villeA`, `nImmeubleA`, `surfaceA`, `prixJournalier`, `idProprietaire`, `idRegion`) VALUES
(1, 'maison', 'berger', '92330', 'Sceaux', '3', '233', 65.00, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `contrat`
--

CREATE TABLE `contrat` (
  `idContrat` int(11) NOT NULL,
  `dateDeSignatureC` date NOT NULL,
  `dateDeDebutC` date NOT NULL,
  `dateDeFinC` date NOT NULL,
  `arrhesPayees` varchar(10) NOT NULL,
  `soldePayee` varchar(10) NOT NULL,
  `cautionVersee` varchar(10) NOT NULL,
  `idAppartement` int(5) NOT NULL,
  `idReservation` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `equipement`
--

CREATE TABLE `equipement` (
  `idEquipement` int(5) NOT NULL,
  `typeDEquipement` varchar(20) NOT NULL,
  `detailAppart` varchar(50) DEFAULT NULL,
  `idAppartement` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `equipement`
--

INSERT INTO `equipement` (`idEquipement`, `typeDEquipement`, `detailAppart`, `idAppartement`) VALUES
(1, '1', 'SKI', 1);

-- --------------------------------------------------------

--
-- Structure de la table `locataire`
--

CREATE TABLE `locataire` (
  `idLocataire` int(5) NOT NULL,
  `nomL` varchar(20) NOT NULL,
  `prenomL` varchar(20) NOT NULL,
  `adresse_Email_L` varchar(50) NOT NULL,
  `passwordL` varchar(20) NOT NULL,
  `telephoneL` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `locataire`
--

INSERT INTO `locataire` (`idLocataire`, `nomL`, `prenomL`, `adresse_Email_L`, `passwordL`, `telephoneL`) VALUES
(2, 'face', 'debeau', 'a@gmail.com', '123', '123456');

-- --------------------------------------------------------

--
-- Structure de la table `photo`
--

CREATE TABLE `photo` (
  `idPhoto` int(5) NOT NULL,
  `Chemin` varchar(50) NOT NULL,
  `descriptionP` varchar(50) DEFAULT NULL,
  `idAppartement` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `proprietaire`
--

CREATE TABLE `proprietaire` (
  `idProprietaire` int(5) NOT NULL,
  `nomP` varchar(20) NOT NULL,
  `prenomP` varchar(20) NOT NULL,
  `adresse_Email_P` varchar(50) NOT NULL,
  `passwordP` varchar(20) NOT NULL,
  `telephoneP` char(10) NOT NULL,
  `rib` char(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `proprietaire`
--

INSERT INTO `proprietaire` (`idProprietaire`, `nomP`, `prenomP`, `adresse_Email_P`, `passwordP`, `telephoneP`, `rib`) VALUES
(2, 'face', 'debeau', 'b@gmail.com', '456', '1234567890', '0789546712'),
(3, 'Jones', 'Martha', 'c@gmail.com', '789', '1234567890', '0765875434'),
(4, 'ee', 'ere', 'ttr', 'j', 'aaa', '2445'),
(5, 'smith', 'john', 'c@gmail.com', '123', '1234', '0769936511');

-- --------------------------------------------------------

--
-- Structure de la table `region`
--

CREATE TABLE `region` (
  `idRegion` int(5) NOT NULL,
  `nomR` varchar(50) NOT NULL,
  `departementR` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `region`
--

INSERT INTO `region` (`idRegion`, `nomR`, `departementR`) VALUES
(1, 'Quyeras', '13'),
(3, 'paris', '75');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `idReservation` int(5) NOT NULL,
  `dateDeFinR` date NOT NULL,
  `statutR` varchar(20) NOT NULL,
  `dateDeDebutR` date NOT NULL,
  `idAppartement` int(5) NOT NULL,
  `idLocataire` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `station`
--

CREATE TABLE `station` (
  `idStation` int(5) NOT NULL,
  `nomS` varchar(50) NOT NULL,
  `idRegion` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `station`
--

INSERT INTO `station` (`idStation`, `nomS`, `idRegion`) VALUES
(4, 'Rose Quartz', 1);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(5) NOT NULL,
  `name` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `role` enum('admin','proprietaire','locataire') NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `activite`
--
ALTER TABLE `activite`
  ADD PRIMARY KEY (`idActivite`),
  ADD KEY `IDStation` (`IDStation`);

--
-- Index pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`idAdministrateur`);

--
-- Index pour la table `appartement`
--
ALTER TABLE `appartement`
  ADD PRIMARY KEY (`idAppartement`),
  ADD KEY `IDproprietaire` (`idProprietaire`),
  ADD KEY `IDregion` (`idRegion`);

--
-- Index pour la table `contrat`
--
ALTER TABLE `contrat`
  ADD PRIMARY KEY (`idContrat`),
  ADD KEY `IDappartement` (`idAppartement`),
  ADD KEY `IDreservation` (`idReservation`);

--
-- Index pour la table `equipement`
--
ALTER TABLE `equipement`
  ADD PRIMARY KEY (`idEquipement`),
  ADD KEY `IDappartement` (`idAppartement`);

--
-- Index pour la table `locataire`
--
ALTER TABLE `locataire`
  ADD PRIMARY KEY (`idLocataire`);

--
-- Index pour la table `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`idPhoto`),
  ADD KEY `IDappartement` (`idAppartement`);

--
-- Index pour la table `proprietaire`
--
ALTER TABLE `proprietaire`
  ADD PRIMARY KEY (`idProprietaire`);

--
-- Index pour la table `region`
--
ALTER TABLE `region`
  ADD PRIMARY KEY (`idRegion`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`idReservation`),
  ADD KEY `IDappartement` (`idAppartement`),
  ADD KEY `IDlocataire` (`idLocataire`);

--
-- Index pour la table `station`
--
ALTER TABLE `station`
  ADD PRIMARY KEY (`idStation`),
  ADD KEY `IDregion` (`idRegion`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `administrateur`
--
ALTER TABLE `administrateur`
  MODIFY `idAdministrateur` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `appartement`
--
ALTER TABLE `appartement`
  MODIFY `idAppartement` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `contrat`
--
ALTER TABLE `contrat`
  MODIFY `idContrat` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `equipement`
--
ALTER TABLE `equipement`
  MODIFY `idEquipement` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `locataire`
--
ALTER TABLE `locataire`
  MODIFY `idLocataire` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `photo`
--
ALTER TABLE `photo`
  MODIFY `idPhoto` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `proprietaire`
--
ALTER TABLE `proprietaire`
  MODIFY `idProprietaire` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `region`
--
ALTER TABLE `region`
  MODIFY `idRegion` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `idReservation` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `station`
--
ALTER TABLE `station`
  MODIFY `idStation` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `activite`
--
ALTER TABLE `activite`
  ADD CONSTRAINT `activite_ibfk_1` FOREIGN KEY (`IDStation`) REFERENCES `station` (`IDStation`) ON DELETE CASCADE;

--
-- Contraintes pour la table `appartement`
--
ALTER TABLE `appartement`
  ADD CONSTRAINT `appartement_ibfk_1` FOREIGN KEY (`IDproprietaire`) REFERENCES `proprietaire` (`IDproprietaire`),
  ADD CONSTRAINT `appartement_ibfk_2` FOREIGN KEY (`IDregion`) REFERENCES `region` (`IDregion`);

--
-- Contraintes pour la table `contrat`
--
ALTER TABLE `contrat`
  ADD CONSTRAINT `contrat_ibfk_1` FOREIGN KEY (`IDappartement`) REFERENCES `appartement` (`IDappartement`),
  ADD CONSTRAINT `contrat_ibfk_2` FOREIGN KEY (`IDreservation`) REFERENCES `reservation` (`IDreservation`);

--
-- Contraintes pour la table `equipement`
--
ALTER TABLE `equipement`
  ADD CONSTRAINT `equipement_ibfk_1` FOREIGN KEY (`IDappartement`) REFERENCES `appartement` (`IDappartement`);

--
-- Contraintes pour la table `photo`
--
ALTER TABLE `photo`
  ADD CONSTRAINT `photo_ibfk_1` FOREIGN KEY (`IDappartement`) REFERENCES `appartement` (`IDappartement`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`IDappartement`) REFERENCES `appartement` (`IDappartement`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`IDlocataire`) REFERENCES `locataire` (`IDlocataire`);

--
-- Contraintes pour la table `station`
--
ALTER TABLE `station`
  ADD CONSTRAINT `station_ibfk_1` FOREIGN KEY (`IDregion`) REFERENCES `region` (`IDregion`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
