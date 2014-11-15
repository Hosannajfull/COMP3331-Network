package set;

public class Main {
    public static void main(String[] args) {
        // Create a set to put people in
        ArraySet personSet = new ArraySet(10);
        
        // Create some people and put them into the person set
        try {
            Person lauren = new Person("Lauren", "Zou", "123-456-7890");
            Person heba = new Person("Heba Elfardy", "000-111-2121");
            Person devika = new Person("Devika", "Dwivedi", "987-789-3987");
            Person karan = new Person("Karan Matnani", "567-432-4453");
            Person baiqin = new Person("Baiqin", "Wang", "322-323-2243");
            Person esha = new Person("Esha Maharishi", "674-343-5674");
            
            personSet.add(lauren);
            personSet.add(heba);
            personSet.add(devika);
            personSet.add(karan);
            personSet.add(baiqin);
            personSet.add(esha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Print out the current person set
        ArraySetIterator iterator = personSet.iterator();
        while (iterator.hasNext()) {
            Person person = (Person) iterator.next();
            System.out.println(person);
        }
        
        // Print out the Person.count
        System.out.println("There are " + Person.getCount() + " teaching assistants!");
    }
}