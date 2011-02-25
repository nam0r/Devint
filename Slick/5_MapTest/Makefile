OUTPUT=bin
INPUT=src
JC=javac
RM=rm
FLAGS= -g -cp libs/slick.jar:bin
#FILE=$(wildcard *.java $(INPUT)/*.java) # Liste les fichiers sources
FILE=src/SimpleGame.java src/Main.java # Pour que les sources soient dans l'ordre des dependances
CLASS=$(patsubst %.java,%.class,$(FILE)) # Remplace les ext .java en .class

vpath %.class $(OUTPUT)
vpath %.java $(INPUT)

all: $(CLASS)

$(CLASS) : %.class : %.java
	@echo Compiling $^
	@$(JC) $(FLAGS) $< -d $(OUTPUT) src/Main.java

clean:
	$(RM) $(OUTPUT)/*.class
