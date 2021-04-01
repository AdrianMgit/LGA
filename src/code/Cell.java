package code;

public class Cell {
    private int value;

    private int[] stan =new int[4];
    private String direction;
    /*    DIRECTIONS TAB
                 tab[1]
        tab[0]           tab[2]
                 tab[3]
     */


    public Cell(int value) {
        this.value = value;
    }
    public Cell(){};


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getStan(int index) {
        return stan[index];
    }

    public void setStan(int index) {
        stan[index]=1;
    }
    public void clearStan(){
        for(int i = 0; i< stan.length; i++){
            stan[i]=0;
        }
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
