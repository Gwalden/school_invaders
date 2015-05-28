import extensions.*;

class My_way extends Program {

    boolean finished = false;
    int[][] game = new int[20][20];
    int k = 19;
    int l = 10;
 
    void algorithm() 
    {
	clearScreen();
	game[k][l] = 1;
	my_try();
	enableKeyTypedInConsole(true);
	while (!finished) {
	    //
	}
    }
    
    void my_try() {
	for (int i = 0; i < 20 ; ++i) {
	    for (int j = 0; j < 20 ; ++j) {
		if (game[i][j] == 0)
		    print("-");
		else
		    print("*");
	    }
	    println();
	}
    }
    void keyTypedInConsole(char key) {
	cursor(k,l);
	print("-");
	switch (key)
	    {
	    case 'q':
		l = l - 1;
		break;
	    case 'd':
		l = l + 1;
		break;
	    case 'w':
		clearScreen();
		cursor(0,0);
		System.exit(1);
		break;
	    case ' ':
		print("SPACE");
	    }
	cursor(k,l);
	hide();
	print("*");
	delay(1);
	//	println(key);
    }
}
