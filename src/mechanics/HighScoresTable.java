package mechanics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * class in charge of the highscore table we will use in the game.
 * @author David Goichman.
 */
public class HighScoresTable {
    // The list of scores member.
    private List<ScoreInfo> scores;

    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    /**
     * The constructor.
     * @param size
     *            Creates an array of scores.
     */
    public HighScoresTable(int size) {
        this.scores = new ArrayList<>(size);
    }

    // Add a high-score.
    /**
     * Adds a score to the list.
     * @param score
     *            The score that will be added.
     */
    public void add(ScoreInfo score) {
        this.scores.add(score);
    }

    // Return table size.
    /**
     * Returns the size of the table.
     * @return Size of table.
     */
    public int size() {
        return this.scores.size();
    }

    // Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    /**
     * Returns current list of scores sorted in order.
     * @return The sorted list of scores.
     */
    public List<ScoreInfo> getHighScores() {
        sort(scores);
        return this.scores;
    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    // be added to the list.
    /**
     * Returns the rank of someone that should be added to the list. if he is not
     * added, then -1.
     * @param score
     *            The score that we will check.
     * @return The list spot or -1.
     */
    public int getRank(int score) {
        List<ScoreInfo> crocs = getHighScores();
        for (int i = 0; i < crocs.size(); i++) {
            if (crocs.get(i).getScore() == score) {
                return (i + 1);
            }
        }
        return -1;
    }

    // Clears the table
    /**
     * Resets the table.
     */
    public void clear() {
        this.scores = new ArrayList<>(this.scores);
    }

    // Load table data from file.
    // Current table data is cleared.
    /**
     * Loads a table if such exists.
     * @param filename
     *            The file we will load from.
     * @throws IOException
     *             An exception if loading fails.
     */
    public void load(File filename) throws IOException {
        this.clear();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(// buffered reader - has readLine()
                    new InputStreamReader(// bytes to characters wrapper
                            new FileInputStream(filename))); // binary file stream
            // print each read line
            String line = reader.readLine();
            while (line != null) {

                String name = line;
                line = reader.readLine();
                int scoreToBe = 0;
                try {
                    scoreToBe = Integer.parseInt(line);
                } catch (Exception e) {
                    return;
                }
                ScoreInfo scoreInfo = new ScoreInfo(name, scoreToBe);
                this.add(scoreInfo);

                line = reader.readLine();
            }
        } catch (IOException fileException) {
            throw new IOException("Failed reading file.");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }

    // Save table data to the specified file.
    /**
     * Saves data into the table file.
     * @param filename
     *            The file we will save into.
     * @throws IOException
     *             An exception if saving fails.
     */
    public void save(File filename) throws IOException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            for (int i = 0; i < this.scores.size(); i++) {
                writer.println(scores.get(i).getName());
                writer.println(scores.get(i).getScore());
            }
        } catch (IOException e) {
            System.out.println("Could not save !" + filename);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    /**
     * Reading from a table file.
     * @param filename
     *            The file we will load from.
     * @return a highscores table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        // read lines
        BufferedReader reader = null;
        HighScoresTable highScoresTable = new HighScoresTable(5);
        try {
            reader = new BufferedReader(// buffered reader - has readLine()
                    new InputStreamReader(// bytes to characters wrapper
                            new FileInputStream(filename))); // binary file stream
            // print each read line
            String line = reader.readLine();
            int flag = 0;
            int score = 0;
            String string = null;
            while (line != null) {
                if (flag == 0) {
                    string = line;
                }
                if (flag == 1) {
                    score = Integer.parseInt(line);
                }
                line = reader.readLine();
                if (string != null && flag == 1) {
                    ScoreInfo scoreInfo = new ScoreInfo(string, score);
                    highScoresTable.add(scoreInfo);
                    flag = -1;
                }
                flag++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename.getName());
        } catch (IOException e) {
            System.err.println("Failed reading file: " + filename.getName() + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
        return highScoresTable;
    }

    /**
     * Method sorts the scores from highest to lowest.
     * @param scoreslist
     *            The list of scores.
     */
    public static void sort(List<ScoreInfo> scoreslist) {
        int n = scoreslist.size();
        ScoreInfo temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (scoreslist.get(j - 1).getScore() < scoreslist.get(j).getScore()) {
                    temp = scoreslist.get(j - 1);
                    scoreslist.set(j - 1, scoreslist.get(j));
                    scoreslist.set(j, temp);
                }
            }
        }
    }
}