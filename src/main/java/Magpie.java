/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *          Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
    /**
     * Get a default greeting   
     * @return a greeting
     */



    public String getGreeting()
    {
        return "Hello, let's talk.";
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (statement.trim().length() == 0) {
            response = "Say something, please.";
        }
        else if (findWord(statement, "no") >= 0) {
            response = "Why so negative?";
        }
        else if (findWord(statement, "dog") >= 0 || findWord(statement, "cat") >= 0) {
            response = "Tell me more about your pets.";
        }
        else if (findWord(statement, "nathan") >= 0) {
            response = "He sounds like a good teacher.";
        }

        else if (findWord(statement, "mother") >= 0
                || findWord(statement, "father") >= 0
                || findWord(statement, "sister") >= 0
                || findWord(statement, "brother") >= 0) {
            response = "Tell me more about your family.";
        }
        else if (findWord(statement, "life, the universe, and everything") >= 0) {
            response = "42.";
        }
        else if (findWord(statement, "pod bay doors") >= 0) {
            response = "I'm sorry, Dave. I'm afraid I can't do that.";
        }
        else if (findWord(statement, "I want") >= 0) {
            return transformIWantStatement(statement);
        }
        else if (findWord(statement, "I") == 0
                && findWord(statement, "you")==statement.length()-3) {
            return transformIYouStatement(statement);
        }
        else if (findWord(statement, "you") >= 0
                && findWord(statement, "me") > findWord(statement, "you")) {
            return transformYouMeStatement(statement);
        }
        else if (findWord(statement, "I want to") >= 0) {
            return transformIWantToStatement(statement);
        }
        else if (findWord(statement, "my name is") >= 0) {
            return transformMyNameIsStatement(statement);
        }

        else {
            response = getRandomResponse();
        }
        return response;
    }
    
    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    public String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 6;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0) {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1) {
            response = "Hmmm.";
        }
        else if (whichResponse == 2) {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3) {
            response = "You don't say.";
        }
        else if (whichResponse == 4) {
            response = "Really?";
        }
        else if (whichResponse == 5) {
            response = "Go on.";
        }
        return response;
    }

    // Checks to see if the String word appears as a whole word
    // in the String str (in this case, a "whole word" means that 
    // word is not just a substring of some larger word in str)

    // This method should work regardless of the capitalization 
    // of str or word

    // The method returns the index of the first character in word
    // if it is found, and returns -1 otherwise. 
    public int findWord(String strCaps, String wordCaps) {
        String word = wordCaps.toLowerCase();
        String str = strCaps.toLowerCase();

        int start = str.indexOf(word);
        int end = start + word.length() - 1;
//        System.out.println(word);

        if (!(str.contains(word))) return -1;
//        System.out.println(word);

        if(start == 0 && str.length() == word.length()) return 0;
//        System.out.println(word);

        if(start == 0 && str.charAt(end+1)==' ') {
            return 0;
        }
//        System.out.println(word);

        if(end == str.length()-1 && str.charAt(start-1) == ' ') {
            return start;
        }
//        System.out.println(word);
        //   n o
        // 0 1 2 3 4 5 6
        if(start>0 && (str.charAt(start - 1) == ' ') && (str.charAt(end + 1) == ' ')) {
            return start;
        }
        else return -1;

    }

    
    // We will work on the following methods later!

    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want"
     * @return the transformed statement
     */
    public String transformIWantStatement(String statement)
    {
        int begin = findWord(statement, "I want")+6;

        return "Would you really be happy if you had" + statement.substring(begin) + "?";
    }

    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     * @param statement the user statement, assumed to contain "I" followed by "you"
     * @return the transformed statement
     */
    public String transformIYouStatement(String statement)
    {
        return "Why do you " + statement.substring(2, statement.length()-3) + "me?";
    }

    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    public String transformIWantToStatement(String statement)
    {

        return "What would it mean to " + statement.substring(10) + "?";
    }




    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    public String transformYouMeStatement(String statement)
    {
        int indexYou = findWord(statement,"you");
        int indexMe = findWord(statement, "me");
        return "What makes you think that I " + statement.substring(indexYou+4, indexMe) + "you?";
    }
    public String transformMyNameIsStatement(String statement) {
        String name = statement.substring(findWord(statement, "my name is") + 11);
        return "Your name is " + name + "? Well, you can call me Hal.";
    }
}
