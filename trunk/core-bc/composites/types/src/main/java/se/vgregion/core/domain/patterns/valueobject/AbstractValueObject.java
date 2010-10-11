package se.vgregion.core.domain.patterns.valueobject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Supporting base class for value objects.
 * <p/>
 * While the ValueObject interface makes the pattern properties explicit, this class is less general and is suited
 * for this particular application.
 * </p>
 * For example, the private id field is meant for autogenerated, surrogate primary keys. Also, you may want more
 * flexibility in selecting significant fields for comparision, or you may need to be able to calculate equals
 * millions of times every second, in which case reflection might not be fast enough.
 * 
 * @param <T>
 *            the Type of the Value Object
 * 
 * @author Anders Asplund - <a href="http://www.callistaenterprise.se">Callista Enterprise</a>
 */
public abstract class AbstractValueObject<T extends ValueObject<T>> implements ValueObject<T> {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private final Long _primaryKey = null;
    private transient int _cachedHashCode = 0;
    private static final String[] EXCLUDED_FIELDS = { "_primaryKey", "_cachedHashCode", "serialVersionUID" };

    /**
     * @param other
     *            The other value object.
     * @return True if all non-transient fields are equal.
     */
    public final boolean sameValueAs(final T other) {
        return other != null && EqualsBuilder.reflectionEquals(this, other, EXCLUDED_FIELDS);
    }

    /**
     * @return Hash code built from all non-transient fields.
     */
    @Override
    public final int hashCode() {
        // Using a local variable to ensure that we only do a single read
        // of the _cachedHashCode field, to avoid race conditions.
        // It doesn't matter if several threads compute the hash code and overwrite
        // each other, but it's important that we never return 0, which could happen
        // with multiple reads of the _cachedHashCode field.
        //
        // See java.lang.String.hashCode()
        int h = _cachedHashCode;
        if (h == 0) {
            // Lazy initialization of hash code.
            // Value objects are immutable, so the hash code never changes.
            h = HashCodeBuilder.reflectionHashCode(this, false);
            _cachedHashCode = h;
        }

        return h;
    }

    /**
     * @param other
     *            other object
     * @return True if other object has the same value as this value object.
     */
    @SuppressWarnings("unchecked")
    @Override
    public final boolean equals(final Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }

        return sameValueAs((T) other);
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames(
                EXCLUDED_FIELDS).toString();
    }
}
