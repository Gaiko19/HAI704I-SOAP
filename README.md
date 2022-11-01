# TP2 SOAP : Agence d'hôtels

[![MIT License](https://img.shields.io/github/license/Gaiko19/HAI704I-SOAP)](https://img.shields.io/github/license/Gaiko19/HAI704I-SOAP/)
[![release](https://img.shields.io/github/v/release/Gaiko19/HAI704I-SOAP)](https://github.com/Gaiko19/HAI704I-SOAP/releases/tag/v1.0)

Ce projet implémente la partie 1 du TP2 de l'UE HAI704I de l'Université de Montpellier. Il s'agit de mettre en place un service de réservation et recherche d'hôtels en utilisant l’appel de méthodes distantes.
L'utilisateur est un client qui possède une carte de crédit et compte dans l'une des agences de voyages disponibles (il y'en a trois). Une agence est une entité possédant une liste d'hôtels partenaires ainsi qu'un pourcentage de réduction par hôtel. Le client peut donc effectuer des recherches et trouver un hôtel correspondant à sa demande et ainsi réserver une chambre.
Cette première partie du projet est totalement locale, un seule projet s'occupe de tout.
Une partie beaucoup plus avancée et disstribuée et disponible dans les releases.

## Contenu

Ce projet contient :
- Un projet
- Un Makefile
- Ce README

## Pré-requis

Le projet a été fait pour fonctionner sur `Linux` et `OSX`, il peut potentiellement et normalement fonctionner sur `Windows` mais nous ne garantissons pas que tous fonctionne à 100%.

`Java` doit être installé sur votre machine.

`Maven` doit être installé sur votre machine. Il est possible de l'installer avec :
```bash
sudo apt install maven
```

`Make` doit être installé sur votre machine. Il est possible de l'installer avec :
```bash
sudo apt install make
```

## Utilisation

1. Commencer par installer le projet en le récupérant [`ici`](https://github.com/Gaiko19/HAI704I-SOAP/releases/tag/v1.0).

2. Ouvrez un terminal à la racine du projet (à l'endroit du Makefile et du README)

3. Compiler le projet en faisant la commande suivante.
```bash
make
```

3. Lancer le avec celle-ci
```bash
make run
```

4. Ce projet fait uniquement une suite d'instructions. Vous n'avez qu'à appuyer sur Entrer pour faire avancer l'exécution

5. Une fois terminé vous pouvez nettoyer les fichiers avec
```bash
make clean
```

## Authors

Ce projet a été fait par :

- [Said Adam](https://github.com/gaiko19)
- [Cossu Arnaud](https://github.com/ArnaudCs)

En M1 Génie Logiciel à l'Université de Montpellier
