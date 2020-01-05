package net.sepptember.lib.cliargs.internal;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.Supplier;

class TransformerFactory {

	private static final Map<Class<?>, Supplier<Transformer<?>>> mapping = Map.ofEntries(
			Map.entry(String.class, StringTransformer::new),
			Map.entry(Integer.class, IntegerTransformer::new),
			Map.entry(int.class, IntegerTransformer::new),
			Map.entry(Byte.class, ByteTransformer::new),
			Map.entry(byte.class, ByteTransformer::new),
			Map.entry(Short.class, ShortTransformer::new),
			Map.entry(short.class, ShortTransformer::new)
	);

	Transformer createTransformerFor(Field field) throws NoSuchTransformerException {
		Class<?> fieldType = field.getType();
		if (mapping.containsKey(fieldType)) {
			return mapping.get(fieldType).get();
		}
		throw new NoSuchTransformerException(fieldType);
	}
}
