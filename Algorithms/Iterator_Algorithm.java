package Algorithms;

import java.util.Iterator;
import java.util.List;

public class Iterator_Algorithm {
    
    /**
     * Returns an iterator for a collection
     * @param ls collection to get an iterator for
     * @return Iterator<Object> for the collection
     */
    public Iterator<Object> getIterator(List<Object> ls) {
        return ls.iterator();
    }

}