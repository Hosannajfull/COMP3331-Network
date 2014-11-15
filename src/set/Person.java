package set;

public class Person implements Comparable<Person> {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private static int count = 0;

    /**
     * Constructor for a {@link Person}. Sets the first name, last name, and
     * phone number. Also validates the phone number. Increments the count for
     * {@link Person} objects in general.
     * 
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @throws Exception
     *             exception for the phone number validation
     */
    public Person(String firstName, String lastName, String phoneNumber) throws Exception {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

        // Validate the phone number
        this.validatePhoneNumber();

        // Increment the Person count
        Person.count++;
    }

    /**
     * Constructor for a {@link Person}. Sets the first and last name with one
     * name.
     * 
     * @param name
     * @param phoneNumber
     * @throws Exception
     *             exception for validity of the name (needs to have a first
     *             name and a last name)
     */
    public Person(String name, String phoneNumber) throws Exception {
        // Try to split the person's name into a first and last name
        String[] nameParts = name.split(" ");
        if (nameParts.length != 2) {
            throw new Exception("Name format is <first name> <last name>");
        }

        this.firstName = nameParts[0];
        this.lastName = nameParts[1];
        this.phoneNumber = phoneNumber;

        // Validate the phone number
        this.validatePhoneNumber();

        // Increment the Person count
        Person.count++;
    }

    /**
     * Validates the phone number.
     * 
     * @throws Exception
     *             exception if the phone number does not follow the correct
     *             format
     */
    private void validatePhoneNumber() throws Exception {
        String errorMessage = "Phone numbers must follow the format XXX-XXX-XXXX";

        // Make sure that the length of the phone number is 12
        if (this.phoneNumber.length() != 12) {
            throw new Exception(errorMessage);
        }

        // Make sure that there are two '-' in the phone number
        String[] phoneNumberArray = phoneNumber.split("-");
        if (phoneNumberArray.length != 3) {
            throw new Exception(errorMessage);
        }

        // Iterate through the three phone number parts and check if all of them
        // are digits
        for (int i = 0; i < phoneNumberArray.length; i++) {
            if (!Person.allDigits(phoneNumberArray[i])) {
                throw new Exception(errorMessage);
            }
        }
    }

    /**
     * Checks each character of the string is a valid digit.
     * 
     * @param str
     *            string to check for digits
     * @return true if all characters of the string are digits, false otherwise
     */
    private static boolean allDigits(String str) {
        // Iterate through all the characters of the string
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if (character < '0' || character > '9') {
                // Not a digit
                return false;
            }
        }

        // All characters of the string are digits
        return true;
    }

    /**
     * @return first name and last name of the person
     */
    public String name() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * @return the count
     */
    public static int getCount() {
        return Person.count;
    }

    /**
     * Compare two Person objects by their last name, then their first name,
     * then by their phone number.
     * 
     * @param person
     *            person to compare to
     * @return 0 if the same person, -1 if last name of parameter person is
     *         alphabetically before the last name of this person, 1 if
     *         otherwise
     */
    @Override
    public int compareTo(Person person) {
        // Put the last name, first name, and phone number all in one string
        String compareString = this.lastName + this.firstName + this.phoneNumber;
        String otherPersonCompareString = person.getLastName() + person.getFirstName() + person.getPhoneNumber();
        return compareString.compareTo(otherPersonCompareString);
    }
    
    @Override
    public String toString() {
        return this.lastName + ", " + this.firstName + " (" + this.getPhoneNumber() + ")";
    }
}