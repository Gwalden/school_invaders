import extensions.*;
import java.awt.Dimension;

class Projet extends Program {

    Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int hauteur = (int)tailleEcran.getHeight();
    int largeur = (int)tailleEcran.getWidth();
    String[][] save ;
    String eventClicked = "";
    TransparentImage img = newTransparentImage(largeur/2, hauteur/2);
    TransparentImage vaisseau = newTransparentImage("Vaisseau","vaiss.png");
    TransparentImage tir = newTransparentImage("Tir","tir.png");
    TransparentImage logo = newTransparentImage("logo","logonew.png");
    TransparentImage wall = newTransparentImage("wall","wallmenu.jpg");
    TransparentImage fr = newTransparentImage("fr","Boutonfr.png");
    TransparentImage math = newTransparentImage("Math","Math.png");
    TransparentImage hist = newTransparentImage("Hist","Histoire.png");
    TransparentImage vict = newTransparentImage("Vict","Victory.jpeg");
    int mat = 0;
    int i = 300;
    int lives = 10;
    int	rez = 0;
    int j = 1;
    int	l = 1;
    int[] my_answ;
    boolean finished = true;
    Alien[] my_tab = new Alien[50];
    
    ///Structure contenant les nouvelles coordonnes de l'objet touche et son indice dans le tableau de structure alien.
    class Check 
    {
	boolean	hit;
	int numb;
	int[] new_coord = new int[2];
    }

    ///Structure contenant les bonnes et mauvaises reponses avec leur coordonne dans la map
    class Alien 
    {
	int alien;
	int[] coord = new int[2];
    }
  
    void algorithm()
    {
      	menu();
	while(finished)
	    {}
    }

    /// Fonction permettant de recuperer les informations du CSV.File dans un tableau 
    String[][]		my_pars(String name) {
	CSVFile file = loadCSV(name);
	save = new String[rowCount(file)][columnCount(file)];
	for (int i = 0; i < rowCount(file) ; ++i) {
	    for (int j = 0 ; j < columnCount(file,i); ++j) {
		save[i][j] = getCell(file,i,j);
	    }
	}
	return save;
    }

    ///Fonction affichant le menu du jeu
    void menu()
    {
	int i = 100;
	int j = 50;
	drawImage(img,wall,0,0);
	drawImage(img,copyAndResize(logo, "logo",120,100),10,10);
	add_button("Math",100,120,i,j);
	add_button("Francais",400,120,i,j);
	add_button("Histoire",250,200,i,j);
	show(img);
    }

    ///Fonction qui permet d'ajouter les zones des differents mode de jeu (pour pouvoir choisir grace au clic de la souris).
    void add_button(String str,int x, int y, int i, int j){
	if(equals(str,"Math") == true){
	    addZone(img,str,x,y,i,j);
	    drawImage(img,math,x,y);
	}
	if(equals(str,"Francais") == true){
	    addZone(img,str,x,y,i,j);
	    drawImage(img,fr,x,y);
	}
	if(equals(str,"Histoire") == true){
	    addZone(img,str,x,y,i,j);
	    drawImage(img,hist,x,y);
	}
    }

    /// Lancement du mode de jeu Francais
    void french()
    {
	printHeart();
	my_pars("Francais.csv");
	drawImage(img,wall,0,0);
	drawImage(img,copyAndResize(logo, "logo",120,100),10,10);	
	finished = true;
	setColor(img,RGBColor.WHITE);
	add_question();
	drawRect(img, 50, 150, 170, 70);       
	drawRect(img, 250, 150, 170, 70);
	drawRect(img, 450, 150, 170, 70);
	printValue();
	show(img);
    }

    ///Affichage de la question pour le francais et l'histoire en haut de la fenetre
    void add_question()
    {
	drawRect(img,140,25,550,50);
	drawString(img,save[j-1][0],150,50);
    }

    ///Fonction qui affiche la fin
    void check_end(){
	show(vict);
	drawString(vict,"Félicitation, vous avez gagné",500,200);
	drawString(vict,"Vie(s) restante(s):",500,300);
	drawString(vict,toString(lives),640,300);
    }

    ///Lancement du mode de jeu Histoire
    void histoire() 
    {
	if(lives >= 0 && j > 60)
	    check_end();
	printHeart();
	my_pars("Histoire.csv");
	drawImage(img,wall,0,0);
	drawImage(img,copyAndResize(logo, "logo",120,100),10,10);
	finished = true;
	setColor(img,RGBColor.WHITE);
	add_question();
	drawRect(img, 50, 150, 150, 70);       
	drawRect(img, 250, 150, 150, 70);
	drawRect(img, 450, 150, 150, 70);
	printValue();
	show(img);
    }

