package set;

public abstract class AbstractSet implements Set {

    public int size() {
        int count = 0;

        // Iterate through all the elements in the set
        for (Iterator iterator = this.iterator(); iterator.hasNext(); iterator.next()) {
            // Increment the count at each iteration of the loop
            count++;
        }

        return count;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean isMember(Object object) {
        // Iterate through all the elements in the set
        for (Iterator iterator = this.iterator(); iterator.hasNext();) {
            // Check if the parameter object is equal to the current element
            // that the iterator is pointing to
            if (object.equals(iterator.next())) {
                return true;
            }
        }

        // We've iterated through all the elements and have not found the
        // parameter object
        return false;
    }

    public Set union(Set otherSet) {
        // Create a copy of this current set
        Set unionSet = this.copy();

        // Iterate through all the elements in the parameter set
        for (Iterator iterator = otherSet.iterator(); iterator.hasNext();) {
            // Add the object that the iterator is pointing to from the other
            // set into the union set
            unionSet.add(iterator.next());
        }

        return unionSet;
    }

    public Set intersection(Set otherSet) {
        // Create an empty copy of this current set
        Set intersectionSet = this.empty();

        // Iterate through all the elements in the parameter set, setting the
        // iterator's current pointer as the object variable at each loop
        Object object = null;
        for (Iterator iterator = otherSet.iterator(); iterator.hasNext(); object = iterator.next()) {
            // Check if object is a member of this current set
            if (this.isMember(object)) {
                // Object is a member of this current set, so add it to the
                // intersection set
                intersectionSet.add(object);
            }
        }

        return intersectionSet;
    }

    public Set copy() {
        // Create an empty copy of this current set
        Set copySet = this.empty();

        // Iterate through all the elements in this set
        for (Iterator iterator = this.iterator(); iterator.hasNext();) {
            // Add the object returned by the iterator to the copy set
            copySet.add(iterator.next());
        }

        return copySet;
    }

    public String toString() {
        String string = "{";

        // Iterates through all the elements in this set, appending the object
        // as a string to the string at each loop
        for (Iterator iterator = this.iterator(); iterator.hasNext(); string += " " + iterator.next())
            ;

        string += " }";

        return string;
    }

    public abstract void add(Object object);

    public abstract void remove(Object object);

    public abstract Iterator iterator();

    public abstract Set empty();
}