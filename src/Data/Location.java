package Data;

import org.rspeer.runetek.api.movement.position.Area;

public enum Location {
	//enums are kinda confusing. I don't really understand them
	//that much. The tutorial told me to make a get function
	//even though I don't use this.
	//Just copy this lol and you can add more locations
	//to this list below and get the area by calling
	//Location.area_name (i.e. Location.coal_mine)
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
