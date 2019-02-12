int : nbCartes;
int : nbSymboles;
int : nbSymbolesParCarte;
int : nbVariantes;

set of int : cartes = 1..nbCartes;
set of int : symboles = 1..nbSymboles;
set of int : symboles_cartes = 1..nbSymbolesParCarte;
set of int : variantes = 1..nbVariantes;

var int : max_symboles = ((nbCartes*nbSymbolesParCarte) div nbSymboles)+2;
var int : min_variantes = ((nbCartes*nbSymbolesParCarte) div nbVariantes)-1;
var int : max_variantes = ((nbCartes*nbSymbolesParCarte) div nbVariantes)+1;


array[cartes, symboles_cartes] of var symboles : jeu_symboles;
array[cartes, symboles_cartes] of var variantes : jeu_variantes;
array[symboles] of var int : nb_symboles; % nb_symb[i] = nb d'occurences du symbole i
array[variantes] of var int : nb_variantes; % nb_variantes[i] = nb d'occurences de la variantes i

include "all_different.mzn";
include "increasing.mzn";
include "at_least.mzn";

% contrainte symbole différent par carte
constraint forall(c in cartes)(all_different([jeu_symboles[c,s] | s in symboles_cartes]));
% contrainte ordre par carte, soucis de performance, on prend que les cartes dont les symboles sont dans l'ordre
constraint forall(c in cartes)(increasing([jeu_symboles[c,s] | s in symboles_cartes]));
% contrainte un symbole commun entre deux cartes
constraint forall(c,cc in cartes where (c<cc))(sum(s in symboles_cartes)(sum(ss in symboles_cartes)(bool2int(jeu_symboles[c,s]=jeu_symboles[cc,ss])))=1);

% Le nombre d'occurence de chaque symbole
constraint forall(s in symboles)(sum(c in cartes, ss in symboles_cartes)(bool2int(jeu_symboles[c,ss]=s))=nb_symboles[s]);
% meilleur répartition des symboles
constraint forall(s in symboles)(nb_symboles[s]<=max_symboles);

% somme pour chaque variante
constraint forall(v in variantes)(sum(c in cartes, ss in symboles_cartes)(bool2int(jeu_variantes[c,ss]=v))=nb_variantes[v]);
% meilleur répartition des variantes
constraint forall(v in variantes)(nb_variantes[v]<=max_variantes);
constraint forall(v in variantes)(nb_variantes[v]>=min_variantes);
% variantes utilisées au moins une fois
constraint forall(v in variantes)(nb_variantes[v]>=1);

% Pour chaque symbole, on vérifie que chaque variante apparait une fois
% constraint forall(s in symboles)(sum(c in cartes, sc in symboles_cartes, v in variantes where jeu_symboles[c,sc]=s)(symboles_variantes[jeu_symboles[c,sc],jeu_variantes[c,sc]])>=1);
% constraint forall(s in symboles, v in variantes)(count_geq([jeu_variantes[c,sc] | c in cartes, sc in symboles_cartes where jeu_symboles[c,sc]=s],v,1));


% sur chaque carte on retrouve toutes les variantes au moins une fois -> permet d'homogéniser les variantes sur les cartes
constraint if nbVariantes <= nbSymbolesParCarte then forall(c in cartes, v in variantes)(at_least(1, [jeu_variantes[c,s] | s in symboles_cartes],v))
      else forall(c in cartes, v in variantes)(all_different([jeu_variantes[c,s] | s in symboles_cartes]))
      endif;


% Fixe la première valeur pour accélérer la propagation
constraint jeu_symboles[1,1] = 1;

solve satisfy;

% pas besoin de l'output car un argument permet de sortir le résultat en JSON avec toutes les variables
% output [
%     if (i == 1 /\ j == 1) then "{\"list_of_card\":[" else "" endif
%     ++ if j == 1 then "[" else "" endif
%     ++ show(jeu_symboles[i,j])
%     ++ if j == nbSymbolesParCarte then "]" else "," endif
%     ++ if (i == nbCartes /\ j == nbSymbolesParCarte) then "]}" else "" endif
%     | i in cartes, j in symboles_cartes
% ]