public class City {
    String name;
    int intersection;
    
    public City(int i, String n){
        name = n;
        intersection = i;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getIntersection(){
        return this.intersection;
    }
    
}
