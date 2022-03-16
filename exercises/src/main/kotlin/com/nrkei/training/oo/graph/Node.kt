/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph

class Node {
    companion object {
        private const val UNREACHABLE = Double.POSITIVE_INFINITY
    }

    private val neighbors = mutableListOf<Node>()

    infix fun to(neighbor: Node) = neighbor.also { neighbors.add(neighbor) }

    infix fun canReach(destination: Node) = this.hopCount(destination, noVisitedNodes) != UNREACHABLE

    infix fun hopCount(destination: Node) = this.hopCount(destination, noVisitedNodes).also {
        require(it != UNREACHABLE) { "Destination cannot be reached" }
    }.toInt()

    private fun hopCount(destination: Node, visitedNodes: List<Node>): Double {
        if (this == destination) return 0.0
        if (this in visitedNodes) return UNREACHABLE
        var champion = UNREACHABLE
        neighbors.forEach { neighbor ->
            neighbor.hopCount(destination, visitedNodes + this).also { neighborHopCount ->
                if (neighborHopCount == UNREACHABLE) return@also
                if (champion == UNREACHABLE || neighborHopCount + 1 < champion)
                    champion = neighborHopCount + 1
            }
        }
        return champion
    }

    private val noVisitedNodes = emptyList<Node>()
}