/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.rectangle

// Understands a four-sided polygon with sides at right angles
class Rectangle(length: Number, width: Number) {
    private val length = length.toDouble()
    private val width = width.toDouble()

    init {
        require(this.length > 0.0 && this.width > 0.0)
        { "Each Rectangle dimension must be greater than zero" }
    }

    fun area() = length * width

    val area get() = area()

    fun perimeter() = 2 * (length + width)

    val perimeter get() = perimeter()
}
