/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.unit

import com.nrkei.training.oo.order.bestOrNull
import com.nrkei.training.oo.probability.Chance
import com.nrkei.training.oo.quantity.RatioQuantity
import com.nrkei.training.oo.quantity.Unit.Companion.celsius
import com.nrkei.training.oo.quantity.Unit.Companion.cups
import com.nrkei.training.oo.quantity.Unit.Companion.fahrenheit
import com.nrkei.training.oo.quantity.Unit.Companion.gallons
import com.nrkei.training.oo.quantity.Unit.Companion.ounces
import com.nrkei.training.oo.quantity.Unit.Companion.quarts
import com.nrkei.training.oo.rectangle.Rectangle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class OrderableTest {

    @Test fun `rectangle with largest area`() {
        assertEquals(36.0, listOf(Rectangle(2, 3), Rectangle.square(6.0), Rectangle(5, 7.0)).bestOrNull()?.area())
        assertNull(emptyList<Rectangle>().bestOrNull())
    }

    @Test internal fun `least likely chance`() {
        assertEquals(Chance(0), listOf(Chance(0.1), Chance(0.7), Chance(0), Chance(0.9)).bestOrNull())
        assertNull(emptyList<Chance>().bestOrNull())
    }

    @Test fun `largest Quantity`() {
        assertEquals(2.quarts, listOf(
            0.2.gallons, 24.ounces, 0.5.gallons, 7.cups).bestOrNull())
        assertNull(emptyList<RatioQuantity>().bestOrNull())
        assertEquals(100.celsius, listOf(
            212.fahrenheit, 0.celsius, 50.fahrenheit, (-40).celsius).bestOrNull())
    }
}
