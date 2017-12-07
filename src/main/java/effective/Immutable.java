package effective;

import java.util.Collections;
import java.util.List;

/*2. Ensure that the class can’t be extended.
* Immutable class should be a final
* */
public final class Immutable {
    /*3. Make all fields final.
      4. Make all fields private.
    * Immutable class should only have private final fields
    * */
    private final int field1;
    private static final int field2;
    private final MutableField field3;
    private final List<String> list;
    //private String state;


    static {
        field2 = 1;
    }

    /*Ensure exclusive access to any mutable components.*/
    public Immutable(int field1, MutableField field3, List<String> list) {
        this.field1 = field1;
        this.field3 = field3;
        this.list = Collections.unmodifiableList(list);
        //this.list.add(""); // UnsupportedOperationException
    }

    public class MutableField {
        private String value;

        public MutableField(String value) {
            this.value = value;
        }
    }

    public Immutable change(int field1, MutableField field3, List<String> list) {
        return new Immutable(this.field1 + field1, new MutableField(this.field3.value + field3.value), list);
    }



    /*1. Don’t provide any methods that modify the object’s state (known as mutators).*/
    /*public void changeState(String newState) {
        state = newState;
    }*/
}
