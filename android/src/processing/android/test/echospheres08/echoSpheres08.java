package processing.android.test.echospheres08;

import processing.core.*; 
import processing.xml.*; 

import android.media.*; 
import apwidgets.*; 

import apwidgets.*; 

import android.view.MotionEvent; 
import android.view.KeyEvent; 
import android.graphics.Bitmap; 
import java.io.*; 
import java.util.*; 

public class echoSpheres08 extends PApplet {

// Library imports



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
public void setup(){
  //size(250, 400); // LEAVE EMPTY FOR ANDROID
  
  // SOUND - COMMENT OUT FOR NON ANDROID TESTING
/**/
  player = new PMediaPlayer(this); 
  player.setMediaFile("test.mp3");
  
  player2 = new PMediaPlayer(this); 
  player2.setMediaFile("intro.mp3");
  player2.start(); 
  player2.setLooping(true); 
  player2.setVolume(0.2f, 0.2f);  
  
  player3 = new PMediaPlayer(this); 
  player3.setMediaFile("arpeggio.mp3");
  
      
  for (int i = 0; i < creatureArr.length; i++){
    creatureArr[i] = new Creature (color(random(0,100),100,50,60),50,random(width), random(height));
  }
}
/* ------------------------ SET UP FUNCTION : END ------------------------ */

/* ------------------------ DRAW FUNCTION : BEGIN ------------------------ */
public void draw(){
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
  //onClick(mouseX,mouseY);
}
/* ------------------------ DRAW FUNCTION : END ------------------------ */


 class Creature{
  int c;
  float diameter;
  float xpos;
  float ypos;
  
  int maxSizeOfObject = 250;
  int minSizeOfObject = 50;
  float currentSize;
  
  float xspeed; // MOVE
  float yspeed; // MOVE
  
  float sphereStartXPos; // START POS
  float sphereStartYPos; // START POS
  
  // BOOLEANS FOR MOUSE PRESS ON AND OFF.THEY ARE NOT CURRENTLY USED
  boolean pressOn;
  boolean pressOff;
  
  // BOOLEANS FOR INCREASING AND DECREASING STATE
  boolean growing = true;
  boolean reset = false;
  
  // BOOLEANS FOR COLLISIONS
  boolean touching = false; 
  
  // BOOLEAN FOR DISPLAY
  boolean on = false;
  
  Creature(int _c, float _diameter, float _xpos, float _ypos){
    c = _c;
    diameter = _diameter;
    xpos = _xpos;
    ypos = _ypos;
    xspeed = random( - 1,1); //MOVE
    yspeed = random( - 1,1); //MOVE 
  }
  
  public void move() {//MOVE
    xpos += xspeed; // Increment x
    ypos += yspeed; // Increment y
    
    // Check horizontal edges
    if (xpos > width || xpos < 0) {
      xspeed *= - 1;
    }
    //Check vertical edges
    if (ypos > height || ypos < 0) {
      yspeed *= - 1;
    }
  }
  
  // FIRE WHEN FIRST CLICKED
  public void start(){
    on = true; // MAKE SPHERES VISIBLE
  }
 
  // GET POSITION WHEN OF THE MOUSE WHEN CLICKED AND USE TO POSITION SPHERES
  public void postionWhenMouseClicked(float _currentMouseX, float _currentMouseY){
    xpos = _currentMouseX;
    ypos = _currentMouseY;
  }
  
  //void display(float _mouseX, float _mouseY){  
  public void display(){
      if (on == true){
      noStroke();
      smooth();
      fill(c);
      ellipseMode(CENTER);
      ellipse(xpos, ypos, diameter, diameter);
    }
  }
      
