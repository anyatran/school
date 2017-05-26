void setup()
{
    size( 333, 333, P3D );
}

void draw()
{
    background( 0 );
    
    translate( width/2, height/2, 0 );
    
    pushMatrix();
    rotateY( radians( frameCount ) );
    fill( 255 );
    noStroke();
    box( 100 );
    popMatrix();
    
    pushMatrix();
    rotateY( radians( frameCount ) );
    rotateX( radians( frameCount ) );
    beginShape();
    fill( 255, 0, 255 );
    vertex( -200, -200 );
    fill( 0, 255, 0 );
    vertex(  200, -200 );
    fill( 0, 0, 255 );
    vertex(  200,  200 );
    fill( 255, 255, 0 );
    vertex( -200,  200 );
    endShape( CLOSE );
    popMatrix();
}
