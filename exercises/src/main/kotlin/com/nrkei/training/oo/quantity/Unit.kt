/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.quantity

class Unit {

    companion object {
        private val TEASPOON = Unit()
        private val TABLESPOON = Unit(3, TEASPOON)
        private val OUNCE = Unit(2, TABLESPOON)
        private val CUP = Unit(8, OUNCE)
        private val PINT = Unit(2, CUP)
        private val QUART = Unit(2, PINT)
        private val GALLON = Unit(4, QUART)

        val Number.teaspoons get() = Quantity(this, TEASPOON)
        val Number.tablespoons get() = Quantity(this, TABLESPOON)
        val Number.ounces get() = Quantity(this, OUNCE)
        val Number.cups get() = Quantity(this, CUP)
        val Number.pints get() = Quantity(this, PINT)
        val Number.quarts get() = Quantity(this, QUART)
        val Number.gallons get() = Quantity(this, GALLON)

        private val INCH = Unit()
        private val FOOT = Unit(12, INCH)
        private val YARD = Unit(3, FOOT)
        private val FATHOM = Unit(6, FOOT)
        private val CHAIN = Unit(22, YARD)
        private val FURLONG = Unit(10, CHAIN)
        private val MILE = Unit(8, FURLONG)
        private val LEAGUE = Unit(3, MILE)

        val Number.inches get() = Quantity(this, INCH)
        val Number.feet get() = Quantity(this, FOOT)
        val Number.yards get() = Quantity(this, YARD)
        val Number.fathoms get() = Quantity(this, FATHOM)
        val Number.chains get() = Quantity(this, CHAIN)
        val Number.furlongs get() = Quantity(this, FURLONG)
        val Number.miles get() = Quantity(this, MILE)
        val Number.leagues get() = Quantity(this, LEAGUE)

        private val CELSIUS = Unit()
        private val FAHRENHEIT = Unit(5/9.0, 32, CELSIUS)

        val Number.celsius get() = Quantity(this, CELSIUS)
        val Number.fahrenheit get() = Quantity(this, FAHRENHEIT)
    }

    private val baseUnit: Unit
    private val baseUnitRatio: Double
    private val offset: Double

    private constructor() {
        baseUnit = this
        baseUnitRatio = 1.0
        offset = 0.0
    }

    private constructor(relativeRatio: Number, relativeUnit: Unit) :
            this(relativeRatio, 0.0, relativeUnit)

    private constructor(relativeRatio: Number, offset: Number, relativeUnit: Unit) {
        baseUnit = relativeUnit.baseUnit
        baseUnitRatio = relativeRatio.toDouble() * relativeUnit.baseUnitRatio
        this.offset = offset.toDouble()
    }

    internal fun convertedAmount(otherAmount: Double, other: Unit) =
        ((otherAmount - other.offset) * other.baseUnitRatio / this.baseUnitRatio + this.offset).also {
            require(this.isCompatible(other)) { "Units are not compatible" }
        }

    internal fun hashCode(amount: Double) = ((amount - offset) * baseUnitRatio).hashCode()

    internal fun isCompatible(other: Unit) = this.baseUnit == other.baseUnit
}