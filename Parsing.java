import extensions.*;

class Parsing extends Program {

    //    CSVFile file = loadCSV("my_questions_math.csv");
    //String[][] save = new String[rowCount(file)][columnCount(file)];
    
    void algorithm() {
	int[] error = new int[2];

	my_menu();
	//	my_pars();
	//	error = my_err();
	//solve(error);
    }

    /*    String[][] my_pars() {	
	for (int i = 0; i < rowCount(file) ; ++i) {
	    for (int j = 0 ; j < columnCount(file); ++j) {
		save[i][j] = getCell(file,i,j);
	    }
	}
	return save;
    }
    
    void printTab(String[][] tab) {
	for (int i = 0; i < tab.length; ++i) {
	    for (int j = 0; j < tab[0].length; ++j) {
		print(tab[i][j]);
	    }
	    println();
	}
    }

    void solve(int[] error) {
	println("La question est : combien vaut " + save[0][1]);
	println("Les reponses possibles sont : " + save[1][1] + "," + error[0] + "," + error[1]);
	print("Veuillez rentrer la reponse a la question : ");
	int rez = readInt();
	if (rez == Integer.parseInt(save[1][1]))
	    println("BRAVO!!!!!!!! NIVEAU SUPERIEUR");
	else
	    println("wronnng ");
	
    }

    int[] my_err() {
	int[] err = new int[2];
	for (int i = 0; i < err.length; ++i) {
	    err[i] = Integer.parseInt(save[1][1]) + (int) (random() * 10);
	}
	return err;
    }
    */
    void my_menu() {
	Image img = newImage(600, 400);
	fill(img, RGBColor.BLUEVIOLET);
	setColor(img, RGBColor.WHITE);
	drawRect(img, 150, 150, 100, 50);
	addZone(img,"Maths", 150,150,100,50);
	drawString(img, "MATHS", 160, 170);
	drawString(img, "FRANCAIS", 280, 170);	
	drawString(img, "HISTOIRE", 400, 170);
	drawRect(img, 270, 150, 100, 50);
	addZone(img, "FRANCAIS", 270, 150, 100, 50);
	drawRect(img, 390, 150, 100, 50);
	addZone(img, "HISTOIRE", 390, 150, 100, 50);
	show(img);
	while (true);
    }

    void mouseChanged(String name, int x, int y, int button, int String event) 
    {
	println("ZONE"+ name+ "a genere l'evenement" + event);
	if (equals(event, "CLICKED")) {
	    println(name);
	}
    }
}
