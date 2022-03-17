/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package com.nrkei.training.oo.graph

import com.nrkei.training.oo.graph.Link.Companion.FEWEST_HOPS
import com.nrkei.training.oo.graph.Link.Companion.LEAST_COST

class Node {
    companion object {
        private const val UNREACHABLE = Double.POSITIVE_INFINITY
    }

    private val links = mutableListOf<Link>()

    infix fun canReach(destination: Node) = this.cost(destination, noVisitedNodes, FEWEST_HOPS) != UNREACHABLE

    infix fun hopCount(destination: Node) = this.cost(destination, noVisitedNodes, FEWEST_HOPS).also {
        require(it != UNREACHABLE) { "Destination cannot be reached" }
    }.toInt()

    infix fun cost(destination: Node) = this.cost(destination, noVisitedNodes, LEAST_COST).also {
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
        infix fun to(neighbor: Node) = neighbor.also { links.add(Link(cost, neighbor))}
    }
}