/*
 *@author Vingadassamy Leo
 *
 *Projet AP School Invaders
 *
 *
 */



import extensions.*;

class Main extends Program 
{
    CSVFile		file = loadCSV("my_questions_math.csv");
    String[][]		save = new String[rowCount(file)][columnCount(file)];
    TransparentImage	vaisseau = newTransparentImage("Vaisseau","vaisseau.png");
    TransparentImage	img = newTransparentImage(600, 300);
    TransparentImage	ship = newTransparentImage(200,200);
    int			i = 250;
    TransparentImage	tir = newTransparentImage("Tir","tir.png");
    Alien[]		my_tab = new Alien[50];
    int			lives = 10;
    int			j = 1;
    int			l = 1;
    int[]		my_answ; 
    boolean		finished = true;

    class Check 
    {
	boolean		hit;
	int		numb;
	int[]		new_coord = new int[2];	
    }

    class Alien 
    {
	int		alien;
	int[]		coord = new int[2];
    }

    void		algorithm()
    {
	my_pars();
	my_game();
    }

    /// Fonction permettant de recuperer les informations du CSV.File dans un tableau 
    String[][]		my_pars() {	
	for (int i = 0; i < rowCount(file) ; ++i) {
	    for (int j = 0 ; j < columnCount(file,i); ++j) {
		save[i][j] = getCell(file,i,j);
	    }
	}
	return save;
    }

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
    
    /// Fonction permettant de retourner un nombre aleatoire que l'on va mettre dans les "faux" aliens
    int			my_err(int rez) {
	return ((int) (random() * 5) + rez);
    }

    /// Fonction permettant de savoir si le joueur a isole ou tue toutes les bonnes reponses.
    int			check_tab(int[] tab) {
	int		i = length(tab) - 1;
	
	for (; i != 0 || tab[i] == 0; --i);
	if (i == 0 && tab[i] != 0)
	    return (1);
	else 
	    return (-1);
    }

    void		my_game()
    {	
	finished = true;
	setColor(img, RGBColor.BLACK);
	fillRect(img, 0, 0, 600, 300);
	for (int i = 0; i< my_tab.length; ++i)
	    my_tab[i] = new Alien();
	printStruct();
	drawImage(img, copyAndResize(vaisseau, "vaisseau-small",70,70), 300, 250);
	my_aff(my_alien());
	my_answ = fill_tab();
	print_question();
	show(img);
	while(finished);
    }

    int[]		fill_tab() {
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

    void		print_question()
    {
	println(save[j][0]);
	if (save[j].length > 2)
	    for (int i = 0; i < 4; ++j) 
		println("Reponse " + i + ")" +  save[j][l]);		
    }

    void		printStruct()
    {
	for (int i = 0; i != 50; ++i)
	    {
		println("STRUC : " + i);
		println(my_tab[i].alien);
		println(my_tab[i].coord[0] + "    " + my_tab[i].coord[1]);
	    }
    }

    /// Fonction affichant toutes les cases : Bonnes et mauvaises reponses
    void		my_aff(Alien[] my_tab) 
    {
	int a = 10;
	int b = 20; 
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
		a = 10;
		b += 40;
	    }
    }

    ///Fonction transformant un int en chaine de caractere
    String		toString(int err) 
    {
	String res = "";
	res = res + err;
	return res;
    }

    int			count = 0;

    void		keyChanged(char c, String event) 
    {
	if (c == 'q')
	    {
		setColor(img, RGBColor.BLACK);
		drawRect(img, i + 10, 250, 50, 50);
		fillRect(img, i + 10, 250, 50, 50);
		i -= 5;
		drawImage(img, copyAndResize(vaisseau, "vaisseau-small",50,50), i, 250);
	    }

	if (c == 'd')
	    {
		setColor(img, RGBColor.BLACK);
		drawRect(img, i - 10, 250, 50, 50);
		fillRect(img, i - 10, 250, 50, 50);
		i += 5;
		drawImage(img, copyAndResize(vaisseau, "vaisseau-small",50,50), i, 250);
	    }
	if (c == 's')
	    {
		setColor(img, RGBColor.BLUE);
		drawRect(img, 300, 250, 50, 50);
		fillRect(img, 300, 250, 50, 50);
	    }	
	if (c == ' ')
	    {
		count = count + 1;
		if (count == 3)
		    {
			count = 0;
			println("SPACE");
			Check my_check = new Check();
			my_check.hit = false;
			my_check = check_coord(i, my_check);
			int traj = 300;
			while(traj != 0)
			    {
		       
				drawImage(img, copyAndResize(tir, "tir-small",1,10), i + 20, traj);
				traj = traj - 50;
			    }
			if (my_check.hit)
			    {
				if (my_tab[my_check.numb].alien == Integer.parseInt(save[j][l]))
				    {
					println("VOUS AVEZ PERDU UNE VIE");
					setColor(img,RGBColor.BLACK);
					fillRect(img, my_check.new_coord[0], my_check.new_coord[1], 35 , 35);
					lives = lives - 1;
					my_tab[my_check.numb].alien = 0;
				    }
				else
				    {
					my_tab[my_check.numb].alien = 0;
					setColor(img,RGBColor.BLACK);
					fillRect(img, my_check.new_coord[0], my_check.new_coord[1], 35 , 35);
					if (check_tab())
					    {
						println("BRAVO NIVEAU REUSSI !");
						l = l + 2;
						j = j + 2;
					    }
				    }
			    }
		    }
	    }
	if (c == 'x')
	    {
		count = count + 1;
		if (count == 3)
		    {
			count = 0;
			println("SPACE");
			Check my_check = new Check();
			my_check.hit = false;
			my_check = check_coord(i, my_check);
			int traj = 300;
			while(traj != 0)
			    {
		       
				drawImage(img, copyAndResize(tir, "tir-small",1,10), i + 20, traj);
				
				traj = traj - 50;
			    }
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
					println("WRONG TARGET ! -1");
					lives = lives - 1;
					setColor(img,RGBColor.BLACK);
					fillRect(img, my_check.new_coord[0], my_check.new_coord[1], 35 , 35);
				    }
			    }
		    }
	    }
    }

    void		check_win() 
    {
	// for (int i = 0; i != length(my_answ) ; ++i)
	//     {
	// 	if (my_tab[my_answ[i]].alien != 0)
	// 	    return ;
	//     }
	println("BRAVO NIVEAU REUSSI !");
	j = j + 2;
	empty_struct();
	println("ICNEUICNAUCBNU9vea");
	finished = false;
	my_game();
    }

    void		empty_struct()
    {
	int[] vide = new int[length(my_answ)];
	my_answ = vide;	
	Alien[]	       empty = new Alien[50];
	my_tab = empty;
    }

	boolean    		check_tab() 
	{
	    for (int i = 0; i != 50; ++i)
		{
		    if (my_tab[i].alien != Integer.parseInt(save[j][l]))
			return false;
		}
	    return true;
	}
 
	Check	       	check_coord(int i, Check if_hit) 
	{
	    int col = 0;
	    for (int k = 49; k != 0 ; --k) 
		{
		    if (i >= my_tab[k].coord[0] && i <= (my_tab[k].coord[0] + 10))
			{
			    for (; my_tab[k].alien == 0; k = k - 10);
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

	void		mouseHasMoved(int x, int y) {

	}
	void		mouseHasChanged(String name, int x, int y, int button, String event){
	    
	}
	void		mouseChanged(String name, int x, int y, int button, String event) {
	}
    }
