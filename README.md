# RMA_DZ3
Pri pokretanju aplikacije prikazuje se početni zaslon s 2 button-a "Add new task" i "Add new category" te sa ListView elementom. 
Unutar list elementa nalaze se task-ovi koji se dohvaćaju iz baze podataka. Svaki element liste(task) ima imageView element čija 
boja prikazuje prioritet i 2 textView elementa koja prikazuju naziv zadatka i kategoriju. Klikom na button "Add new task" prikazuje se 
sučelje za dodavanje novog task-a gdje se unose naziv zadatka(editText), priroritet i kategorija (2 spinner-a). Podatci za spinner s 
prioritetima se dohvaćaju iz string array-a(string.xml), a za spinner s kategorijama se dohvaćaju iz xml datoteke kreirane korištenjem 
djeljenih postavki. Pritiskom na button "Add new task" uneseni se podatci spremaju u bazu podataka i prikazuje se početni zaslon u kojemu 
možemo vidjeti i prethodno dodani task. Odabirom opcije "Add new category" prikazuje se zaslon za unos nove kategorije(editText) pritiskom 
na button kategorija se sprema metodom djeljenih postavki te ju je moguće odabrati iz spinner-a prilikom izrade idućeg zadatka.
Također postoji mogućnost brisanja i ostvaruje se dugim klikom na task koji se želi izbrisati.

Pri izradi došlo je do problema prilikom dodavanja nove kategorije gdje je bilo potrebno spremiti polje stringova,a ne jednu vrijednost 
metodom shared preferences što se rješilo tako da se save i retrieve funkcijama klase PreferenceManagment spremalo i dohvaćalo polje 
stringova unutar tipa Set<String> pomoću funkcija putStringSet i getStringSet.
Kako ne bi radili dva različita activity-ja i xml-a, za prikaz sučelja za dodavanje zadatka i kategorije koristilo se svojstvo elemenata 
visibility te se sučelje prikazivalo s obzirom na vrijednost VALUE prosljeđenu s ListActivity-ja metodom putExtra. Ovisno o tome koji je 
button pritisnut prosljeđuje se različita vrijednost VALUE.
Boja ImageView elementa postavlja se s obzirom na vrijednost priority unutar switch case-a te se prosljeđuje konstruktoru klase Task. 
Provjera koji button je pritisnut se odvija unutar switch case-a gdje se dohvaća id button-a s getId funkcijom.  
Za brisanje iz baze podataka je kreirana funkcija deleteTask pomoću koje se izvršavanjem sql upita briše iz baze podataka Task koji je 
proljeđen kao parametar funkcije. Dugim klikom na task dohvaća se Task getItem funkcijom TaskAdaptera te se prosljeđuje deleteTask f-ji. 
Kako bi se odmah ažurirala i lista task-ova prikazanih na zaslonu koristi se funkcija deleteAt kojoj se prosljeđuje pozicija Task-a te 
se Task briše iz liste.

Izvori:
-predlošci s loomena
-https://developer.android.com/guide/topics/ui/controls/spinner.html	-spinner
-https://developer.android.com/reference/android/transition/Visibility.html		-visibility
-https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html	-database
-http://stackoverflow.com/questions/5817144/android-select-row-by-id-in-sqlite -select row
-http://stackoverflow.com/questions/7510219/deleting-row-in-sqlite-in-android -delete row
-http://stackoverflow.com/questions/9917787/merging-two-arraylists-into-a-new-arraylist-with-no-duplicates-and-in-order-in -merge arrays
-http://stackoverflow.com/questions/19820803/android-spinner-using-arrayliststring -list string to spinner
-https://developer.android.com/reference/java/util/Set.html	-Set Collection
-http://stackoverflow.com/questions/1492554/set-transparent-background-of-an-imageview-in-android	-transparent color
-http://stackoverflow.com/questions/21822614/the-background-distortion-when-the-screen-turn-from-vertical-to-horizontal -turn screen horizontal disortion
-https://kodejava.org/how-do-i-create-an-empty-collection-object/ -empty Set
-http://stackoverflow.com/questions/7057845/save-arraylist-to-sharedpreferences -Set Collection saving and retrieving, SharedPreferences
