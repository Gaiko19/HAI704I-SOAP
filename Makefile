all: compile

compile:
	(cd finder ; javac *.java)

run:
	(cd finder ; java Finder)

clean:
	(cd finder ; rm *.class)
