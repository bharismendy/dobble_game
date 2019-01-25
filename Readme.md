On a essayé d'imposer des valeurs pour accélerer la propagation :
* constraint jeu_symboles[1,1] = 1; -> set le premier symbole de la première carte
* constraint forall(s in symboles_cartes)(jeu_symboles[1,s]); -> set les symboles de la première carte

Etonnament, forcer la première carte à avoir la suite de valeur (1,2,3,...,nbSymbolesParCarte) donne un résultat en plus de temps que simplement forcer le premier symbole de la première carte.