    ///Fonction permettant l'affichage des bonnes ou mauvaises reponses sur l'image
    void printValue()
    {
	if (equals(save[j - 1][1], save[j][1]))
	    rez = 1;
	if (equals(save[j - 1][2], save[j][1]))
	    rez = 2;
	if (equals(save[j - 1][3], save[j][1]))
	    rez = 3;
	drawString(img, save[j - 1][1], 70, 190);
	drawString(img, save[j - 1][2], 270, 190);
	drawString(img, save[j - 1][3], 470, 190);
    }

    ///Affichage de la question pour les maths en haut de la fenetre
    void questionMath()
    {
	setColor(img, RGBColor.GREEN);
	drawRect(img,largeur/4 - 75,10,100,25);
	drawString(img,save[j-1][1],largeur/4 - 70,25);
    }

    ///Lancement du mode de jeu math
    void maths()
    {
	my_pars("Maths.csv");
	printHeart();
	finished = true;
	setColor(img, RGBColor.BLACK);
	fillRect(img, 0, 0, 600, 300);
	for (int i = 0; i< my_tab.length; ++i)
	    my_tab[i] = new Alien();
	questionMath();
	my_aff(my_alien());
	my_answ = fill_tab();
	show(img);
    }

    
    ///Fonction permettant de remplir toutes les bonnes et mauvaises reponses
    Alien[]		my_alien()
    {
	int	rand =  (int)random() * 10;
	for (int i = 0; i < my_tab.length; ++i)
	    {
		if (i != rand)
		    {
			my_tab[i] = new Alien();
			my_tab[i].alien = my_err(Integer.parseInt(save[j][l]));
		    }
		else
		    my_tab[i].alien = Integer.parseInt(save[j][l]);
	    }
	return my_tab;
    }

    ///Fonction qui permet de remplir les cases comportant les bonnes reponses
    int[]		fill_tab() 
    {
	int i = 0;
	for (int k = 0; k != length(my_tab) ; ++k)
	    {
		if (my_tab[k].alien == Integer.parseInt(save[j][l]))
		    i = i + 1;
	    }
	int[] tab = new int[i];
	i = 0;
	for (int k = 0; k < length(my_tab) ; ++k)
	    {
		if (my_tab[k].alien == Integer.parseInt(save[j][l]))
		    {
			tab[i] = k;
			i = i + 1;
		    }
	    }
	return tab;
    }

    /// Fonction permettant de retourner un nombre aleatoire que l'on va mettre dans les "faux" aliens
    int			my_err(int rez) {
	return ((int) (random() * 5) + rez);
    }
     
    /// Fonction affichant toutes les cases : Bonnes et mauvaises reponses
    void		my_aff(Alien[] my_tab) 
    {
	int a = 20;
	int b = 50; 
	int num = 0;
	for (int i = 0; i != 5; ++i) 
	    {
		for (int k = 0; k != 10; ++k) 
		    {
			setColor(img, RGBColor.WHITE);
			fillRect(img, a, b, 35 , 35);
			setColor(img, RGBColor.BLACK);
			drawString(img, toString(my_tab[num].alien),(a + 10) ,b + 20);
			my_tab[num].coord[0] = a;
			my_tab[num].coord[1] = b;
			a += 60;
			num = num + 1;
		    }
		a = 20;
		b += 40;
	    }
    }
    
    ///Fonction transformant un int en string
    String		toString(int err) 
    {
	String res = "";
	res = res + err;
	return res;
    }
    
    int count = 0;

    ///Fonction qui gere les evenements du clavier, avec c = la lettre rentree et event = l'evenement.
    void	keyChanged(char c, String event)
    {
	if (c == 'q' && (mat == 1 ||mat == 2 || mat == 3))
	    {
		setColor(img, RGBColor.BLACK);
		drawRect(img, i + 10, 350, 100, 100);
		fillRect(img, i + 10, 350, 100, 100);
		i -= 5;
		drawImage(img, copyAndResize(vaisseau, "vaisseau-small",70,70), i, 350);
	    }
	if (c == 'd' && (mat == 1 ||mat == 2 || mat == 3))
	    {
		setColor(img, RGBColor.BLACK);
		drawRect(img, i - 10, 350, 100, 100);
		fillRect(img, i - 10, 350, 100, 100);
		i += 5;
		drawImage(img, copyAndResize(vaisseau, "vaisseau-small",70,70), i, 350);
	    }
	if (c == ' ' && (mat == 1 || mat == 2))
	    tirHistFr();
	if (c == ' ' && mat == 3)
	    tirMath();
	if (c == 'x' && mat == 3)
	    capture();
    }

