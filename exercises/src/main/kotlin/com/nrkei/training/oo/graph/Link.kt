package com.nrkei.training.oo.graph

// Understands a connection from one Node to another
internal class Link internal constructor(private val cost: Double, private val target: Node) {
    companion object {
        internal val LEAST_COST = { cost: Double -> cost }
        internal val FEWEST_HOPS = { _: Double -> 1.0 }

        internal fun List<Link>.cost() = this.sumOf { it.cost }
    }

    internal fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Double {
        return target.cost(destination, visitedNodes, strategy) + strategy(cost)
    }

    internal fun path(destination: Node, visitedNodes: List<Node>): Path? {
        return target.path(destination, visitedNodes)?.also { it.prepend(this) }
    }
}

internal typealias CostStrategy = (Double) -> Double