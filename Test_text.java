import extensions.*;

class Test_text extends Program {

    boolean finished = false;
      int[][] game = new int[20][30];
    int i = 19;
    int j = 15;
 
    void algorithm() 
    {
	game[i-1][j] = 1;
	game[i][j+1] = 1;
	game[i][j-1] = 1;
	enableKeyTypedInConsole(True);
	while (!finished)
	    my_try();
    }
    

    void my_try() {
	int k;
	for (int i = 0; i < 20 ; ++i) {
	    for (int j = 0; j < 30 ; ++j) {
		if (game[i][j] == 0)
		    print("_");
		else
		    print("|");
	    }
	    println();
	}
    }
    void keyTypedInConsole(int key) {
	switch (key) {
	case ANSI_UP:
	    clearScreen();
	    game[i - 1][j] = 0;
	    game[i][j + 1] = 0;
	    game[i][j - 1] = 0;
	    i = i - 1;
	    game[i - 1][j] = 1;
	    game[i][j + 1] = 1;
	    game[i][j - 1] = 1;
	    break;
	case ANSI_DOWN:
	    clearScreen();
	    game[i - 1][j] = 0;
	    game[i][j + 1] = 0;
	    game[i][j - 1] = 0;
	    i = i + 1;
	    game[i - 1][j] = 1;
	    game[i][j + 1] = 1;
	    game[i][j - 1] = 1;
	}
    }
}