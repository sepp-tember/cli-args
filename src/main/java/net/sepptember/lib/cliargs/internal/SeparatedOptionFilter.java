package net.sepptember.lib.cliargs.internal;

import java.lang.reflect.Field;

public class SeparatedOptionFilter {
	private final Object target;
	private final Field field;
	private final Transformer<?> transformer;
	private final String option;
	private SeparatedOptionFilter nextFilter;

	public SeparatedOptionFilter(Object target, Field field, String option) throws NoSuchTransformerException {
		this(target, field, option, new TransformerFactory().createTransformerFor(field.getType()));
	}

	SeparatedOptionFilter(Object target, Field field, String option, Transformer<?> transformer) {
		this.target = target;
		this.field = field;
		field.setAccessible(true);
		this.option = option;
		this.transformer = transformer;
	}

	public ImmutableList<String> process(ImmutableList<String> args) {
		if (!args.isEmpty() && option.equals(args.get(0))) {
			int size = args.size();
			try {
				Transformer.Result<?> result = transformer.transform(args.subList(1, size));
				Object value = result.getValue();
				field.set(target, value);
				return result.getRemainingArguments();
			} catch (IllegalAccessException e) {
				// TODO use logger
				e.printStackTrace();
			} catch (TransformationFailedException e) {
				// TODO decide what to do when transformation fails
				e.printStackTrace();
			}
			return args.subList(Math.min(2, size), size);
		} else {
			if (nextFilter == null) {
				return args;
			} else {
				return nextFilter.process(args);
			}
		}
	}

	public SeparatedOptionFilter add(SeparatedOptionFilter nextFilter) {
		this.nextFilter = nextFilter;
		return this;
	}
}
