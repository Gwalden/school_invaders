import extensions.*;

class Main extends Program 
{
    CSVFile file = loadCSV("my_questions_math.csv");
    String[][] save = new String[rowCount(file)][columnCount(file)];
    Image	vaisseau = newImage("Vaisseau","vaisseau.png");
    Image	img = newImage(600, 300);
    Image	ship = newImage(200,200);
    int		i = 250;
    Image	tir = newImage("Tir","tir.png");
   
    void	algorithm()
    {
	//	my_menu();
	my_game();
	//enableKeyTypedInConsole(true);
    }

    String[][] my_pars() {	
	for (int i = 0; i < rowCount(file) ; ++i) {
	    for (int j = 0 ; j < columnCount(file,i); ++j) {
		save[i][j] = getCell(file,i,j);
	    }
	}
	return save;
    }

    int[][]	my_alien() {
	int num = 0;
	int rand =  (int)random() * 5;
	int[] err = new int[5];
	for (int i = 0; i != 5 ; ++i) {
	    if (i != rand) {
		err[i] = my_err();
		println(err[i]);
	    }
	    else
		err[i] = rand;
	}
	int[][] alien = new int[5][10];
	for (int k = 0; k != 10 ; ++k) {
	    for (int i = 0; i != i; ++i) {
		if (i == rand)
		    alien[k][i] = Integer.parseInt(save[1][1]);
		else {
		    alien[k][i] = err[num];
		    num = num + 1;
		}
	    }
	}
	return alien;
    }

    int		my_err() {
	return ((int) (random() * 5));
	
    }

    int		check_tab(int[] tab) {
	int i = length(tab) - 1;
	for (; i != 0 || tab[i] == 0; --i);
	if (i == 0 && tab[i] != 0)
	    return (1);
	else 
	    return (-1);
    }

    int 	my_menu()
    {
	Image img = newImage(600, 400);
	fill(img, RGBColor.BLUEVIOLET);
	setColor(img, RGBColor.WHITE);
	addButton(img, "Maths");
	addButton(img, "Francais");
	addButton(img, "Histoire");
	show(img);
	while (true);
    }

    void	my_game() 
    {	
	my_pars();
      	copyAndResize(vaisseau, "vaisseau-small",50,50);
	drawImage(img, copyAndResize(vaisseau, "vaisseau-small",50,50), 300, 250);
	my_aff(my_alien());
	//my_answers();
	show(img);
	while(true);
    }

    void	my_aff(int[][] alien) {
	int a = 10;
	int b = 20; 
	for (int i = 0; i != 5 ; ++i) {
	    for (int k = 0; k != 10; ++k) {
		setColor(img, RGBColor.WHITE);
		fillRect(img, a, b, 35 , 35);
		setColor(img, RGBColor.BLACK);
		drawString(img, toString(alien[i][k]),(a + 10) ,b + 20);
		a +=60;	    
	    }
	    a = 10;
	    b += 40;
	}
    }

    void	my_answers()
    {
	int	i;
	
	int[] my_err = my_error();
	
	setColor(img, RGBColor.WHITE);
	fillRect(img, 50, 10, 100, 70);
	setColor(img, RGBColor.BLACK);
	drawString(img, save[1][1], 60, 30);
	setColor(img, RGBColor.WHITE);
	fillRect(img, 150 + 100, 10, 100, 70);
	setColor(img, RGBColor.BLACK);
	drawString(img,toString(my_err[1]), 260, 30);
	setColor(img, RGBColor.WHITE);
	fillRect(img, 320+100 , 10, 100, 70);
	setColor(img, RGBColor.BLACK);
	drawString(img, toString(my_err[2]), 430, 30);
    }

    String toString(int err) 
    {
	String res = "";
	res = res + err;
	return res;
    }

    void keyChanged(char c, String event) 
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
		int traj = 300;
		while(traj != 0)
		    {
		       
			drawImage(img, copyAndResize(tir, "tir-small",1,1), i + 10, traj);
			setColor(img, RGBColor.WHITE);
			fillRect(img, i, i, 1, 1);
			if ((i > 50 && i < 120)) 
			    {
				setColor(img, RGBColor.BLUEVIOLET);
				fillRect(img, 50, 10, 100, 70);
			    }
			traj = traj - 50;
		    }
	    }
    }

    int[] my_error() {	
	int[] err = new int[3];
	err[0] = Integer.parseInt(save[1][1]) + (int) (random() * 10);
	err[1] = Integer.parseInt(save[1][1]) + (int) (random() * 10);
	err[2] = Integer.parseInt(save[1][1]) + (int) (random() * 10);
	return err;
    }

    void mouseHasMoved(int x, int y) {

    }
    void mouseHasChanged(String name, int x, int y, int button, String event){

    }
    void mouseChanged(String name, int x, int y, int button, String event) {
    }
}
