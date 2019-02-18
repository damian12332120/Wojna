import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gracz {

    private List<Karta> listaKartGranych;
    private List<Karta> listaKartWygranych;
    private boolean czyPrzegrany = false;

    public Gracz() {
        listaKartGranych = new ArrayList<>();
        listaKartWygranych = new ArrayList<>();
    }

    public Karta zagrywka() {
        Karta granaKarta;
        if (!czyListaGranychPusta()) {
            granaKarta = listaKartGranych.get(listaKartGranych.size() - 1);
            listaKartGranych.remove(listaKartGranych.size() - 1);
            return granaKarta;
        } else {
            throw new IllegalStateException("Próba zagrania karty z pustej listy");
        }
    }

    public boolean uzupelnijListeGranych() {
        boolean czyKoniec = false;
        if (!listaKartWygranych.isEmpty()) {
            Collections.shuffle(listaKartWygranych);
            listaKartGranych.addAll(listaKartWygranych);
            listaKartWygranych.clear();
        } else {
            czyKoniec = true;
            czyPrzegrany = true;
        }
        return czyKoniec;
    }

    public boolean czyListaGranychPusta() {
        return listaKartGranych.isEmpty();
    }

    public boolean czyLsitaWygranychJestPusta() {
        return listaKartWygranych.isEmpty();
    }

    public boolean isCzyPrzegrany() {
        return czyPrzegrany;
    }

    public List<Karta> getListaKartGranych() {
        return listaKartGranych;
    }

    public List<Karta> getListaKartWygranych() {
        return listaKartWygranych;
    }


}
