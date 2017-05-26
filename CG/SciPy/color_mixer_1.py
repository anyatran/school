"""
Program name: color_mixer_1.py
Objective: Mixes numerical RGB (red-green-blue) integers obtained from
 sliders and provides the hexadecimal color code.
Provide a separately draggable color swatch for matching purposes.

Keywords: canvas, multiple sliders, scale, input assignment, color mixng
          validation, Toplevel widget.
============================================================================79

Explanation:
Here three sliders are used to set red, green and blue colors.
Reasonably precise matching by comparison with an existing object is possible,
by positioning the separate swatch window immediately adjacent the existing
color patch.
The chosen color is seen and the hex value can be noted for use in code.
So you mix red, green and blue in any combination to get a desired colour and
the exact hex code "xrrggbb" is given -  a mixing color picker.

Author:          Mike Ohlson de Fine

"""
# color_mixer_1.py
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
from Tkinter import *
root = Tk()
root.title("Color mixer in Hex and Integer")
canvas_1 = Canvas(root, width=320, height=400, background="white")
canvas_1.grid(row=0, column=1)

slide_value_red   = IntVar()
slide_value_green = IntVar()
slide_value_blue  = IntVar()
combined_hex = '000000'
red_hex   = '00'
green_hex = '00'
blue_hex  = '00'
red_int = 0
green_int = 0
blue_int = 0
red_text = 0
green_text = 0
blue_text = 0
combined_hex = "#000"
fnt = 'Bookantiqua 14 bold'

# red display
canvas_1.create_rectangle( 20,  30, 80, 110)
canvas_1.create_text(20,10,  text="Red", width=60, font=fnt,\
                     anchor=NW, fill='red' )

# green display
canvas_1.create_rectangle( 100,  30, 160, 110)
canvas_1.create_text(100,10,  text="Green", width=60, font=fnt,\
                     anchor=NW, fill='green' )

# blue display
canvas_1.create_rectangle( 180,  30, 240, 110)
canvas_1.create_text(180,10,  text="Blue", width=60, font=fnt,\
                     anchor=NW, fill='blue' )

# Labels
canvas_1.create_text(250,30, text="integer 256", width=60, anchor=NW )
canvas_1.create_text(250,60, text="% of 256", width=60, anchor=NW )
canvas_1.create_text(250,86, text="hex", width=60, anchor=NW )

# combined display
fnt = 'Bookantiqua 12 bold'
canvas_1.create_rectangle( 20, 170, 220, 220 )
canvas_1.create_text(20,130, text="Combined colors", width=200, font=fnt,\
                     anchor=NW, fill='black' )
canvas_1.create_text(20,150, text="Hexadecimal red-green-blue", width=300,
                      font=fnt,anchor=NW, fill='black' )

# callback functions to service slider changes
#=============================================
def codeShorten(slide_value, x0, y0, width, height, kula):
    global combined_hex, red_int, green_int, blue_int
    fnt = 'Bookantiqua 12 bold'
    slide_txt = str(slide_value)
    slide_int = int(slide_value)
    slide_hex = hex(slide_int)
    slide_percent = slide_int * 100 / 256
    canvas_1.create_rectangle(x0, y0, x0 + width, y0 + height, fill='white')
    canvas_1.create_text(x0+6, y0+6,   text=slide_txt, width=width, font=fnt,\
                         anchor=NW, fill=kula )
    canvas_1.create_text(x0+6, y0+28, text=slide_percent, width=width,\
                         font=fnt, anchor=NW, fill=kula)
    canvas_1.create_text(x0+6, y0+50, text=slide_hex, width=width,\
                         font=fnt, anchor=NW, fill=kula)
    return slide_int

def callback_red(*args):
   global red_int
   kula = "red"
   jimmy = str(slide_value_red.get())
   red_int =  codeShorten(jimmy, 20, 30, 60, 80, kula)
   update_display(red_int, green_int, blue_int)

def callback_green(*args):
   global green_int
   kula = "darkgreen"
   jimmy = str(slide_value_green.get())
   green_int =  codeShorten(jimmy, 100, 30, 60, 80, kula)
   update_display(red_int, green_int, blue_int)

def callback_blue(*args):
   global blue_int
   kula = "blue"
   jimmy = str(slide_value_blue.get())
   blue_int =  codeShorten(jimmy, 180, 30, 60, 80, kula)
   update_display(red_int, green_int, blue_int)

def update_display(red_int, green_int, blue_int):
    combined_int = (red_int, green_int, blue_int)
    combined_hex = '#%02x%02x%02x' % combined_int
    canvas_1.create_rectangle( 20,  170, 220 , 220, fill='white')
    canvas_1.create_text(26, 170, text=combined_hex, width=200,\
                         anchor=NW, font='Bookantiqua 16 bold')
    canvas_1.create_rectangle( 0,  400, 300, 230, fill= combined_hex)
    swatch.config(bg =  combined_hex)    # Update the background color of swatch

slide_value_red.trace_variable("w", callback_red)
slide_value_green.trace_variable("w", callback_green)
slide_value_blue.trace_variable("w", callback_blue)

# The color swatch that you cna drag around the screen to match colors.
swatch = Toplevel(master=None, bg= combined_hex, bd = 0, height=50, width=50)

slider_red = Scale(root,
                       length = 400,
                       fg = 'red',
                       activebackground = "tomato",
                       background = "grey",
                       troughcolor = "red", 
                       label = "RED",
                       from_ = 0,
                       to = 255,
                       resolution = 1,
                       variable = slide_value_red,
                       orient   = 'vertical')

slider_red.grid(row=0, column=2)

slider_green =Scale(root,
                       length = 400,
                       fg = 'dark green',
                       activebackground = "green yellow",
                       background = "grey",
                       troughcolor = "green", 
                       label = "GREEN",   
                       from_ = 0,
                       to = 255,
                       resolution = 1,
                       variable = slide_value_green,
                       orient   = 'vertical')

slider_green.grid(row=0, column=3)

slider_blue = Scale(root,
                       length = 400,
                       fg = 'blue',
                       activebackground = "turquoise",
                       background = "grey",
                       troughcolor = "blue", 
                       label = "BLUE",   
                       from_ = 0,
                       to = 255,
                       resolution = 1,
                       variable = slide_value_blue,
                       orient   = 'vertical')

slider_blue.grid(row=0, column=4)

root.mainloop()
