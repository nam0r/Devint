javac -nowarn -cp ../lib t2s/*.java t2s/chant/*.java t2s/exception/*.java t2s/ihm/*.java t2s/ihm/courbe/*java t2s/prosodie/*.java t2s/son/*.java  t2s/newProsodies/*.java t2s/newProsodies/courbe/*.java t2s/traitement/*.java t2s/util/*.java -d ../bin -extdirs ../lib

pause

cd ..\bin

jar cmf  ../src/manifest.mf SI_VOX.jar t2s

pause
