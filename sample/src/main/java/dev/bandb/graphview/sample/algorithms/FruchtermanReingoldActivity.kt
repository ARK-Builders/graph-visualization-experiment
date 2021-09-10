package dev.bandb.graphview.sample.algorithms

import dev.bandb.graphview.decoration.edge.ArrowEdgeDecoration
import dev.bandb.graphview.graph.Graph
import dev.bandb.graphview.graph.Node
import dev.bandb.graphview.layouts.energy.FruchtermanReingoldLayoutManager
import dev.bandb.graphview.sample.GraphActivity
import kotlin.random.Random

class FruchtermanReingoldActivity : GraphActivity() {

    public override fun setLayoutManager() {
        recyclerView.layoutManager = FruchtermanReingoldLayoutManager(this, 1000)
    }

    public override fun setEdgeDecoration() {
        recyclerView.addItemDecoration(ArrowEdgeDecoration())
    }

    public override fun createGraph(): Graph {
        val graph = Graph()
        val nodesCount = 100
        val edgesCount = 200
        for (i in 0..nodesCount)
            graph.addNode(Node(nodeText))

        for (i in 0..edgesCount) {
            val i1 = Random.nextInt(0, nodesCount)
            val i2 = Random.nextInt(0, nodesCount)
            graph.addEdge(graph.nodes[i1], graph.nodes[i2])
        }
        return graph
    }
}