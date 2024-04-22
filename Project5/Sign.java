import java.util.*;

public class Sign {
    int start;
    int end;
    double distance;
    List<City> cities = new ArrayList<>();
    
    public Sign(int s, int e, double d){
        start = s;
        end = e;
        distance = d;
    }
    
    public void addCity(City c) {
        cities.add(c);
    }
}
