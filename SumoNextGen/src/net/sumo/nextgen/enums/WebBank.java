package net.sumo.nextgen.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.Script;

public enum WebBank {

    DRAYNOR(Banks.DRAYNOR),
    AL_KHARID(Banks.AL_KHARID),
    LUMBRIDGE(Banks.LUMBRIDGE_UPPER),
    FALADOR_EAST(Banks.FALADOR_EAST),
    FALADOR_WEST(Banks.FALADOR_WEST),
    VARROCK_EAST(Banks.FALADOR_EAST),
    VARROCK_WEST(Banks.VARROCK_WEST),
    SEERS(Banks.CAMELOT),
    CATHERBY(Banks.CATHERBY),
    EDGEVILLE(Banks.EDGEVILLE),
    YANILLE(Banks.YANILLE),
    GNOME_STRONGHOLD(Banks.GNOME_STRONGHOLD),
    ARDOUNGE_NORTH(Banks.ARDOUGNE_NORTH),
    ARDOUNE_SOUTH(Banks.ARDOUGNE_SOUTH),
    CASTLE_WARS(Banks.CASTLE_WARS),
    DUEL_ARENA(Banks.DUEL_ARENA),
    PEST_CONTROL(Banks.PEST_CONTROL),
    CANIFIS(Banks.CANIFIS),
    TZHAAR(Banks.TZHAAR);

    private final Area area;

    WebBank(Area area) {
        this.area = area;
    }

    public static WebBank getNearest(Script script) {
        WebBank bank = null;
        int distance = Integer.MAX_VALUE;
        for (WebBank b : WebBank.values()) {
            final int bDistance = b.area.getRandomPosition().distance(script.myPosition());
            if (bDistance < distance) {
                distance = bDistance;
                bank = b;
            }
        }
        return bank;
    }

    public Position getRandomPosition() {
        return area.getRandomPosition();
    }

    public Area getArea() {
        return area;
    }
}