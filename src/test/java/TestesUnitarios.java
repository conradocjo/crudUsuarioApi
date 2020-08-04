
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import io.swagger.utils.TratativasUteis;

@RunWith(MockitoJUnitRunner.class)
public class TestesUnitarios {

	@Before
	public void init() {
		initMocks(this);
	}

	@Test
	public void validaMetodoIsListNotEmpty() {
		assertTrue(TratativasUteis.isListNotEmpty(Arrays.asList("Teste")));
		assertTrue(TratativasUteis.isListNotEmpty(Arrays.asList(1)));
		assertFalse(TratativasUteis.isListNotEmpty(null));
	}

}
