from os import listdir, rename
from os.path import isfile, join

onlyfiles = [f for f in listdir(".") if isfile(join(".", f))]

for thefile in onlyfiles :
    if thefile != "rename.py" :
        rename(thefile, thefile.replace("glowcrete_", "redcrete_"))
