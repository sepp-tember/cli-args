package net.sepptember.lib.cliargs.internal;

import java.util.List;

class TransformerFactory {

	private final List<Transformer<?>> transformers = List.of(
			new StringTransformer(),
			new IntegerTransformer(),
			new IntegerPrimitiveTransformer(),
			new ByteTransformer(),
			new BytePrimitiveTransformer(),
			new ShortTransformer(),
			new ShortPrimitiveTransformer(),
			new LongTransformer(),
			new LongPrimitiveTransformer(),
			new DoubleTransformer(),
			new DoublePrimitiveTransformer(),
			new FloatTransformer(),
			new FloatPrimitiveTransformer(),
			new BooleanTransformer(),
			new BooleanPrimitiveTransformer()
	);

	@SuppressWarnings("unchecked")
	<T> Transformer<T> createTransformerFor(Class<T> type) throws NoSuchTransformerException {
		return (Transformer<T>) transformers.stream()
				.filter(transformer -> transformer.resultType() == type)
				.findAny()
				.orElseThrow(() -> new NoSuchTransformerException(type));
	}
}