  public void hitTest() {
    // IF OBJECT IS TAPPED
    if (mousePressed == true) {
      // CHECK FINGER IS OVER OBJECT
      if(sq(mouseX-this.xpos)+sq(mouseY-this.ypos)<=sq(this.diameter/2)) {
        // MOVE OBJECT
        this.xpos = mouseX;
        this.ypos = mouseY; 
        // WE WANT ONE CLICK TO BEGIN EXPAND
        if (this.diameter < maxSizeOfObject && growing == true){
          float growingSpeed = 0.01f;
          growingSpeed ++;
          this.diameter *= growingSpeed;
          currentSize = this.diameter; 
          //println(" max:" + maxSizeOfObject + " current size:" + currentSize);
          }
          if (this.diameter >= maxSizeOfObject){ // CHECK IF WE HAVE REACHED THE MAXIMUM SIZE
            growing = false;
          }
          if (growing == false){ // REDUCE SIZE
            this.diameter --;
            
            // PLAY ARPEGGIO SOUND ON SHRINK  
            player3.start(); 
            player3.setLooping(false); 
            player3.setVolume(0.3f, 0.3f);  
            
            if (this.diameter <= minSizeOfObject){
              growing = true;
            }
          }
        }
      }
      // IF OBJECT IS JUST HOVERED OVER WITHOUT CLICK
      if(sq(mouseX-this.xpos)+sq(mouseY-this.ypos)<=sq(this.diameter/2)) {
       // SOUND PLAYS HERE 
      }
    }
  }
  
  
/*  
  
  VARIOUS FUNCTIONS LIVE HERE

*/

// ADD OBJECTS FUNCTION
public void mousePressed(){
   // CAPTURE MOUSE POSITION ON CLICK. THIS IS WHAT WE NEED TO USE FOR SETTING THE START POSTION OF EACH INCREMENTED OBJ
   currentMouseX = mouseX;
   currentMouseY = mouseY; 
   
   creatureArr[currentObj].postionWhenMouseClicked(currentMouseX, currentMouseY);
   creatureArr[currentObj].start();
    currentObj++;
    if (currentObj >=numOfSpheres){
      currentObj = 0;
    }
  
  // PLAY SOUND ON PRESS  
  player.start(); 
  player.setLooping(false); 
  player.setVolume(0.3f, 0.3f);  
}

// DISPLAY INTRO IMAGE FUNCTION
public void displayIntroImg(){
    if(displayIntro == true){
        PImage b;
        // Images must be in the "data" directory to load correctly
        b = loadImage("beginImg.jpg");
        image(b, width/2-160, height/2-100);
        tint(255, 0);
        tint(255, counter);
        //FADE IN TEXT
        /*if (counter < 255){
          tint(255, counter);
        }else{
          noTint();
        } */
      }
}

//RELEASE THE MEDIAPLAYER ON APP EXIT
/**/
public void onDestroy() {
  super.onDestroy(); //call onDestroy on super class
  if(player!=null) { //must be checked because or else crash when return from landscape mode
    player.release(); //release the player

  }
}


/*

THIS FILE HOLDS VARIATIONS OF THE hitTest METHOD

*/

// EXPAND OBJECT - NO CLICK
/*
   if(sq(mouseX-this.xpos)+sq(mouseY-this.ypos)<=sq(this.diameter/2)) {
     println("we are over the object");
     this.diameter ++;
     
     if (this.diameter < maxSizeOfObject){ 
     this.diameter ++;
      }else if (this.diameter == maxSizeOfObject){
        println("We have increased to maximum size");  
        if (mousePressed == true){
          println("size exceeded");
          this.diameter --;
      }
    }
   }
*/

 /* void touchTest(){ //COLLISION
    if (!touching) && ()  {
       code to change direction
       touching = true;
      } else if (! test for intersection ) {
       touching = false;
      }
   }*/

/*

TO DO
 
  Get some default sound playing using apwidgets library - Done (we sort of, phone bricking aside)
  Use apwidgets to create an info screen
  
  Should we have a blank screen that then creates a 'sphere' on touch. This then floats about, emitting tones. The user can tap again to create more patterns in sound.
  Moving down creates alterations to sound as does moving up. 
  
*/


}
