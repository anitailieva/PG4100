package innlevering2;

import org.junit.Test;

import java.sql.SQLException;
import static org.junit.Assert.assertTrue;


/**
 * Created by anitailieva on 16/05/16.
 */
public class QuizTest {


    @Test
    public void testCompareAnswer() throws SQLException {
        DBHandler db = new DBHandler();
        String answer = "author";
        String correctAnswer = "author";
        assertTrue(db.compareAnswer(answer, correctAnswer));
    }
    }
