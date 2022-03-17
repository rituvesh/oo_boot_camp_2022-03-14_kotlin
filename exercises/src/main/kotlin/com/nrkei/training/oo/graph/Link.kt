package com.nrkei.training.oo.graph

internal class Link internal constructor(private val target: Node) {
    internal fun hopCount(destination: Node, visitedNodes: List<Node>): Double {
        return target.hopCount(destination, visitedNodes) + 1
    }
}