package code;

public class LGAModel {
    private int spaceSizeY;
    private int spaceSizeX;
    private Cell[][][] space;
    private int iterationAmount;
    private int iterationNow = 0;
    private int realIt=0;
    private int iterationSizeTab=3;
    private int realPreviousIteration;

    public LGAModel(int spaceSizeX, int spaceSizeY, int iterationAmount) {
        this.spaceSizeX = spaceSizeX;
        this.spaceSizeY = spaceSizeY;
        this.iterationAmount = iterationAmount;
        space = new Cell[iterationSizeTab][spaceSizeY][spaceSizeX];
        // INICJALIZACJA MACIERZY
        for (int t = 0; t < iterationSizeTab; t++) {
            for (int y = 0; y < spaceSizeY; y++) {
                for (int x = 0; x < spaceSizeX; x++) {
                    if(x==0||x==spaceSizeX-1||y==0||y==spaceSizeY-1)
                        space[t][y][x] = new Cell(-1);
                    else
                        space[t][y][x] = new Cell(0);
                }
            }
        }
    }



    public boolean play() {

        //PRZECHDOZENIE PO MACIERZY
        if (iterationNow < iterationAmount) {
            iterationNow++;
            realIt=iterationNow%iterationSizeTab;
            realPreviousIteration=(iterationNow-1)%iterationSizeTab;
            collision();
            streaming();
            return true;
        }
        return false;
    }

private void collision(){
    for (int y = 0; y < spaceSizeY; y++) {
        for (int x = 0; x < spaceSizeX; x++) {
                if(space[realIt][y][x].getValue()!=-1) {
                    space[realIt][y][x].setValue(0);
                    space[realIt][y][x].clearStan();
                }

            if(space[realPreviousIteration][y][x].getValue()==1) {

                if (space[realPreviousIteration][y][x].getStan(1) == 1 && space[realPreviousIteration][y][x].getStan(3) == 1 &&
                        space[realPreviousIteration][y][x].getStan(0) == 0 && space[realPreviousIteration][y][x].getStan(2) == 0) {
                    space[realPreviousIteration][y][x].clearStan();
                    space[realPreviousIteration][y][x].setStan(0);
                    space[realPreviousIteration][y][x].setStan(2);

                } else if (space[realPreviousIteration][y][x].getStan(0) == 1 && space[realPreviousIteration][y][x].getStan(2) == 1 &&
                        space[realPreviousIteration][y][x].getStan(1) == 0 && space[realPreviousIteration][y][x].getStan(3) == 0) {
                    space[realPreviousIteration][y][x].clearStan();
                    space[realPreviousIteration][y][x].setStan(1);
                    space[realPreviousIteration][y][x].setStan(3);

                }
            }
        }
    }
}

    private void streaming() {
        for (int y = 1; y < spaceSizeY-1; y++) {
            for (int x = 1; x < spaceSizeX-1; x++) {

                if (space[realPreviousIteration][y][x].getValue() == 1) {
                    if(space[realPreviousIteration][y][x].getStan(0)==1){
                        if(space[realPreviousIteration][y][x-1].getValue() !=-1){
                            space[realIt][y][x-1].setValue(1);
                            space[realIt][y][x-1].setStan(0);
                        }
                        else{
                            space[realIt][y][x].setValue(1);
                            space[realIt][y][x].setStan(2);
                        }
                    }
                    if(space[realPreviousIteration][y][x].getStan(2)==1){
                        if(space[realPreviousIteration][y][x+1].getValue() !=-1){
                            space[realIt][y][x+1].setValue(1);
                            space[realIt][y][x+1].setStan(2);
                        }
                        else{
                            space[realIt][y][x].setValue(1);
                            space[realIt][y][x].setStan(0);
                        }
                    }
                     if(space[realPreviousIteration][y][x].getStan(1)==1){
                        if(space[realPreviousIteration][y-1][x].getValue() !=-1){
                            space[realIt][y-1][x].setValue(1);
                            space[realIt][y-1][x].setStan(1);
                        }
                        else{
                            space[realIt][y][x].setValue(1);
                            space[realIt][y][x].setStan(3);
                        }
                    }
                     if(space[realPreviousIteration][y][x].getStan(3)==1){
                        if(space[realPreviousIteration][y+1][x].getValue() !=-1){
                            space[realIt][y+1][x].setValue(1);
                            space[realIt][y+1][x].setStan(3);
                        }
                        else{
                            space[realIt][y][x].setValue(1);
                            space[realIt][y][x].setStan(1);
                        }
                    }
                }
            }
        }
    }

    // -------------------------------------------- USTAWIENIE OBSZARU -----------------------------------
    public void createBorder(int size,int shift){

        for (int t = 0; t < iterationSizeTab; t++) {
            for (int y = 0; y < spaceSizeY / 2 - size/2; y++) {
                space[t][y][shift].setValue(-1);
            }
            for (int y = spaceSizeY / 2 + size/2; y < spaceSizeY; y++) {
                space[t][y][shift].setValue(-1);
            }
        }
    }



    public Cell[][][] getSpace() {
        return space;
    }

    public void setSpace(Cell[][][] space) {
        this.space = space;
    }


    public int getIterationAmount() {
        return iterationAmount;
    }

    public void setIterationAmount(int iterationAmount) {
        this.iterationAmount = iterationAmount;
    }

    public int getIterationNow() {
        return iterationNow;
    }

    public void setIterationNow(int iterationNow) {
        this.iterationNow = iterationNow;
    }

    public int getSpaceSizeY() {
        return spaceSizeY;
    }

    public void setSpaceSizeY(int spaceSizeY) {
        this.spaceSizeY = spaceSizeY;
    }

    public int getSpaceSizeX() {
        return spaceSizeX;
    }

    public void setSpaceSizeX(int spaceSizeX) {
        this.spaceSizeX = spaceSizeX;
    }

    public int getIterationSizeTab() {
        return iterationSizeTab;
    }

    public void setIterationSizeTab(int iterationSizeTab) {
        this.iterationSizeTab = iterationSizeTab;
    }
}
