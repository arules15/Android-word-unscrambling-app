package ca.roumani.jumbleapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.CharacterIterator;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * The dictionary, which is the model of this app.
 *
 * @author Franck van Breugel
 */
public class Dictionary
{
    /**
     * Initializes this dictionary from the given file.  Each line of the file contains a
     * single word.
     *
     * @param file file containing the words of this dictionary.
     *
     */

    private String output;

    public Dictionary(File file)
    {
        try
        {
            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                String let = input.nextLine();
                entries.add(let);
            }
            input.close();
        }
        catch (FileNotFoundException e)
        {
            // do nothing
        }
    }

    /**
     * Returns the list of words that are unscramblings of the given word.
     *
     * @param word word to be unscrambled.
     * @return list of words that are unscramblings of the given word.
     */
    public List<String> getUnscramblings(String word)
    {
        this.SortWords();
        return SearchandGet(word);
    }
    List<String> entries = new ArrayList<String>();
    Map <String, String> hash = new HashMap<String, String>();

    public void SortWords()
    {
        Collator col = Collator.getInstance(new Locale("en", "EN"));
        for (int i = 0; i < entries.size() - 1; i++)
        {
            String current = entries.get(i);
            String[] part = current.split("");
            Arrays.sort(part, col);
            String sorts = " ";
            for (int j = 0; j <= current.length(); j++)
            {
                sorts+= part[j];
                if (!hash.containsKey(sorts)){
                    hash.put(sorts, current);}
            }
        }
    }

    public List<String> SearchandGet(String word)
    {
        Collator col = Collator.getInstance(new Locale("en", "EN"));
        String sorts = " ";
        String[] parts = word.split("");
        Arrays.sort(parts, col);
        for (int j = 0; j <= word.length(); j++)
        {
            sorts += parts[j];
        }
        //boolean has = hash.containsKey(sorts);
        //String contains = hash.get(sorts);
        for (int i = 0; i < hash.size(); i++) {
            if (hash.containsKey(sorts)) {
                output = hash.get(sorts);
            } else {
                output = "Word is not contained";
            }
        }

        List<String> stuff= new ArrayList<String>();
        stuff.add(output);
        return stuff;
    }

}

