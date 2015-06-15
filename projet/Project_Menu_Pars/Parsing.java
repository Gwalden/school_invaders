/*
 * Projet School Invaders Semestre 1
 *
 *
 * Parsing Question Math
 *
 *
 * @author: Leo_Vingadassamy && Alban_Tartar
 * @date: 18/05/2015
 */

import extensions.*;

class Parsing extends Program {

    CSVFile file = loadCSV("Questions.csv");
    String[][] save = new String[rowCount(file)][columnCount(file)];

    void algorithm(){
	my_pars();
	show_q(1);
	reponse(1);

    }

    String[][] my_pars(){
	for (int i = 0; i < rowCount(file); ++i){
	    for(int j = 0; j < columnCount(file,i);++j){
		    save[i][j] = getCell(file,i,j);
		}
	}
	return save;
    }
    void show_q(int l){
	int pos = l + (l-2);
	for(int j = 0; j <= 3; ++j){
	    println(save[pos][j]);
	}
    }
    void reponse(int l){
	int pos = l + (l-2);
	println("Entrez votre resultat:");
	String rep = readString();
	if (equals(rep,save[pos][1]) == true){
	    println("Bonne reponse");
	}
	else
	    println("FAUX!");
    }
}