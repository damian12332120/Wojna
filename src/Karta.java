public class Karta {


    private final Kolor kolor;
    private final Ranga ranga;

    public Karta(Kolor kolor, Ranga ranga) {
        this.kolor = kolor;
        this.ranga = ranga;
    }

    public  Kolor getKolor() {
        return kolor;
    }

    public Ranga getRanga() {
        return ranga;
    }

    @Override
    public String toString() {
        return ranga+" "+kolor;
    }
}
