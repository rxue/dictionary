package rx.dictionary.jpa;

public class EntityManagerAddNewExplanationWillAutoRefreshIT extends AbstractDatabaseConfiguration {
    /**
    @Test
    public void testUpdate() {
        executeTransaction(entityManagerFactory, repo -> {
                    LexicalItem l = new LexicalItem();
                    l.setLanguage(Locale.forLanguageTag("en"));
                    l.setValue("take");
                    Explanation explanation = new Explanation();
                    explanation.setLanguage(Locale.forLanguageTag("en"));
                    explanation.setExplanation("action of taking");
                    l.addExplanation(explanation);
                    repo.add(l);
                }
        );
        System.out.println("going to add a new explanation to existing LexicalItem");
        executeTransaction(entityManagerFactory, repo -> {
                    LexicalItem l = repo.findByKeywordStartWith(new Keyword("tak", Locale.ENGLISH), Locale.ENGLISH)
                            .get(0);
                    Explanation explanation = new Explanation();
                    explanation.setLanguage(Locale.SIMPLIFIED_CHINESE);
                    explanation.setExplanation("new");
                    l.addExplanation(explanation);
                }
        );
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            LexicalItem lexicalItem = ITUtil.getSingleLexicalItem(em);
            Set<Explanation> explanations = lexicalItem.getExplanations();
            assertSame(2, explanations.size(), "Add a new Explanation causes automatic refresh");
        }
    }
    }*/

}
