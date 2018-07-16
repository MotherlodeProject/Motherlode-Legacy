package contrivitive.util;

import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public class Syncable {
	private IntSupplier intSupplier;
	private IntConsumer intConsumer;
	private boolean isShort;

	public Syncable(IntSupplier intSupplier, IntConsumer intConsumer) {
		this(intSupplier, intConsumer, false);
	}

	public Syncable(IntSupplier intSupplier, IntConsumer intConsumer, boolean isShort) {
		this.intSupplier = intSupplier;
		this.intConsumer = intConsumer;
		this.isShort = isShort;
	}

	public IntSupplier getIntSupplier() {
		return intSupplier;
	}

	public IntConsumer getIntConsumer() {
		return intConsumer;
	}

	public boolean isShort() {
		return isShort;
	}
}
