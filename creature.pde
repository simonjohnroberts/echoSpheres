 class Creature{
  color c;
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
  
  Creature(color _c, float _diameter, float _xpos, float _ypos){
    c = _c;
    diameter = _diameter;
    xpos = _xpos;
    ypos = _ypos;
    xspeed = random( - 1,1); //MOVE
    yspeed = random( - 1,1); //MOVE 
  }
  
  void move() {//MOVE
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
  void start(){
    on = true; // MAKE SPHERES VISIBLE
  }
 
  // GET POSITION WHEN OF THE MOUSE WHEN CLICKED AND USE TO POSITION SPHERES
  void postionWhenMouseClicked(float _currentMouseX, float _currentMouseY){
    xpos = _currentMouseX;
    ypos = _currentMouseY;
  }
  
  //void display(float _mouseX, float _mouseY){  
  void display(){
      if (on == true){
      noStroke();
      smooth();
      fill(c);
      ellipseMode(CENTER);
      ellipse(xpos, ypos, diameter, diameter);
    }
  }
      
  void hitTest() {
    // IF OBJECT IS TAPPED
    if (mousePressed == true) {
      // CHECK FINGER IS OVER OBJECT
      if(sq(mouseX-this.xpos)+sq(mouseY-this.ypos)<=sq(this.diameter/2)) {
        // MOVE OBJECT
        this.xpos = mouseX;
        this.ypos = mouseY; 
        // WE WANT ONE CLICK TO BEGIN EXPAND
        if (this.diameter < maxSizeOfObject && growing == true){
          float growingSpeed = 0.01;
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
            player3.setVolume(0.3, 0.3);  
            
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
  
  
