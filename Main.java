import extensions.*;

class Main extends Program 
{
    Image	vaisseau = newImage("Vaisseau","vaisseau.png");
    Image	img = newImage(600, 300);
    Image	save = img;
    Image	ship = newImage(200,200);
    int		i = 250;
    Image	tir = newImage("Tir","tir.png");
    void	algorithm()

    {
	my_menu();
	my_game();
	//enableKeyTypedInConsole(true);
    }
    void	my_menu()
    {

    }
    void	my_game() 
    {	
        setColor(save, RGBColor.BLUE);
	copyAndResize(vaisseau, "vaisseau-small",50,50);
	drawImage(img, copyAndResize(vaisseau, "vaisseau-small",50,50), 300, 250);
	my_answers();
	show(img);
	while(true);
    }

    void	my_answers()
    {
	int	i;
	
	setColor(img, RGBColor.WHITE);
	fillRect(img, 50, 10, 100, 70);
	setColor(img, RGBColor.BLACK);
	drawString(img, "reponse une", 60, 30);
	setColor(img, RGBColor.WHITE);
	fillRect(img, 150 + 100, 10, 100, 70);
	setColor(img, RGBColor.BLACK);
	drawString(img, "reponse deux", 260, 30);
	setColor(img, RGBColor.WHITE);
	fillRect(img, 320+100 , 10, 100, 70);
	setColor(img, RGBColor.BLACK);
	drawString(img, "reponse trois", 430, 30);
    }

    void keyChanged(char c, String event) 
    {
	if (c == 'q')
	    {
		println("GAUCHE");
		setColor(img, RGBColor.BLACK);
		drawRect(img, i + 10, 250, 50, 50);
		fillRect(img, i + 10, 250, 50, 50);
		drawImage(img, copyAndResize(vaisseau, "vaisseau-small",50,50), i, 250);
		i -= 10;
	    }
	if (c == 'd')
	    {
		println("DROITE");
		setColor(img, RGBColor.BLACK);
		drawRect(img, i - 10, 250, 50, 50);
		fillRect(img, i - 10, 250, 50, 50);
		drawImage(img, copyAndResize(vaisseau, "vaisseau-small",50,50), i, 250);
		i += 10;
	    }
	if (c == 's')
	    {
		println("hide");
		setColor(img, RGBColor.BLUE);
		drawRect(img, 300, 250, 50, 50);
		fillRect(img, 300, 250, 50, 50);
	    }
	if (c == ' ') 
	    {
		int traj = 295;
		while(traj != 0)
		    {
			drawImage(img, copyAndResize(tir, "tir-small",5,4), i , traj);
						traj = traj - 1;
		    }

		println("SPACE");
	    }
    } 
}
