package com.nrkei.training.oo.graph

// Understands a connection from one Node to another
internal class Link internal constructor(private val cost: Double, private val target: Node) {
    companion object {
        internal fun List<Link>.cost() = this.sumOf { it.cost }
    }

    internal fun paths(destination: Node, visitedNodes: List<Node>) =
        target.paths(destination, visitedNodes).onEach { it.prepend(this) }
}
