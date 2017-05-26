TARGET=cache.out

run: compile
	./$(TARGET)

compile:
	gcc -std=c99 -o $(TARGET) hw6.c

clean:
	rm $(TARGET)

dist:
	dir=`basename $$PWD`; cd ..; tar cvf $$dir.tar ./$$dir; gzip $$dir.tar
	dir=`basename $$PWD`; ls -l ../$$dir.tar.gz