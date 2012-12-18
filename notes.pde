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

