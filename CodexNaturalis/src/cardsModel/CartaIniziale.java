package cardsModel;

import java.util.ArrayList;
import java.util.List;

public class CartaIniziale extends Carta {
    private List<String> centrale;

    public CartaIniziale(int idCarta, String fronte, List<String> centrale, String retro) {
        super(idCarta, "Iniziale", fronte, retro);
        this.centrale = new ArrayList<>(centrale);
    }

    public List<String> getCentrale() {
        return new ArrayList<>(centrale);
    }

    public void setCentrale(List<String> centrale) {
        this.centrale = new ArrayList<>(centrale);
    }

    @Override
    public String toString() {
        if (!isFronte()) {
            return toStringRetro();
        }

        StringBuilder sb = new StringBuilder();
        String[] angoli = getFronte().split(" - ");
        List<String> centraleList = getCentrale();

        sb.append("----------------------------\n");

        sb.append(String.format("[%s%s%20s%s%s]\n",
                getEmojiAngolo(angoli[0]), getBordoAngolo(angoli[0], false),
                "",
                getBordoAngolo(angoli[1], true), getEmojiAngolo(angoli[1])
        ));

        sb.append(String.format("[%s]\n", centraStringa((centraleList.size() > 0 ? "["+getEmojiRegno(centraleList.get(0))+"]" : ""), 26)));
        sb.append(String.format("[%s]\n", centraStringa((centraleList.size() > 1 ? "["+getEmojiRegno(centraleList.get(1))+"]" : ""), 26)));
        sb.append(String.format("[%s]\n", centraStringa((centraleList.size() > 2 ? "["+getEmojiRegno(centraleList.get(2))+"]" : ""), 26)));

        sb.append(String.format("[%s%s%20s%s%s]\n",
                getEmojiAngolo(angoli[2]), getBordoAngolo(angoli[2], false),
                "",
                getBordoAngolo(angoli[3], true), getEmojiAngolo(angoli[3])
        ));

        sb.append("----------------------------\n");
        return sb.toString();
    }

    @Override
    public String toStringRetro() {
        StringBuilder sb = new StringBuilder();
        String[] angoli = getRetro().split(" - ");

        sb.append("----------------------------\n");

        sb.append(String.format("[%s%s%20s%s%s]\n",
                getEmojiAngolo(angoli[0]), getBordoAngolo(angoli[0], false),
                "",
                getBordoAngolo(angoli[1], true), getEmojiAngolo(angoli[1])
        ));

        sb.append(String.format("[%26s]\n", ""));
        sb.append(String.format("[%26s]\n", ""));
        sb.append(String.format("[%26s]\n", ""));

        sb.append(String.format("[%s%s%20s%s%s]\n",
                getEmojiAngolo(angoli[2]), getBordoAngolo(angoli[2], false),
                "",
                getBordoAngolo(angoli[3], true), getEmojiAngolo(angoli[3])
        ));

        sb.append("----------------------------\n");
        return sb.toString();
    }
}
