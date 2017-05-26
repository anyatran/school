"""

    renamer.py
    
    Renaming script that has a few good functions in it 
    Teaches us about classes and how to use them
    
    NOTE: this has been updated from the recording to address 
    issues with name conflicts

"""

import maya.cmds as cmds


class Renamer():
    
    def __init__(self):
    
        self.current_index = -1
        self.matches = []
        self.search_string = ""
        
        # declare the name of our window object
        window_name = "renamerWindow"
        window_title = "Renamer"
        # check if that object already exists, and delete it if it does 
        if cmds.window(window_name, q=True, exists=True):
            cmds.deleteUI(window_name)
            
        # create a new window object
        my_window = cmds.window(window_name, w=10, title = window_title)
        
        # the adj flag makes the buttons stretch to the width of the column
        main_layout = cmds.columnLayout(parent=my_window, adj=True)
        
        

        self.find_box = cmds.textFieldButtonGrp( label='Find', adj=2,text='', buttonLabel='Select', bc = self.find , cw=[1,50])
        self.replace_box = cmds.textFieldButtonGrp( label='Replace', adj=2, text='', buttonLabel='Replace', bc=self.replace , cw=[1,50])
        self.prefix_box = cmds.textFieldButtonGrp( label='Prefix', adj=2, text='', buttonLabel='Add', bc=self.add_prefix , cw=[1,50])
        self.suffix_box = cmds.textFieldButtonGrp( label='Suffix', adj=2,text='', buttonLabel='Add', bc=self.add_suffix , cw=[1,50])

        # show that window
        cmds.showWindow(my_window)

    def _get_matches(self):
        find_string = cmds.textFieldButtonGrp(self.find_box, q=True, text=True)
        matches = cmds.ls("*" + find_string  + "*", type="transform")

        return matches
    
    def replace(self):
        find_string = cmds.textFieldButtonGrp(self.find_box, q=True, text=True)
        replace_string = cmds.textFieldButtonGrp(self.replace_box, q=True, text=True)
        # changed the matches to be based on selection
        matches = cmds.ls(sl=True)
        
        for match in matches:
            # we do the split here based on | to only get the short name of the object 
            # the split will break up the name into an array, and the -1 will give us 
            # the last item of that array, so if something is name mygroup|sphere, 
            # it will return sphere 
            
            new_name = match.split("|")[-1].replace(find_string, replace_string)
            print "Renamed", match, ">", new_name
            cmds.rename(match, new_name)
            
    def add_prefix(self):
        prefix = cmds.textFieldButtonGrp(self.prefix_box, q=True, text=True)
        matches = cmds.ls(sl=True)
        
        for match in matches:
            new_name = prefix +  match.split("|")[-1]
            print "Renamed", match, ">", new_name
            cmds.rename(match, new_name)                        
            
    def add_suffix(self):
        suffix = cmds.textFieldButtonGrp(self.suffix_box, q=True, text=True)
        matches = cmds.ls(sl=True)
        
        for match in matches:
            new_name = match.split("|")[-1] + suffix
            print "Renamed", match, ">", new_name
            cmds.rename(match, new_name)                        
            
    def find(self):
        matches = self._get_matches()
        cmds.select(matches, r=True)
        
Renamer()