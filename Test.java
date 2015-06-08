



class Test extends Program 
{


    class Alien 
    {
	int[] alien = new int[10];
	int[] coord = new int[2];
    }
 
    void algorithm() 
    {
	Alien[] my_tab = new Alien[5];
	for (int i=0; i<my_tab.length;++i)
	    my_tab[i] = new Alien();
	my_tab[0].alien[0] = 3;
	println(my_tab[0].alien[0] = 3);
    }
}