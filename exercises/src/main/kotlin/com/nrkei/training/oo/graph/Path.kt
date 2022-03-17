package com.nrkei.training.oo.graph

import com.nrkei.training.oo.graph.Link.Companion.cost

// Understands a route from one Node to another
abstract class Path internal constructor() {
    internal open infix fun prepend(link: Link)  { }

    abstract fun cost(): Double

    abstract fun hopCount(): Int

    internal class ActualPath internal constructor() : Path() {
        private val links = mutableListOf<Link>()

        override infix fun prepend(link: Link) = links.add(0, link)

        override fun cost() = links.cost()

        override fun hopCount() = links.size
    }

    internal object None: Path() {
        override fun cost() = Double.POSITIVE_INFINITY
        override fun hopCount() = Int.MAX_VALUE
    }
}

internal typealias PathStrategy = (Path) -> Number