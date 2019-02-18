import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Rozgrywka {

    private List<Karta> talia;
    private Gracz uzytkownik;
    private Gracz komputer;
    private List<Karta> graneOdkryte;
    private List<Karta> graneZakryte;

    public Rozgrywka() {
        talia = new ArrayList<>();
        uzytkownik = new Gracz();
        komputer = new Gracz();
        graneOdkryte = new ArrayList<>();
        graneZakryte = new ArrayList<>();
    }

    public void uruchomGre() {
        uzupelnianieTalii(5);
        tasowanie();
        rozdanie();
        rozgrywka();
    }

    public void uzupelnianieTalii(int ileRang) {
        if (ileRang < Ranga.values().length) {
            for (int i = 0; i < ileRang; i++) {
                for (Kolor kolor : Kolor.values()) {
                    Karta karta = new Karta(kolor, Ranga.values()[i]);
                    talia.add(karta);
                }
            }
        }else{
            System.out.println("Liczba kart z poza zakresu");
        }
        System.out.println("Talia jest uzupełniona");
    }


    public void tasowanie() {
        Collections.shuffle(talia);
        System.out.println("Talia jest potasowana ");
    }

    public void rozdanie() {
        boolean turaGracza = true;
        for (Karta karta : talia) {
            if (turaGracza) {
                uzytkownik.getListaKartGranych().add(karta);
                turaGracza = false;
            } else {
                komputer.getListaKartGranych().add(karta);
                turaGracza = true;
            }
        }
        System.out.println("Talia jest rozdana\n");
    }

    public void rozgrywka() {
        int licznikTur = 1;
        boolean czyKoniecGry;
        do {
            System.out.println("Tura: " + licznikTur);
            System.out.println("");
            czyKoniecGry = pojedynczaTura();
            licznikTur++;

        } while (!czyKoniecGry);
        if (uzytkownik.isCzyPrzegrany()) {
            System.out.println("Koniec gry. Wygrał: Komputer");
        } else
            System.out.println("Koniec gry. Wygrał: Uzytkownik");
    }

    public boolean pojedynczaTura() {
        int wynikRundy;
        boolean czyWojna = false;
        boolean czyKoniecGry = false;
        do {
            czyKoniecGry = uzupelnijKarty();
            if (czyKoniecGry) {
                return czyKoniecGry;
            }
            if (czyWojna) {
                dodajZakryteKarty();
                czyKoniecGry = uzupelnijKarty();
                if (czyKoniecGry) {
                    return czyKoniecGry;
                }
                wynikRundy = pojedynczaRunda();
                czyWojna = false;
            } else {
                wynikRundy = pojedynczaRunda();
                czyWojna = true;
            }

        } while (wynikRundy == 0);
        przekazWygranemu(wynikRundy);
        return false;
    }

    public int pojedynczaRunda() {
        int wynik = 0;
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        Karta kartaGracza = uzytkownik.zagrywka();
        graneOdkryte.add(kartaGracza);
        System.out.println("Gracz zagrywa " + kartaGracza);
        scanner.nextLine();
        Karta kartaKomputera = komputer.zagrywka();
        graneOdkryte.add(kartaKomputera);
        System.out.println("Komputer zagrywa " + kartaKomputera);
        scanner.nextLine();
        wynik = porownajKarty(kartaGracza, kartaKomputera);
        System.out.println(podajWynikRundy(wynik));
        return wynik;
    }

    public String podajWynikRundy(int wynik) {
        if (wynik > 0) {
            return "Gracz wygrywa rundę";
        } else if (wynik < 0) {
            return "Komputer wygrywa rundę";
        } else {
            return "remis - wojna";
        }
    }


    public void dodajZakryteKarty() {
        System.out.println("Gracz rzuca zakrytą kartę");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        graneZakryte.add(uzytkownik.zagrywka());
        System.out.println("Komputer rzuca zakrytą kartę");
        graneZakryte.add(komputer.zagrywka());
        scanner.nextLine();
    }

    public void przekazWygranemu(int wynik) {
        Gracz gracz;
        if (wynik > 0) {
            gracz = uzytkownik;
        } else {
            gracz = komputer;
        }
        gracz.getListaKartWygranych().addAll(graneOdkryte);
        if (!graneZakryte.isEmpty()) {
            gracz.getListaKartWygranych().addAll(graneZakryte);
            System.out.println("Karty zakryte:" + graneZakryte);
            graneZakryte.clear();
        }
        graneOdkryte.clear();
    }

    public boolean uzupelnijKarty() {
        boolean czyKoniec = false;
        if (uzytkownik.czyListaGranychPusta()) {
            czyKoniec = uzytkownik.uzupelnijListeGranych();
            if (czyKoniec) {
                return czyKoniec;
            }
        }
        if (komputer.czyListaGranychPusta()) {
            czyKoniec = komputer.uzupelnijListeGranych();
        }
        return czyKoniec;
    }

    public int porownajKarty(Karta karta1, Karta karta2) {
        return karta1.getRanga().compareTo(karta2.getRanga());
    }

    // debuging
    public void wydrukujTure() {
        System.out.println("uzytkownik: " + uzytkownik.getListaKartGranych());
        System.out.println("komputer: " + komputer.getListaKartGranych());
        System.out.println("uzytkownik: " + uzytkownik.getListaKartWygranych());
        System.out.println("komputer: " + komputer.getListaKartWygranych());
        System.out.println("-------");

    }

    public Gracz getGracz() {
        return uzytkownik;
    }

    public Gracz getKomputer() {
        return komputer;
    }
}
