package rx.dictionary;


import org.junit.jupiter.api.Test;
import rx.dictionary.vo.LexicalItemVO;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LexicalItemVOTest {
    @Test
    public void nullLanguageLocale() {
        assertThrows(NullPointerException.class, () -> new LexicalItemVO("test", null));
    }
}