    ///Fonction gerant le tir dans le mode math
    void tirMath() 
    {
	count = count + 1;
	if (count == 3)
	    {
		count = 0;
		Check my_check = new Check();
		my_check.hit = false;
		my_check = check_coord(i, my_check);
		if (my_check.hit)
		    {
			if (my_tab[my_check.numb].alien == Integer.parseInt(save[j][l]))
			    {
				setColor(img,RGBColor.BLACK);
				fillRect(img, my_check.new_coord[0], my_check.new_coord[1], 35 , 35);
				lives = lives - 1;
				println("VOUS AVEZ PERDU UNE VIE ! Il vous reste " + lives + "vies");
				check_lives();
				my_tab[my_check.numb].alien = 0;
			    }
			else
			    {
				my_tab[my_check.numb].alien = 0;
				setColor(img,RGBColor.BLACK);
				fillRect(img, my_check.new_coord[0], my_check.new_coord[1], 35 , 35);
			    }
		    }		    
	    }
    }

    ///fonction gerant la capture des bonnes reponses dans le mode math
    void capture() 
    {
	count = count + 1;
	if (count == 3)
	    {
		count = 0;
		Check my_check = new Check();
		my_check.hit = false;
		my_check = check_coord(i, my_check);
		int traj = 300;
		if (my_check.hit)
		    {
			println(my_tab[my_check.numb].alien);
			if (my_tab[my_check.numb].alien == Integer.parseInt(save[j][l])) 
			    {
				println("UNE CIBLE DE SAUVEE ");
				setColor(img,RGBColor.BLUEVIOLET);
				fillRect(img, my_check.new_coord[0], my_check.new_coord[1], 35 , 35);
				my_tab[my_check.numb].alien = 0;
				check_win();
			    }
			else
			    {
				my_tab[my_check.numb].alien = 0;
				lives = lives - 1;
				println("MAUVAISE CIBLE  ! Il vous reste "+ lives+"vies ");
				check_lives();
				setColor(img,RGBColor.BLACK);
				fillRect(img, my_check.new_coord[0], my_check.new_coord[1], 35 , 35);
			    }
		    }
	    }
    }

    ///Fonction permettant de renvoyer une structures avec les coordonnees de l'objet touche, avec i la position du vaisseau, et if_hit la structure ayant les nouvelles coordonnees.
    Check check_coord(int i, Check if_hit) 
    {
	int col = 0;
	for (int k = 49; k != 0 ; --k) 
	    {
		if (i + 20 >= my_tab[k].coord[0] && i+20 <= (my_tab[k].coord[0] + 10))
		    {
			for (; my_tab[k].alien == 0 && k >=10; k = k - 10);
			if_hit.hit = true;
			if_hit.new_coord[0] = my_tab[k].coord[0];
			if_hit.new_coord[1] = my_tab[k].coord[1];
			if_hit.numb = k;
			println(if_hit.new_coord[0]);
			println(if_hit.new_coord[1]);
			return if_hit;
		    }
	    }
	if_hit.hit = false;
	return if_hit;
    }

    ///Fonction permettant de savoir si le joueur est toujours en vie
    void check_lives()
    {
	printHeart();
        if (lives == 0)
	    {
		TransparentImage game_over = newTransparentImage("END","game_over.jpg");
		drawImage(img,copyAndResize(game_over, "game_over_small",largeur/2,hauteur/2),0,0);
		j = (j - 1) / 2;
		println("Vous avez reussi a atteindre le niveau " + j); 
	    }
    }

    ///Affichage du coeur avec les vies
    void printHeart()
    {
	
	setColor(img, RGBColor.BLACK);
	drawRect(img, largeur/2 - 200, 60, 170, 90);
	fillRect(img, largeur/2 - 200, 60, 170, 90);
	if (mat == 1 || mat == 2)
	    drawRect(img,140,25,500,50);
	TransparentImage heart = newTransparentImage("heart","coeurdevie.png");
	drawImage(img, copyAndResize(heart,"hear-smaller", 170, 90), largeur/2 - 200, 60);
	setColor(img, RGBColor.WHITE);
	drawString(img, toString(lives), largeur/2 -120, 60 + 45); 
    }

