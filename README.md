
Riad Dar El Fassi – Application Mobile de Gestion des Commandes
Développé par : Malak Samouh – L3 ISEI UP8

<p align="center">
<img src="Logo de Mode Circulaire Classique en Beige.png" width="200">
</p>


🕌 
1. Présentation du projet:
L’application Riad Dar El Fassi est une solution mobile destinée à améliorer l’expérience des clients au sein du riad en digitalisant la prise de commandes.

Elle permet :

D’attribuer automatiquement un numéro de réservation unique à chaque client.

De parcourir un menu visuel contenant les plats typiques du riad.

De créer un panier interactif avec gestion des quantités (+ / –).

De finaliser une commande avec un paiement sur place.

D’enregistrer chaque commande dans une base de données Room.

De consulter l’historique complet des commandes.

De supprimer l’historique si nécessaire.



Cette application rend le service plus rapide, plus fluide et évite les erreurs humaines lors de la prise de commandes.

🧱 
2. Architecture Technique


L’application suit une architecture simple et efficace :

MainActivity → MenuActivity → CartActivity → PaymentActivity → HistoryActivity

CartManager (Singleton)
Gère le panier global.

Stocke les plats sélectionnés.

Conserve le numéro de réservation actif.

Room Database contient 3 composants :

OrderItemEntity → modèle stocké.

OrderDao → requêtes SQL.

AppDatabase → instance unique de la BDD.


Chaque plat est enregistré avec :

Nom ,Prix,Quantité,Image,Date,Numéro de réservation

3. Fonctionnalités Détaillées:
3.1. Numéro de réservation automatique

Lors du clic sur Réserver une table, l’application :
Lit le dernier numéro utilisé dans la base (ex: 12) puis génère le suivant (ex: 13).
L’attribue automatiquement au client.

➡️ Évite les collisions, simplifie la gestion.

3.2. Menu interactif
5 plats marocains authentiques.
Chaque bouton “Ajouter” ajoute au panier via CartManager.
3.3. Panier dynamique
Affichage automatique des plats sélectionnés.

Gestion des quantités :

➕ augmente

➖ diminue ou supprime le plat

Calcul automatique du total.
3.4. Paiement sur place
Enregistre la commande dans Room.

Affiche une page de remerciement avec photo du riad.

Vide le panier + numéro de réservation.
3.5. Historique des commandes
Liste toutes les commandes passées.

Groupées par numéro de réservation.

Affiche :

plat,quantité,prix,date,numéro de réservation.

Bouton pour supprimer tout l’historique.

 
4. Interface Utilisateur


L’UI respecte un thème marocain chaleureux :

Couleurs dominantes :

Beige (#EFD8C0)

Marron traditionnel (#3E291A)

Cuivre/orangé (#9B4922)

Boutons arrondis.

Images centrées et haute qualité.

Navigation simple et intuitive.

🧩 
5. Structure des fichiers
📂 app/src/main/java/com/example/riad1/
│── MainActivity.kt
│── MenuActivity.kt
│── CartActivity.kt
│── PaymentActivity.kt
│── HistoryActivity.kt
│── CartManager.kt
│── Room/
│     ├── AppDatabase.kt
│     ├── OrderItemEntity.kt
│     └── OrderDao.kt
│── adapters/
│     └── HistoryAdapter.kt
📂 res/layout/
│── activity_main.xml
│── activity_menu.xml
│── activity_cart.xml
│── activity_payement.xml
│── activity_history.xml
│── cart_item.xml
│── item_history.xml
📦 
6. Technologies utilisées
Kotlin (100%)

Android SDK

Room Database

RecyclerView

Coroutines (I/O) pour les opérations en base

ConstraintLayout / LinearLayout pour l’UI

🚀 
7. Améliorations futures
Système de gestion des tables disponibles.

Ajout d’un back-office pour le personnel.

Notifications lorsque la commande est prête.

Support du multilingue (FR – EN – AR).

Système de QR codes par table.




Projet réalisé dans le cadre d’un développement mobile éducatif.

