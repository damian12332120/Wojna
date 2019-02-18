public enum Kolor {
    KARO("karo"),TREFL("trefl"),PIK("pik"),KIER("kier");

    private String name;

    Kolor(String name){
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
