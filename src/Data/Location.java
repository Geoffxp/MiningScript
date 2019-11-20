package Data;

import org.rspeer.runetek.api.movement.position.Area;

public enum Location {
    copper_tin_iron_mine(Area.rectangular(3281, 3370, 3290, 3360)),
    coal_mine(Area.rectangular(3029, 9821, 3033, 9827));

    private Area area;

    Location(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }

}
