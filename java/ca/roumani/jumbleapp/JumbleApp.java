package ca.roumani.jumbleapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * The controller of this app.
 *
 * @author Franck van Breugel
 */
public class JumbleApp extends AppCompatActivity
{
    private Dictionary dictionary;

    /**
     * Initializes this controller.
     *
     * @param savedInstanceState information needed for re-initialization.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        // get the dictionary file
        File path = Environment.getExternalStorageDirectory();
        File file = new File(path, "dict.txt");

        // create a dictionary
        this.dictionary = new Dictionary(file);
        //this.dictionary.SortWords();
    }

    /**
     * Unscrambles the entered jumble.
     *
     * @param view not applicable.
     */
    public void jumble(View view)
    {
        // get the input as text
        View inputView = findViewById(R.id.input);
        EditText inputText = (EditText) inputView;
        String input = inputText.getText().toString();

        // get the unscramblings
        List<String> unscramblings = this.dictionary.getUnscramblings(input);

        // output
        View outputView = findViewById(R.id.output);
        TableLayout output = (TableLayout) outputView;

        for (String word : unscramblings)
        {
            TableRow row = new TableRow(this);
            TextView cell = new TextView(this);
            cell.setText(word);
            row.addView(cell);
            output.addView(row);
        }
    }
}