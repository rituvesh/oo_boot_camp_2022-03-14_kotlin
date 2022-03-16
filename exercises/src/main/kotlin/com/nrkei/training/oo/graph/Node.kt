/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph

class Node {
    companion object {
        private const val UNREACHABLE = -1
    }

    private val neighbors = mutableListOf<Node>()

    infix fun to(neighbor: Node) = neighbor.also { neighbors.add(neighbor) }

    infix fun canReach(destination: Node) = this.canReach(destination, noVisitedNodes)

    infix fun hopCount(destination: Node) = this.hopCount(destination, noVisitedNodes).also {
        require(it != UNREACHABLE) { "Destination cannot be reached" }
    }

    private fun hopCount(destination: Node, visitedNodes: MutableList<Node>): Int {
        if (this == destination) return 0
        if (this in visitedNodes) return UNREACHABLE
        visitedNodes.add(this)
        neighbors.forEach { neighbor ->
            neighbor.hopCount(destination, visitedNodes).also { neighborHopCount ->
                if (neighborHopCount != UNREACHABLE) return neighborHopCount + 1
            }
        }
        return UNREACHABLE
    }

    private fun canReach(destination: Node, visitedNodes: MutableList<Node>): Boolean {
        if (this == destination) return true
        if (this in visitedNodes) return false
        visitedNodes.add(this)
        return neighbors.any { it.canReach(destination, visitedNodes) }
    }

    private val noVisitedNodes get() = mutableListOf<Node>()
}