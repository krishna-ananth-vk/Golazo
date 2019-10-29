package tech.the_code_builder.golazo;



public class User {

    String name,points,id,pos;

    public User(String name, String point, String id,String pos){
        this.pos = pos;
        this.id = id;
        this.name = name;

        this.points = point;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPoints() {
        int p = Integer.parseInt(points);
        return String.format("%3d",p);
    }

    public String getPos() {
        return pos;
    }
}
