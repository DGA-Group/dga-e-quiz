import com.dga.equiz.model.word.Definition;
import com.dga.equiz.model.word.Meaning;
import com.dga.equiz.model.word.Word;
import com.dga.equiz.utils.EquizUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApiDictionaryTest {
    private void printWord(Word word) {
        System.out.println("Word: " + word.getWord());
        System.out.println();
        List<Meaning> meanings = word.getMeanings();
        for (Meaning meaning : meanings) {
            Definition definition = meaning.getDefinitions().get(0);
            System.out.println(definition.getDefinition());
            System.out.println(definition.getExample());
            System.out.println();
        }
    }
    private void printSuggest(Word word) {
        System.out.println("    " + word.getWord());
    }

    @Test
    void test1() {
        try {
            List<Word> words = EquizUtils.FetchWordFromDictionary("book");
            for (Word word : words) {
                printWord(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(true);
    }

    @Test
    void test2() {
        try{
            List<Word> words = EquizUtils.FetchSuggestWordFromDictionary("boo");
            System.out.println("Suggestion for 'boo':");
            for (Word word : words){
                printSuggest(word);
            }
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        assertTrue(true);
    }



}
