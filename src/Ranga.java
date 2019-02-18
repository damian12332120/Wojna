public enum Ranga {
    DWA("dwa"),TRZY("trzy"),CZTERY("cztery"),PIEC("pięć"),SZESC("sześć"),SIEDEM("siedem")
    , OSIEM("osiem"),DZIEWIEC("dziewieć"),DZIESIEC("dziesięć"),JOPEK("jopek"),DAMA("dama"),
    KROL("król"),AS("as");

    private String name;

    Ranga(String name){
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
