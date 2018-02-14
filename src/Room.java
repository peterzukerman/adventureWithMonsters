public class Room {

    private String name;
    private String description;
    private Direction[] directions;
    private Item[] items;
    private String[] monstersInRoom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Direction[] getDirections() {
        return directions;
    }

    public void setDirections(Direction[] directions) {
        this.directions = directions;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public String[] getMonstersInRoom() {
        return monstersInRoom;
    }

    public void setMonstersInRoom(String[] monstersInRoom) {
        this.monstersInRoom = monstersInRoom;
    }
}
