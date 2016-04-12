package cs355.model.image;

/**
 * Created by Joshua on 4/12/2016.
 */
public class MedianHolder {
    private int number;
    private int occurrence;

    public MedianHolder(){
        number = 0;
        occurrence = 0;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    @Override
    public boolean equals(Object obj) {
        MedianHolder comp = (MedianHolder)obj;
        if (comp.number == number){
            return true;
        }
        return false;
    }
}
