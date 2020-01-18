package net.sepptember.lib.cliargs.internal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

class BooleanTransformerTest {
	@Test
	public void testTransformBooleanAsStringToBoolean() {
		assertAll(
				() -> assertThat(new BooleanTransformer().transform("true"), is(true)),
				() -> assertThat(new BooleanTransformer().transform("false"), is(false)),
				() -> assertThat(new BooleanTransformer().transform("abc"), is(false)),
				() -> assertThat(new BooleanTransformer().transform("1"), is(false))
		);
	}

	@Test
	public void testTransformReturnsNullIfNullIsGiven() throws TransformationFailedException {
		assertThat(new BooleanTransformer().transform(null), is(nullValue()));
	}
}