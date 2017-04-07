# Projet Assurance - M2 MIAGE FA - Thomas OSTROWSKI

### Fonctionnalités implémentées

* CU1 - Identification/Gestion des rôles et de la sécurité
* CU2 - Lister/Ajouter/Supprimer un utilisateur
* CU3 - Lister/Ajouter/Supprimer un type de contrat
* CU4 - Lister/Ajouter/Supprimer un contrat
* CU5 - Gestion des demandes d'un assuré
	* Lister les contrats qu'il a souscrit
	* Faire une demande d'arrêt de contrat
	* Lister les types de contrat
	* Faire une demande de souscription à un contrat
	* Lister ses demandes en attente
* CU6 - Gestion des demandes en attente
	* Lister les clients d'un courtier
	* Lister les contrats d'un client
	* Lister les demandes en attente
	* Créer des contrats
	* Supprimer des contrats

La quasi totalité des fonctionnalités a été développé.
L'application est pleinement fonctionnelle et répond au besoin initialiement exprimé à savoir la gestion (simplifiée) d'une compagnie d'assurance.

### Fonctionnalités non implémentées

* CU7 - Notification du traitement d'une demande

Les notifications de traitement d'une demande n'ont pas été implémentées principalement par manque de temps.

### Guide de déploiement
* Compiler l'ensemble des sources
* Ouvrir la console d'administration Glassfish (http://localhost:4848)
	* Applications > Deploy...
	* Sélection du fichier cara_project.ear
	* OK
	* Pour accéder à l'application, consulter l'URL suivante : http://localhost:8080/cara_project-war/
* Afin de créer les premiers utilisateurs pour l'application, un script SQL est mis à votre disposition dans le dossier joint, celui-ci se nomme 'script.sql', veillez à changer le nom d'utilisateur (ici APP)
