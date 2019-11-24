package Data;

import org.rspeer.runetek.api.movement.position.Area;

public enum Location {
    copper_tin_iron_mine(Area.rectangular(3281, 3370, 3290, 3360)),
    coal_mine(Area.rectangular(3080, 3423, 3084, 3420));

    private Area area;

    Location(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }

}
