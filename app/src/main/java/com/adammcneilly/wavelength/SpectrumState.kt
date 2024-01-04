package com.adammcneilly.wavelength

/**
 * @property[pointMarkerPct] Is where the 4 point indicator is. This is a percentage from 0F to 1F.
 * @property[guessMarkerPct] Is where the guessing team has placed the pointer. This is a percentage from 0F to 1F.
 */
data class SpectrumState(
    val isCovered: Boolean,
    val pointMarkerPct: Float,
    val guessMarkerPct: Float,
)
