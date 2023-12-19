# SI4-Equipe-G - SophiaTech Eats
![Badge de statut](https://img.shields.io/badge/status-complet-brightgreen)
![Badge de version](https://img.shields.io/badge/version-1.0.0-blue)
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

## Membres de l'Équipe et Rôles
- **PO (Product Owner)**: Sara ZENKRI - Définition de la vision produit
- **Archi (Architecte)**: Melek FARHAT - Conception de l'architecture système
- **QA (Quality Assurance)**: Clement CHATELAIN - Assurance de la qualité et tests
- **Ops (Operations)**: Kaleb SIKA - Gestion de l'infrastructure et déploiements

## User Stories Principales
- **US #28 Buffet Order Management.**: ![95%](https://progress-bar.dev/100) En tant que membre du personnel universitaire (par exemple, une secrétaire), je veux pouvoir commander des buffets pour divers événements universitaires, afin d'assurer une organisation efficace et une expérience satisfaisante pour les participants.
  - `issue`: [Buffet Order Management](https://github.com/PNS-Conception/ste-23-24-equipe-g/issues/28)
- **US #27 Extension Signalement des Usagers.**: ![100%](https://progress-bar.dev/100) En tant que[livreur], Je veux [pouvoir noter l'utilisateur].
  - `issue`: [Extension Signalement des Usagers](https://github.com/PNS-Conception/ste-23-24-equipe-g/issues/27)
- **US #25 commandes pour les événements AfterWork.**: ![100%](https://progress-bar.dev/100) En tant qu' utilisateur souhaitant organiser un événement Afterwork,
  Je veux avoir la possibilité de créer des ordres pour ces événements,
  Afin de pouvoir organiser des événements Afterwork sans processus de paiement immédiat ni de livraison.
  - `issue`: [commandes pour les événements AfterWork](https://github.com/PNS-Conception/ste-23-24-equipe-g/issues/25)
- **US #24 Gestion de commandes multiples.**: ![100%](https://progress-bar.dev/100) En tant que [utilisateur], Je veux [pouvoir créer, ajouter, annuler et gérer plusieurs commandes en une seule fois] afin de [simplifier le processus de commande groupée]
  - `issue`: [Gestion de commandes multiples](https://github.com/PNS-Conception/ste-23-24-equipe-g/issues/24)
- **US #23 Extension de la remise.**: ![100%](https://progress-bar.dev/100) En tant que [utilisateur], Je veux [pouvoir créer, ajouter, annuler et gérer plusieurs commandes en une seule fois] afin de [simplifier le processus de commande groupée]
  - `issue`: [Extension de la remise](https://github.com/PNS-Conception/ste-23-24-equipe-g/issues/23)
- **US #29 Visualiser la liste des restaurants..**: ![100%](https://progress-bar.dev/100) En tant que [utilisateur], Je veux [pouvoir voir la liste des restaurants disponibles] afin de [choisir un restaurant pour passer une commande]
  - `issue`: [Visualiser la liste des restaurants](https://github.com/PNS-Conception/ste-23-24-equipe-g/issues/29)
- **US #19 Accès aux métriques opérationnelles.**: ![100%](https://progress-bar.dev/100) En tant qu' administrateur ou partie prenante,
  Je veux accéder aux métriques réalisées à partir des données collectées,
  Afin de pouvoir analyser ces métriques pour une prise de décision éclairée.
  - `issue`: [Accès aux métriques opérationnelles](https://github.com/PNS-Conception/ste-23-24-equipe-g/issues/19)
- **US #11 Passez une commande de groupe.**: ![100%](https://progress-bar.dev/100)
  - `Issue`: [Passez une commande de groupe](https://github.com/PNS-Conception/ste-23-24-equipe-g/issues/11)
    - ⭐ **Titre/Title**: Placer une commande de groupe.
      - ⭐ Description
      En tant que [utilisateur Alice, membre du groupe de commande], Je veux [pouvoir placer, annuler, recevoir et finaliser une commande groupée] afin de [coordonner efficacement les commandes avec d'autres membres du groupe].

      - 🌟 Priorité/Priority
      Must have/Doit avoir : la première version de ce produit nécessite absolument cette fonctionnalité - elle est essentielle au succès du produit.

      - 🌟 Estimation/Estimate M

      - ✨ Règle métier /Business rules
        - Une commande groupée est créée avec "Alice" en tant que propriétaire et "Bob" en tant que membre.
        - "Alice" peut placer une commande depuis un restaurant et payer pour elle.
        - "Bob" peut placer une commande depuis un restaurant et payer pour lui.
        - "Bob" peut placer une commande depuis un restaurant et payer pour lui.
        -  Si toutes les commandes dans le groupe sont payées, le statut de la commande groupée est "Paid", et les restaurants reçoivent la commande.
        -  Si toutes les commandes ne sont pas payées, le statut de la commande groupée n'est pas "Paid", et les restaurants ne reçoivent pas la commande.
        -  Si une commande individuelle est annulée, le statut de la commande groupée reste "Placed", le membre est retiré du groupe, et il est remboursé.
        -  Si toutes les commandes individuelles sont annulées, le statut de la commande groupée devient "Canceled", tous les membres sont remboursés, et le groupe est supprimé.
        -  Si toutes les commandes individuelles sont acceptées, le statut de la commande groupée devient "Ready", et tous les membres sont notifiés.
        -  Si la commande groupée est validée par le livreur, le statut devient "Picked up", et tous les membres sont notifiés.
        -  Si la commande groupée est livrée, le statut devient "Delivered", tous les membres sont notifiés, et le groupe est supprimé.

        - 🌟 Critères d'acceptation/Acceptance criteria
      
          - **Scénario 1: Placer une commande groupée où toutes les commandes sont payées**
           ```bash
          - _Etant donné_ qu un groupe de commande est créé avec "Alice" en tant que propriétaire et "Bob" en tant que membre...
          - _Quand_ "Alice" place une commande depuis le restaurant "Vapiano" et paye pour elle...
          - _Alors_ le statut de la commande groupée doit être "Paid"...
           ``` 
          - **Scénario: Placer une commande groupée où il y a des commandes ne sont pas payées.**
           ```bash
              _Etant donné_ qu un groupe de commande est créé avec "Alice" en tant que propriétaire et "Bob" en tant que membre
              _Quand_ "Alice" place une commande depuis le restaurant "Vapiano" et paye pour elle
              _Et_ "Bob" place une commande depuis "le déclice" mais ne paye pas
              _Alors_ le statut de la commande groupée ne doit pas être "Placed"
              _Et_ les restaurants ne doivent pas recevoir la commande.
           ``` 
          - **Scénario: Annuler toutes les commandes individuelles dans le groupe de commande groupée.**
           ```bash
              _Etant donné_ qu une commande groupée est placée
              _Quand_ tous les membres annulent leurs commandes
              _Alors_ le statut de la commande groupée doit être "Canceled"
              _Et_ tous les membres doivent être remboursés
              _Et_ le groupe doit être supprimé.
           ``` 
        - **Scénario: La commande groupée est prête après l'acceptation de toutes les commandes individuelles.**
          ```bash
              _Etant donné_ que toutes les commandes individuelles sont acceptées
              _Quand_ toutes les commandes individuelles sont prêtes
              _Alors_ le statut de la commande groupée doit être "Ready"
              _Et_ tous les membres doivent être notifiés.
          ``` 
          **Scénario: La commande groupée est récupérée après validation par le livreur.**
          ```bash
              _Etant donné_ que la commande groupée est prête
              _Quand_ le livreur valide la commande groupée
              _Alors_ le statut de la commande groupée doit être "Picked up"
              _Et_ tous les membres doivent être notifiés.
          ``` 
          - **Scénario:La commande groupée est livrée après validation par le livreur.**
          ```bash
              _Etant donné_ que la commande groupée est récupérée
              _Quand_ le livreur effectue la livraison
              _Alors_ le statut de la commande groupée doit être "Delivered"
              _Et_ tous les membres doivent être notifiés
              _Et_ le groupe doit être supprimé.
          ``` 

  

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
![Architecture](https://github.com/votre_utilisateur/votre_projet/images/architecture.jpg)
Le projet est structuré comme suit pour assurer une organisation claire et une maintenance facile :

- **`.github/`**: Contient les configurations spécifiques à GitHub, comme les templates pour les issues et les workflows d'intégration continue.
- **`.idea/`**: Répertoire de configuration pour l'environnement de développement IntelliJ IDEA.
- **`doc/`**: Documentation technique, rapports de projet et autres documents relatifs.
- **`src/`**: Le cœur du code source de l'application.
  - **`main/java/fr/unice/polytech/app/`**: Code source principal de l'application, organisé par packages fonctionnels.
    - **`Delivery/`**: Gestion des livraisons.
    - **`Orders/`**: Traitement des commandes.
    - **`Restaurant/`**: Fonctionnalités liées aux restaurants.
    - **`State/`**: Gestion des états (par exemple, état de commande).
    - **`Statistic/`**: Calcul et gestion des statistiques.
    - **`System/`**: Fonctionnalités de base du système.
    - **`User/`**: Gestion des utilisateurs.
    - **`Util/`**: Utilitaires et fonctions communes.
  - **`test/java/fr/unice/polytech/app/`**: Definitions des Steps.
- **`test/resources/features/`**: Scénarios de test basés sur des fonctionnalités.

Cette structure offre une vue claire de l'organisation du projet, facilitant à la fois le développement et la maintenance. Les dossiers sont organisés de manière logique, reflétant les différents aspects et composants du système.

## Milestones et Suivi de Progression
- **Milestone 1**: ![100%](https://progress-bar.dev/100)  Infrastructure de Base et Commandes Simples
- **Milestone 2**: ![100%](https://progress-bar.dev/100) Gestion Avancée des Commandes et Intégration de Paiement
- **Milestone 3**: ![100%](https://progress-bar.dev/100) Optimisation et Gestion des Partenariats
- **Milestone 4**: ![100%](https://progress-bar.dev/100) Fonctionnalités Complémentaires et Amélioration Continue
- **Milestone 5**: ![100%](https://progress-bar.dev/99) Extensions et Scénarios Complexes

**Détail des Progressions**
- [Voir Milestones](https://github.com/PNS-Conception/ste-23-24-equipe-g/milestones?state=closed)

## Rapport de Projet et Documentation
- **Rapport Final**: [TeamSI4-Equipe-G-rendu2.pdf](https://github.com/PNS-Conception/ste-23-24-equipe-g/blob/main/doc/TeamSI4-Equipe-G-rendu2.pdf)
- **Documentation du projet**: [Voir Etude de cas](https://unice-my.sharepoint.com/personal/mireille_blay_unice_fr/_layouts/15/onedrive.aspx?id=%2Fpersonal%2Fmireille%5Fblay%5Funice%5Ffr%2FDocuments%2FENSEIGNEMENTS%5F23%5F24%2FCOURS%2FCOO%20DISTANT%2FTDs%2FPOUB%2FCaseStudy23%2D24%2Epdf&parent=%2Fpersonal%2Fmireille%5Fblay%5Funice%5Ffr%2FDocuments%2FENSEIGNEMENTS%5F23%5F24%2FCOURS%2FCOO%20DISTANT%2FTDs%2FPOUB&ga=1)
- **Extension du projet**: [Voir SophiaTech Eats: new functionalities](https://unice-my.sharepoint.com/:w:/g/personal/mireille_blay_unice_fr/EWrdQ4sG4wxDoOhWGgfyKgMBClaCekdUJuKiWhSCDGCSyg?e=fhGgVV)

![Badge de statut](https://img.shields.io/badge/status-complet-brightgreen)
![Badge de version](https://img.shields.io/badge/version-1.0.0-blue)
