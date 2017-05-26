

void setup()
{
    size( 640, 480 );
    smooth();
    


    strokeCap( SQUARE );
}

void draw()
{
    background( 255 );
    fill( 128, 0, 0 );
    textAlign( LEFT );
    text( "Hello, I'm some text", 20, 50 );
    
    text("I'm a line of text.", 20, 80);
    
    stroke( 128, 0, 0 );
    strokeWeight( 1 );
    line( 20, 94, 620, 94 );
    line( 20, 96, 620, 96 );
    
    fill( 0 );
    text( "The quick brown fox jumps over the lazy dog. (24)", 20, 130 );
    text( "Pack my box with five dozen liquor jugs. (18)", 20, 154 );
    text( "Blowzy red vixens fight for a quick jump. (12)", 20, 172 );

    stroke( 128 );
    strokeWeight( 3 );
    line( 20, 186, 620, 186 );

    fill( 245 );
    stroke( 128 );
    strokeWeight( 1 );
    rect( 20, 192, 600, 110 );
    
    stroke( 128 );
    line( width/2, 192, width/2, 298 );
    
    fill( 128 );
    stroke( 128 );
    triangle( width/2-4, 192, width/2+4, 192, width/2, 196 );
    triangle( width/2-4, 302, width/2+4, 302, width/2, 298 );

    fill( 0 );
    textAlign( LEFT );
    text( "since I left you", width/2, 225 );
    textAlign( CENTER );
    text( "symmetry is boring", width/2, 252 );
    textAlign( RIGHT );
    text( "flush to the right", width/2, 280 );
    
    // text over multiple lines with different leadings
    textAlign( LEFT );
    String multiline = "In typography, leading refers\n";
    multiline += "to the distance between the\n";
    multiline += "baselines of successive lines\n";
    multiline += "of type.";

    // comes from the Processing source code
    float standardLeading = ( textAscent() + textDescent() ) * 1.275f;
    
    textLeading( standardLeading );
    text( multiline, 20, 340 );
    textLeading( standardLeading * 0.75 );
    text( multiline, 220, 340 );
    textLeading( standardLeading * 1.5 );
    text( multiline, 420, 340 );


    String s = "textWidth";
    float w = textWidth( s );
    fill( 128, 0, 0 );
    text( s, 20, 450 );
    noStroke();
    rect( 20, 455, w, 8 );

}
