package com.adammcneilly.wavelength

/**
 * @property[pointMarker] Is where the 4 point indicator is. This should be a number between 0 and 180
 *  where 0 is the leftmost part of the spectrum, and 180 being on the rightmost part of the spectrum.
 * @property[guessMarker] Is where the guessing team has placed the pointer. This should be a number between 0 and 180
 *  *  where 0 is the leftmost part of the spectrum, and 180 being on the rightmost part of the spectrum.
 */
data class SpectrumState(
    val isCovered: Boolean,
    val pointMarker: Int,
    val guessMarker: Int,
)
