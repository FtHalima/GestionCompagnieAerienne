# 🛫 Gestion Compagnie Aérienne

 
Système de gestion des réservations de vols pour une compagnie aérienne.

---

## 📋 Description

Application Java permettant de gérer :
- ✈️ **Passagers** : Inscription, modification, consultation, suppression (CRUD complet)
- 🎫 **Billets** : Réservation, annulation, remboursement, modification
- 🗄️ **Base de données PostgreSQL** : Stockage persistant des données
- 🖥️ **Interface graphique Swing** : Interface utilisateur intuitive

---

## 🏗️ Architecture du Projet
```
GestionCompagnieAerienne/
├── src/
│   └── com.compagnieaerienne/
│       ├── modele/           # Entités métier (Passager, Billet)
│       ├── enumeration/      # Énumérations (Genre, TypePassager, etc.)
│       ├── dao/              # Accès aux données (JDBC, ConnexionBD)
│       ├── vue/              # Interfaces graphiques (Swing)
│       └── test/             # Classes de test
├── lib/
│   └── postgresql-42.7.3.jar # Driver JDBC PostgreSQL
├── sql/
│   └── compagnie_aerienne.sql # Script de création de la BD
└── README.md                  # Ce fichier
```

---

## 🛠️ Technologies Utilisées

| Technologie | Version | Usage |
|-------------|---------|-------|
| **Java** | 8+ | Langage de programmation |
| **PostgreSQL** | 12+ | Base de données relationnelle |
| **JDBC** | 4.2 | Connexion Java-PostgreSQL |
| **Swing** | - | Interface graphique |
| **Eclipse** | 2023+ | Environnement de développement |
| **Git/GitHub** | - | Gestion de versions |

---

## 📦 Prérequis

Avant de commencer, assurez-vous d'avoir installé :

