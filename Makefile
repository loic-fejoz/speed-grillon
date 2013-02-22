#-----------------------------------------------------
ASCIIDOC=asciidoc -a data-uri
MD=markdown
MAIN=cdcu.html FAQ.html README.html
#-----------------------------------------------------

all: $(MAIN)

%.html: %.adoc
	$(ASCIIDOC) -a toc2 -b html5 -a numbered $< 

%.html: %.md
	$(MD) $< > $@

clean: 
	@echo '==> Cleaning compilation files'
	@rm -f *~
	@# fichiers de compilation latex
	@rm -f *.log *.aux *.dvi *.toc *.lot *.lof *.ilg
	@# fichiers de bibtex
	@rm -f *.blg

distclean: clean
	@echo '==> Cleaning distribution files'
	@rm -f $(MAIN)