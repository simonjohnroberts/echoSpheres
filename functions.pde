/*  
  
  VARIOUS FUNCTIONS LIVE HERE

*/

// ADD OBJECTS FUNCTION
void mousePressed(){
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
  player.setVolume(0.3, 0.3);  
}

// DISPLAY INTRO IMAGE FUNCTION
void displayIntroImg(){
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


