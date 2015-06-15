/*
 * Menu 
 *
 * Alban Tartar
 * June 2015
 */

import extensions.*;

class Menu extends Program{
    TransparentImage img = newTransparentImage(600,300);
    TransparentImage logo = newTransparentImage("logo","logonew.png");
    TransparentImage wall = newTransparentImage("wall","wallmenu.jpg");
    TransparentImage fr = newTransparentImage("fr","Boutonfr.png");
    TransparentImage Math = newTransparentImage("Math","Math.png");
    TransparentImage Hist = newTransparentImage("Hist","Histoire.png");
    void algorithm(){
	menu();
    }
    void menu(){
	int i = 100;
	int j = 50;
	drawImage(img,wall,0,0);
	drawImage(img,copyAndResize(logo, "logo",120,100),10,10);
	add_button("Math",100,120,i,j);
	add_button("Francais",400,120,i,j);
	add_button("Histoire",250,200,i,j);
	show(img);
	while(true);
    }
    void add_button(String str,int x, int y, int i, int j){
	if(equals(str,"Math") == true){
	    addZone(img,str,x,y,i,j);
	    drawImage(img,Math,x,y);
	}
	if(equals(str,"Francais") == true){
	    addZone(img,str,x,y,i,j);
	    drawImage(img,fr,x,y);
	}
	if(equals(str,"Histoire") == true){
	    addZone(img,str,x,y,i,j);
	    drawImage(img,Hist,x,y);
	}
    }
}