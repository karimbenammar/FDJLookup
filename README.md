# FDJLookup
 
FDJLookup est une application Android qui vous permet d'accéder facilement aux différentes leagues de football et parcourir la liste des équipes correspondantes.

## Introduction
L'application comporte un écran principal:
- **La liste des équipes** vous permet d'afficher les différentes équipes et choisir la league à parcourir.

## Screenshot
![SCREENSHOT](../main/.github/screenshot.png?raw=true)

## Recherche avec auto-complete
La recherche des équipes se fait à travers la barre de recherche des leagues. En séléctionnant une league, la liste des équipes se met à jour automatiquement.
La barre de recherche est munie de la fonctionnalité auto-complete, qui affiche les suggestions au fur et à mesure que vous saisissez le nom d'une league.

![SEARCH_AUTOCOMPLETE](../main/.github/searchbar.gif)

## Choix techniques
Le projet utilise le **Design Pattern MVVM**, les frameworks **Jetpack Compose** et **Jetpack Navigation**, et **Hilt** pour l'injection de dépendances.
La bibliothèque de test **Mockito** est utilisée pour créer des objets simulés (mocks) lors de l'écriture de tests unitaires.
Le projet a été conçu avec l'aspect **Scalability** en tête, d'où l'application de la **Clean Architecture** et autres bonnes pratiques telles que la conception de **composants UI réutilisables** et même le suivi des rêgles de collaboration pour les [**Conventional Commits**](https://www.conventionalcommits.org/).

## Test
Les tests unitaires présents dans le projet testent principalement le ViewModel.
Les tests couvrent la réception de données en cas de réussite et d'échec, ainsi que la logique du tri et du filtrage de la liste des équipes.

## Chargement des images
- L'utilisation de **Landscapist/Glide** pour le chargement des images ainsi que des placeholders.