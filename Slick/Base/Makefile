OUTPUT=bin
INPUT=src
JC=javac
RM=rm
FLAGS= -g -cp libs/slick.jar:bin
#FILE=$(wildcard *.java $(INPUT)/*.java)
FILE=src/SimpleGame.java src/Main.java
CLASS=$(patsubst %.java,%.class,$(FILE))

vpath %.class $(OUTPUT)
vpath %.java $(INPUT)

all: $(CLASS)

$(CLASS) : %.class : %.java
	@echo Compiling $^
	@$(JC) $(FLAGS) $< -d $(OUTPUT) src/Main.java

clean:
	$(RM) $(OUTPUT)/*.class