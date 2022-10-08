package dictionary;

import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.dictionary.ExplanationRepository;
import rx.dictionary.SearchKeyword;
import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.util.Resources;

@RunWith(Arquillian.class)
public class ArquillianIT {
	@Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
        	.addPackage("rx.dictionary.jpaentity")
            .addClasses(ExplanationRepository.class, Resources.class, SearchKeyword.class)
            .addAsResource("persistence-IT.xml", "META-INF/persistence.xml")
            .addAsResource("initial_import_IT.sql", "initial_import.sql")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	
	@Inject
	ExplanationRepository tested;
    @Test
    public void should_create_greeting() {
    	SearchKeyword searchKeyword = new SearchKeyword("crux", Locale.US);
    	List<Explanation> result = tested.find(searchKeyword, Locale.US);
    	assertFalse(result.isEmpty());
    }
}
