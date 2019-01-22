int : nbCartes;
int : nbSymboles;
int : nbSymbolesParCarte;
int : nbVariantes;

set of int : cartes = 1..nbCartes;
set of int : symboles = 1..nbSymboles;
set of int : symboles_cartes = 1..nbSymbolesParCarte;
set of int : variantes = 1..nbVariantes;

var int : max_symboles = ((nbCartes*nbSymbolesParCarte) div nbSymboles)+2;


array[cartes, symboles_cartes] of var symboles : jeu_symboles;
array[cartes, symboles_cartes] of var variantes : jeu_variantes;
array[symboles] of var int : nb_symboles; % nb_symb[i] = nb d'occurences du symbole i

include "all_different.mzn";
include "increasing.mzn";

% contrainte symbole différent par carte
constraint forall(c in cartes)(all_different([jeu_symboles[c,s] | s in symboles_cartes]));
% contrainte ordre par carte, soucis de performance, on prend que les cartes dont les symboles sont dans l'ordre
constraint forall(c in cartes)(increasing([jeu_symboles[c,s] | s in symboles_cartes]));
% contrainte un symbole commun entre deux cartes
constraint forall(c,cc in cartes where (c<cc))(sum(s in symboles_cartes)(sum(ss in symboles_cartes)(bool2int(jeu_symboles[c,s]=jeu_symboles[cc,ss])))=1);
% somme pour chaque symbole
constraint forall(s in symboles)(sum(c in cartes, ss in symboles_cartes)(bool2int(jeu_symboles[c,ss]=s))=nb_symboles[s]);
% meilleur répartition des symboles
constraint forall(s in symboles)(nb_symboles[s]<=max_symboles);

% Fixe première valeur pour accélérer la propagation
constraint jeu_symboles[1,1] = 1;

% pas besoin de l'output car un argument permet de sortir le résultat en JSON avec toutes les variables
% output [
%     if (i == 1 /\ j == 1) then "{\"list_of_card\":[" else "" endif
%     ++ if j == 1 then "[" else "" endif
%     ++ show(jeu_symboles[i,j])
%     ++ if j == nbSymbolesParCarte then "]" else "," endif
%     ++ if (i == nbCartes /\ j == nbSymbolesParCarte) then "]}" else "" endif
%     | i in cartes, j in symboles_cartes
% ]