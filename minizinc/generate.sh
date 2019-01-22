nbParameters=4

if [ "$#" -ne $nbParameters ]
    then echo $nbParameters" arguments needed" 1>&2 && exit
    
fi

echo "nbCartes = $1;"> data.dzn;
echo "nbSymboles = $2;" >> data.dzn;
echo "nbSymbolesParCarte = $3;" >> data.dzn;
echo "nbVariantes = $4;" >> data.dzn;

minizinc-program/bin/minizinc dobble.mzn data.dzn --output-mode json --soln-sep "" > results.txt