- ✅ **JDK 8 ou supérieur** : [Télécharger ici](https://adoptium.net/)
- ✅ **PostgreSQL 12+** : [Télécharger ici](https://www.postgresql.org/download/)
- ✅ **Eclipse IDE** : [Télécharger ici](https://www.eclipse.org/downloads/)
- ✅ **pgAdmin 4** : Inclus avec PostgreSQL (pour gérer la base)

---

## ⚙️ Installation et Configuration

### **1️⃣ Cloner le repository**
```bash
git clone https://github.com/FtHalima/GestionCompagnieAerienne.git
cd GestionCompagnieAerienne
```

### **2️⃣ Importer dans Eclipse**

1. Ouvrir Eclipse
2. `File` → `Import...`
3. `General` → `Existing Projects into Workspace`
4. `Select root directory` → Choisir le dossier `GestionCompagnieAerienne`
5. Cliquer **Finish**

### **3️⃣ Configurer PostgreSQL**

#### **a) Créer la base de données**

Ouvrir **pgAdmin 4** :
1. Clic droit sur `Databases` → `Create` → `Database...`
2. **Database :** `CompagnieAérienne`
3. **Save**

#### **b) Exécuter le script SQL**

1. Clic droit sur `CompagnieAérienne` → `Query Tool`
2. Ouvrir le fichier `sql/compagnie_aerienne.sql`
3. Copier-coller le contenu
4. Cliquer **Execute** (▶️)

✅ Les tables `Passager` et `Billet` sont créées avec des données de test !

### **4️⃣ Configurer la connexion dans le code**

Ouvrir `src/com/compagnieaerienne/dao/ConnexionBD.java` :
```java
private static final String URL = "jdbc:postgresql://localhost:5432/CompagnieAérienne";
private static final String USER = "postgres";
private static final String PASSWORD = "votre_mot_de_passe";  // ⚠️ Modifier ici
```

**Remplacer** `votre_mot_de_passe` par votre mot de passe PostgreSQL.

---

## 🚀 Exécution

### **Tester la connexion à la base de données**

1. Dans Eclipse, ouvrir `com.compagnieaerienne.test.TestConnexion`
2. Clic droit → `Run As` → `Java Application`

**Résultat attendu :**
```
✓ Driver PostgreSQL chargé avec succès
✓ Connexion à la base de données établie avec succès !
✓ 2 table(s) trouvée(s) !
```

### **Tester les énumérations**

1. Ouvrir `com.compagnieaerienne.test.TestEnumerations`
2. Clic droit → `Run As` → `Java Application`

**Résultat attendu :**
```
✓ TypePassager : 4 valeurs
✓ Genre : 2 valeurs
✓ ClasseBillet : 3 valeurs
✓ StatutBillet : 4 valeurs
✓ Nationalite : 26 valeurs
```

---

## 📊 Structure de la Base de Données

### **Table Passager**

| Colonne | Type | Description |
|---------|------|-------------|
| `id_passager` | SERIAL | Identifiant unique (PK) |
| `nom` | VARCHAR(100) | Nom du passager |
| `prenom` | VARCHAR(100) | Prénom du passager |
| `date_naissance` | DATE | Date de naissance |
| `num_passeport` | VARCHAR(20) | Numéro de passeport (unique) |
| `nationalite` | VARCHAR(50) | Nationalité |
| `type_passager` | VARCHAR(20) | Adulte/Enfant/Bébé/Senior |
| `genre` | VARCHAR(10) | Masculin/Féminin |
| `email` | VARCHAR(100) | Email (unique) |
| `telephone` | VARCHAR(20) | Numéro de téléphone |

### **Table Billet**

| Colonne | Type | Description |
|---------|------|-------------|
| `num_billet` | VARCHAR(20) | Numéro de billet (PK) |
| `id_passager` | INTEGER | Référence au passager (FK) |
| `classe` | VARCHAR(20) | Première/Business/Économique |
| `tarif` | DECIMAL(10,2) | Prix du billet |
| `statut` | VARCHAR(20) | Réservé/Annulé/Remboursé/Modifié |
| `date_emission` | DATE | Date d'émission |
| `num_vol` | VARCHAR(20) | Numéro du vol |
| `num_siege` | VARCHAR(10) | Numéro de siège |

---

## 👥 Équipe de Développement

| Membre | Rôle | Responsabilités |
|--------|------|-----------------|
| **Personne 1** | Infrastructure & Base de données | - Script SQL<br>- ConnexionBD (JDBC)<br>- Énumérations (5)<br>- Configuration Git |
| **Personne 2** | Logique Métier Passager | - Classe Passager<br>- Méthodes CRUD Passager<br>- Gestion des exceptions |
| **Personne 3** | Logique Métier Billet & UI | - Classe Billet<br>- Méthodes CRUD Billet<br>- Interfaces graphiques<br>- Tests d'intégration |

---

## 📝 Fonctionnalités

### **Module Passager**
- ➕ Ajouter un nouveau passager
- 🔍 Rechercher un passager (par ID, nom, passeport)
- ✏️ Modifier les informations d'un passager
- 🗑️ Supprimer un passager
- 📋 Lister tous les passagers

### **Module Billet**
- ➕ Émettre un nouveau billet
- 🔍 Rechercher un billet (par numéro, passager, vol)
- ✏️ Modifier un billet
- ❌ Annuler un billet
- 💰 Rembourser un billet
- 📋 Lister tous les billets

### **Module Interface Graphique**
- 🏠 Menu principal de navigation
- 📝 Formulaires de saisie
- 📊 Tableaux d'affichage des données
- 🔔 Messages de confirmation/erreur

---

## 🧪 Tests

### **Tests disponibles**

1. **TestConnexion** : Teste la connexion JDBC à PostgreSQL
2. **TestEnumerations** : Valide toutes les énumérations
3. **TestGestionCompagnieAerienne** : Tests d'intégration complets

### **Exécuter les tests**
```bash
# Dans Eclipse
Clic droit sur la classe de test → Run As → Java Application
```

---

## 🔧 Configuration Git

### **Cloner le projet**
```bash
git clone https://github.com/FtHalima/GestionCompagnieAerienne.git
```

### **Créer une branche pour vos modifications**
```bash
git checkout -b feature/nom-de-votre-fonctionnalite
```

### **Commit et push**
```bash
git add .
git commit -m "Description de vos modifications"
git push origin feature/nom-de-votre-fonctionnalite
```

---

## 📅 Planning

| Phase | Durée | État |
|-------|-------|------|
| **Phase 1** : Infrastructure | 2-3 jours | ✅ Terminée |
| **Phase 2** : Classe Passager | 2-3 jours | 🔄 En cours |
| **Phase 3** : Classe Billet & UI | 3-4 jours | ⏳ À venir |
| **Phase 4** : Tests & Intégration | 1-2 jours | ⏳ À venir |

---

## 🐛 Résolution de Problèmes

### **Erreur : "Driver PostgreSQL non trouvé"**
**Solution :** Vérifier que `postgresql-42.7.3.jar` est dans le Build Path
```
Clic droit sur le JAR → Build Path → Add to Build Path
```

### **Erreur : "Database does not exist"**
**Solution :** Créer la base de données avec pgAdmin
```sql
CREATE DATABASE "CompagnieAérienne";
```

### **Erreur : "Authentication failed"**
**Solution :** Vérifier le mot de passe dans `ConnexionBD.java`

---

## 📚 Documentation

- [JavaDoc](./doc/) : Documentation générée automatiquement
- [Diagramme UML](./uml/) : Diagrammes de classes
- [Cahier des charges](./docs/cahier_des_charges.pdf) : Spécifications du projet

---

## 📄 Licence

Projet académique - Cours POO-Java  
© 2025 - Tous droits réservés

---

## 📧 Contact

Pour toute question concernant le projet :

- **GitHub** : [FtHalima](https://github.com/FtHalima)
- **Email** : votre.email@example.com

---

## 🎯 Objectifs Pédagogiques

Ce projet permet de maîtriser :
- ✅ Programmation Orientée Objet (POO)
- ✅ JDBC et connexion aux bases de données
- ✅ Gestion des exceptions
- ✅ Interfaces graphiques Swing
- ✅ Pattern DAO (Data Access Object)
- ✅ Pattern Singleton
- ✅ Utilisation de Git/GitHub
- ✅ Travail en équipe

---

## 🌟 Remerciements

Merci au professeur et aux membres de l'équipe pour leur contribution à ce projet !

---

**Dernière mise à jour :** Décembre 2025
```

---

### **Étape 3 : Sauvegarder**

1. **Sauvegarder** le fichier (`Ctrl + S`)

---

### **Étape 4 : Commit et Push**

1. **Clic droit** sur le projet → `Team` → `Commit...`
2. Sélectionner `README.md`
3. **Message :**
```
📝 Ajout du README complet

- Documentation complète du projet
- Instructions d'installation
- Guide de configuration
- Structure de la base de données
- Informations sur l'équipe