FILE=hw5.out

run: compile
	./$(FILE)

compile:
	gcc -std=c99 -o $(FILE) hw5.c

clean:
	rm $(FILE)

dist:
	dir=`basename $$PWD`; cd ..; tar cvf $$dir.tar ./$$dir; gzip $$dir.tar
	dir=`basename $$PWD`; ls -l ../$$dir.tar.gz