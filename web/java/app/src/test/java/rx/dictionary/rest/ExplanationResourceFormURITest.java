package rx.dictionary.rest;

import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplanationResourceFormURITest {
    @Test
    public void valueWithSpace() {
        Explanation explanation = new Explanation();
        explanation.setId(1);
        assertEquals("/explanations/1", ExplanationsResource.formURI(explanation).toString());
    }
}
