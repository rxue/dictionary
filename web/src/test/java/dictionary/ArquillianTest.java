package dictionary;

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

@RunWith(Arquillian.class)
public class ArquillianTest {
	@Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClass(ExplanationRepository.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	
	@Inject
	ExplanationRepository tested;

    @Test
    public void should_create_greeting() {
    	SearchKeyword searchKeyword = new SearchKeyword("take", Locale.ENGLISH);
    	tested.find(searchKeyword, Locale.SIMPLIFIED_CHINESE);
    }
}