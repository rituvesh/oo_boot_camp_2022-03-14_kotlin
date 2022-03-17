package com.nrkei.training.oo.graph

internal class Link internal constructor(private val cost: Double, private val target: Node) {
    companion object {
        internal val LEAST_COST = { cost: Double -> cost }
    }
    internal fun hopCount(destination: Node, visitedNodes: List<Node>): Double {
        return target.hopCount(destination, visitedNodes) + 1
    }

    internal fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Double {
        return target.cost(destination, visitedNodes, strategy) + strategy(cost)
    }
}

internal typealias CostStrategy = (Double) -> Double