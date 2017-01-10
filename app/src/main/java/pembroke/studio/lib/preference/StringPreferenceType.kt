package pembroke.studio.lib.preference

/**
 * Represents a single, persistent String preference value.
 */
interface StringPreferenceType {
    /**
     * Get the current value of the preference.
     */
    fun get(): String?

    /**
     * Returns whether a value has been explicitly set for the preference.
     */
    fun isSet(): Boolean

    /**
     * Set the preference to a value.
     */
    fun set(value: String)

    /**
     * Delete the currently stored preference.
     */
    fun delete()
}