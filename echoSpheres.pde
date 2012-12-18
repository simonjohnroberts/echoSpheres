// Library imports
import android.media.*;
import apwidgets.*;

//SET NUM OF CREATURES 
int numOfSpheres = 20;
Creature[] creatureArr = new Creature[numOfSpheres];
int objectCounter = 0; // IS THIS BEING USED?
int currentObj = 0;

//DISPLAY BOOLEAN
boolean displayObjects = false;
boolean displayIntro = true;

//CURRENT MOUSE POS
float currentMouseX;
float currentMouseY;

//FRAME COUNT
int counter = 20;

PMediaPlayer player, player2, player3;

/* ------------------------ SET UP FUNCTION: BEGIN ----------------------- */
void setup(){
  //size(250, 400); // LEAVE EMPTY FOR ANDROID
  
  // SOUND - COMMENT OUT FOR NON ANDROID TESTING
/**/
  player = new PMediaPlayer(this); 
  player.setMediaFile("test.mp3");
  
  player2 = new PMediaPlayer(this); 
  player2.setMediaFile("intro.mp3");
  player2.start(); 
  player2.setLooping(true); 
  player2.setVolume(0.2, 0.2);  
  
  player3 = new PMediaPlayer(this); 
  player3.setMediaFile("arpeggio.mp3");
  
      
  for (int i = 0; i < creatureArr.length; i++){
    creatureArr[i] = new Creature (color(random(0,100),100,50,60),50,random(width), random(height));
  }
}
/* ------------------------ SET UP FUNCTION : END ------------------------ */

/* ------------------------ DRAW FUNCTION : BEGIN ------------------------ */
void draw(){
  //COUNTER FOR INTRO TEXT FADE IN
  if (counter < 255){
  counter ++;
  }else{
    counter = 255;
  }
  background(255);  
    if (mousePressed == true) { //HIDE INTO IMAGE WHEN PRESSED
      displayIntro = false;
    }
  displayIntroImg(); //DISPLAY INTRO IMAGE

  for(int i = 0; i < creatureArr.length; i++){ 
    creatureArr[i].hitTest();
    creatureArr[i].display();
    creatureArr[i].move();
  }
}
/* ------------------------ DRAW FUNCTION : END ------------------------ */


