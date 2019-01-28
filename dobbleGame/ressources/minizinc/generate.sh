nbParameters=4

if [ "$#" -ne $nbParameters ]
    then echo $nbParameters" arguments needed" 1>&2 && exit

fi

echo "nbCartes = $1;"> ressources/minizinc/data.dzn;
echo "nbSymboles = $2;" >> ressources/minizinc/data.dzn;
echo "nbSymbolesParCarte = $3;" >> ressources/minizinc/data.dzn;
echo "nbVariantes = $4;" >> ressources/minizinc/data.dzn;

ressources/minizinc/minizinc-program/bin/minizinc ressources/minizinc/dobble.mzn ressources/minizinc/data.dzn --output-mode json --soln-sep "" -p 4 > ressources/minizinc/result.txt
