package com.nrkei.training.oo.graph

internal class Link internal constructor(private val cost: Double, private val target: Node) {
    internal fun hopCount(destination: Node, visitedNodes: List<Node>): Double {
        return target.hopCount(destination, visitedNodes) + 1
    }

    internal fun cost(destination: Node, visitedNodes: List<Node>): Double {
        return target.cost(destination, visitedNodes) + cost
    }
}