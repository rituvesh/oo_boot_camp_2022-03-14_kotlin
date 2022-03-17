/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph

import com.nrkei.training.oo.graph.Link.Companion.FEWEST_HOPS
import com.nrkei.training.oo.graph.Link.Companion.LEAST_COST
import com.nrkei.training.oo.graph.Path.ActualPath

// Understands its neighbors
class Node {
    companion object {
        private const val UNREACHABLE = Double.POSITIVE_INFINITY
    }

    private val links = mutableListOf<Link>()

    infix fun canReach(destination: Node) = this.cost(destination, noVisitedNodes, FEWEST_HOPS) != UNREACHABLE

    infix fun hopCount(destination: Node) = this.cost(destination, FEWEST_HOPS).toInt()

    infix fun cost(destination: Node) = cost(destination, LEAST_COST)

    infix fun path(destination: Node) = this.path(destination, noVisitedNodes).also {
        require(it is ActualPath) { "Destination cannot be reached" }
    }

    internal fun path(destination: Node, visitedNodes: List<Node>): Path {
        if (this == destination) return ActualPath()
        if (this in visitedNodes) return Path.None
        return links
            .map { link -> link.path(destination, visitedNodes + this) }
            .minByOrNull { it.cost() }
            ?: Path.None
    }

    private fun cost(destination: Node, strategy: CostStrategy) =
        this.cost(destination, noVisitedNodes, strategy).also {
            require(it != UNREACHABLE) { "Destination cannot be reached" }
        }

    internal fun cost(destination: Node, visitedNodes: List<Node>, strategy: CostStrategy): Double {
        if (this == destination) return 0.0
        if (this in visitedNodes) return UNREACHABLE
        return links
            .minOfOrNull { link -> link.cost(destination, visitedNodes + this, strategy) }
            ?: UNREACHABLE
    }

    private val noVisitedNodes = emptyList<Node>()

    infix fun cost(amount: Number) = LinkBuilder(amount.toDouble(), links)

    class LinkBuilder internal constructor(private val cost: Double, private val links: MutableList<Link>) {
        infix fun to(neighbor: Node) = neighbor.also { links.add(Link(cost, neighbor)) }
    }
}