    ///fonction permettant de savoir si le joueur a gagne ou pas
    void check_win()
    {
	for (int i = 0; i != length(my_answ) ; ++i)
	    {
		if (my_tab[my_answ[i]].alien != 0)
		    return ;
	    }
	println("BRAVO NIVEAU REUSSI !");
	j = j + 2;
	empty_struct();
	finished = false;
	maths();
    }

    ///Fonction permettant de vider les structures avant le passage du niveau supplementaire
    void empty_struct()
    {
	int[] vide = new int[length(my_answ)];
	my_answ = vide;	
	Alien[]	       empty = new Alien[50];
	my_tab = empty;
    }

    ///Fonction gerant les animations du tir
    void animeTir(int nb)
    {
	int traj = 300;
	while(traj > nb)
	    {
		TransparentImage save = copyAndResize(tir, "tir-small",1,10);
		drawImage(img,save , i + 40, traj);
		traj = traj - 20;
	    }
    }

    ///Fonction gerant le tir dans le mode Histoire et Francais
    void tirHistFr()
    {
	count = count + 1;
	if (count == 3)
	    {
		count = 0;
		int traj = 300;
		if (!check_touch())
		    animeTir(0);
		else
		    {
			animeTir(100);
			if (mat == 2 &&check_who_touch())
			    {
				j = j + 2;
				histoire();
			    }
			else if (mat == 1 && check_who_touch())
			    {
				j = j + 2;
				french();
			    }
		    }
	    }
    }
       
    ///Fonction permettant de voir si le joueur a touche une bonne reponse ou pas, et agir en consequence
    boolean	check_who_touch()
    {
	drawString(img,toString(rez),400,5);
	println(rez);
	if (rez == 1)
	    {
		if (i >= 50 && i <= 200) {
		    println("Bonne Reponse !");
		    return true;
		}
		else if (rez == 1 && (i >= 250 && i <= 400) || (i >= 450 && i <= 600))
		    {
			lives = lives - 1;
			println("Mauvaise cible ! Il vous reste "+lives +" vies");
			check_lives();
		    }
	    }
	if (rez == 2) 
	    {
		if(i >= 250 && i <= 400)
		    {
			println("Bonne Reponse !");
			return true;
		    }
		else if ((i >= 50 && i <= 200) || (i >= 450 && i <= 600))
		    {
			lives = lives - 1;
			println("Mauvaise cible ! Il vous reste "+lives +" vies");
			check_lives();
		    }

	    }
	if (rez == 3)
	    {
		if (i >= 450 && i <= 600) {
		    println("Bonne Reponse !");
		    return true;
		}
		else if ((i >= 250 && i <= 400) || (i >= 50 && i <= 200))
		    {
			lives = lives - 1;
			println("Mauvaise cible ! Il vous reste "+lives +" vies");
			check_lives();
		    }
	    }
	return false;
    }
    
    ///Fonction permettant de savoir si le joueur a touche un des rectangles contenant les reponses
    boolean check_touch()
    {
	println(i);
	if ((i >= 50 && i <= 200) || (i >= 250 && i <= 400) || (i >= 450 && i <= 600))
	    return true;
	return false;
    }

 
    ///Fonction qui permet de gerer les clics de la souris, avec name = nom de la zone, x et y les coordonnees, button = le nom du boutton utilise avec la souris, et event l'evenement.
    void	mouseChanged(String name, int x, int y, int button, String event) {
	if (equals(name,"Francais") && button == 1 && event == "PRESSED") 
	    {
		removeAllZone(img);
		finished = false;
		println(finished);
		//eventClicked = "francais";
		mat = 1;
		french();
	    }	
	if (equals(name,"Histoire") && button == 1 && event == "PRESSED")
	    {
		removeAllZone(img);
		finished = false;
		mat = 2;
		histoire();
	    }
	if (equals(name,"Math") && button == 1 && event == "PRESSED") 
	    {
		mat = 3;
		removeAllZone(img);
		finished = false;
		maths();
	    }
	}

    void	mouseHasMoved(int x, int y) {
    }
    void	mouseHasChanged(String name, int x, int y, int button, String event){
    }

}
