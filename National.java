/**
 * Interface for a person associated with a nation.  Code to be used for Assignment 3 in the
 *  Object-Oriented Programming (Java) course at Tel Hai Spring 2023.
 *
 *  @author Michael J. May
 *  @version 2.0
 */
public interface National {
    /**
     * Gets the name of the nation that the person is from
     * @return The name of the nation
     */
    String getNation();

    /**
     * Sets the nation that the person is from
     * @param nationName The name of the nation
     */
    void setNation(String nationName);
}
