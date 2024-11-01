package com.github.shortmikelon.univerandroidlabs.screens.animations


data class AnimationsState(
    val boxLocation: Int = LOCATION_CENTER,
    val boxColor: Int = RED,
    val transparent: Float = VISIBLE,
    val rotation: Float = ANGLE_0,
) {

    companion object {
        const val LOCATION_LEFT = 1
        const val LOCATION_CENTER = 2
        const val LOCATION_RIGHT = 3

        const val BLUE = 0
        const val RED = 1

        const val ANGLE_45 = 0.45f
        const val ANGLE_0 = 0f

        const val VISIBLE = 1f
        const val TRANSPARENT = 0f
    }

}
