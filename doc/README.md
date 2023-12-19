# SI4-Equipe-G - SophiaTech Eats

## Table des Matières
- [Introduction](#introduction)
- [Membres de l'Équipe et Rôles](#membres-de-léquipe-et-rôles)
- [User Stories Principales](#user-stories-principales)
- [Guide d'Installation et de Lancement](#guide-dinstallation-et-de-lancement)
- [Architecture et Structure du Projet](#architecture-et-structure-du-projet)
- [Milestones et Suivi de Progression](#milestones-et-suivi-de-progression)
- [Rapport de Projet et Documentation](#rapport-de-projet-et-documentation)

## Introduction

Ce projet vise à développer une plateforme innovante pour faciliter la commande, la livraison, et la gestion des restaurants sur un campus universitaire. L'objectif principal est de créer une solution numérique qui répond aux besoins variés des étudiants, du personnel universitaire, des restaurateurs et des livreurs. La plateforme offrira des fonctionnalités telles que la consultation de menus, la gestion de commandes individuelles ou groupées, la mise à jour des horaires et menus des restaurants, ainsi que la collecte de données statistiques pour une prise de décision éclairée.

Les principales caractéristiques du projet incluent :

- **Navigation intuitive** : Permettant à tout utilisateur Internet de parcourir facilement les menus des différents restaurants du campus.
- **Fonctionnalités avancées pour les utilisateurs enregistrés** : Comme l'accès à l'historique des commandes, la possibilité de passer des commandes et de participer à des commandes de groupe.
- **Gestion optimisée pour les restaurants** : Les responsables peuvent mettre à jour leurs offres et gérer leur capacité de production.
- **Intégration avec des services de livraison** : Simplifiant le processus de livraison et de réception des commandes.
- **Analyses statistiques** : Fournissant des insights précieux sur les volumes de commande, l'efficacité de la livraison et les préférences des utilisateurs.
- **Flexibilité et adaptabilité** : Avec la capacité d'ajouter de nouvelles fonctionnalités, comme la diversification des types de commandes (buffets, afterworks, etc.) et le signalement des usagers par les livreurs.

![Badge de statut](https://img.shields.io/badge/status-complet-brightgreen)
![Badge de version](https://img.shields.io/badge/version-1.0.0-blue)
## Membres de l'Équipe et Rôles
- **PO (Product Owner)**: Sara ZENKRI - Définition de la vision produit
- **Archi (Architecte)**: Malek FARHAT - Conception de l'architecture système
- **QA (Quality Assurance)**: Clement Chatelin - Assurance de la qualité et tests
- **Ops (Operations)**: Kaleb SIKA - Gestion de l'infrastructure et déploiements

## User Stories Principales
- **US #12**: ![100%](https://progress-bar.dev/100) En tant qu'utilisateur, je veux pouvoir me connecter afin d'accéder à mon espace personnel.
    - `Feature Branch`: [feature/login](https://github.com/SI4-Equipe-G/projet/tree/feature/login)
- **US #34**: ![85%](https://progress-bar.dev/85) En tant qu'administrateur, je veux générer des rapports pour suivre l'activité des utilisateurs.
    - `Feature Branch`: [feature/report-generation](https://github.com/SI4-Equipe-G/projet/tree/feature/report-generation)

## Guide d'Installation et de Lancement
### Prérequis
- Java 17
- Maven 3.9.5

### Étapes d'Installation
```bash
git clone https://github.com/PNS-Conception/ste-23-24-equipe-g
cd projet
mvn clean install
```

### Lancement de l'Application
```bash
java -jar target/demo-1.0-SNAPSHOT.jar
```

## Architecture et Structure du Projet
![Architecture](lien-vers-image-darchitecture.jpg)
- `src/`: Contient le code source.
- `test/`: Tests unitaires et d'intégration.
- `doc/`: Documentation technique et rapport de projet.

## Milestones et Suivi de Progression
- **Milestone 1**: ![100%](https://progress-bar.dev/100)  Infrastructure de Base et Commandes Simples
- **Milestone 2**: ![100%](https://progress-bar.dev/100) Gestion Avancée des Commandes et Intégration de Paiement
- **Milestone 3**: ![100%](https://progress-bar.dev/100) Optimisation et Gestion des Partenariats
- **Milestone 4**: ![100%](https://progress-bar.dev/100) Fonctionnalités Complémentaires et Amélioration Continue
- **Milestone 5**: ![100%](https://progress-bar.dev/99) Extensions et Scénarios Complexes

**Détail des Progressions**
- [Voir Milestones](https://github.com/PNS-Conception/ste-23-24-equipe-g/milestones?state=closed)

## Rapport de Projet et Documentation
- **Rapport Final**: [TeamSI4-Equipe-G-rendu2.pdf](https://github.com/SI4-Equipe-G/projet/doc/TeamSI4-Equipe-G-rendu2.pdf)
- **Documentation du projet**: [Voir Etude de cas](https://unice-my.sharepoint.com/personal/mireille_blay_unice_fr/_layouts/15/onedrive.aspx?id=%2Fpersonal%2Fmireille%5Fblay%5Funice%5Ffr%2FDocuments%2FENSEIGNEMENTS%5F23%5F24%2FCOURS%2FCOO%20DISTANT%2FTDs%2FPOUB%2FCaseStudy23%2D24%2Epdf&parent=%2Fpersonal%2Fmireille%5Fblay%5Funice%5Ffr%2FDocuments%2FENSEIGNEMENTS%5F23%5F24%2FCOURS%2FCOO%20DISTANT%2FTDs%2FPOUB&ga=1)
- **Extension du projet**: [Voir SophiaTech Eats: new functionalities](https://unice-my.sharepoint.com/:w:/g/personal/mireille_blay_unice_fr/EWrdQ4sG4wxDoOhWGgfyKgMBClaCekdUJuKiWhSCDGCSyg?e=fhGgVV)
