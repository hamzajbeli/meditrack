# 🏥 MediTrack

> Application Spring Boot de gestion de rendez-vous et de dossiers patients,
> conçue comme support d'un projet DevSecOps.

> ⚠️ **Ce dépôt contient des failles volontaires** (injection SQL, IDOR,
> mauvaise configuration). Ne pas déployer sur un serveur public.

## Contexte
Projet personnel [année]. L'objectif : développer une application, y introduire
des vulnérabilités connues, les détecter automatiquement avec un pipeline
GitHub Actions, puis les corriger.

## Fonctionnalités
- Trois rôles : patient, médecin, administrateur
- [Prise de rendez-vous]
- [Dossiers patients]

## Pipeline DevSecOps
| Étape | Outil | Statut |
|---|---|---|
| Build et tests | Maven | [ ] |
| Détection de secrets | Gitleaks | [ ] |
| SAST | SonarCloud | [ ] |
| Analyse des dépendances | Trivy | [ ] |
| Analyse de l'image Docker | Trivy, Hadolint | [ ] |
| DAST | OWASP ZAP | [ ] |

## Résultats : quel outil détecte quelle faille
| Faille | Détectée par | Corrigée |
|---|---|---|
| F1 Injection SQL | [à remplir après exécution] | [ ] |
| F2 IDOR | [à remplir] | [ ] |
| F3 Configuration | [à remplir] | [ ] |

## Feuille de route
- [x] Application de base
- [x] Introduction des failles F1 à F3
- [ ] Pipeline GitHub Actions (build, Gitleaks, SonarCloud, Trivy)
- [ ] Ajout de OWASP ZAP (DAST)
- [ ] Correction des failles et rapport avant/après

## Auteur
Hamza Jbeli, [lien LinkedIn]
