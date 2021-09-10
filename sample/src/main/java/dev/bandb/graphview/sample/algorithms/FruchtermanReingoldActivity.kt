package dev.bandb.graphview.sample.algorithms

import android.widget.TextView
import dev.bandb.graphview.decoration.edge.ArrowEdgeDecoration
import dev.bandb.graphview.graph.Graph
import dev.bandb.graphview.graph.Node
import dev.bandb.graphview.layouts.GraphLayoutManager
import dev.bandb.graphview.layouts.energy.FruchtermanReingoldLayoutManager
import dev.bandb.graphview.sample.*
import kotlin.random.Random

class FruchtermanReingoldActivity : GraphActivity() {

    val listMillis = mutableListOf<Long>()

    public override fun setLayoutManager() {
        val iterations = intent.getIntExtra(ITERATIONS_KEY, 1000)
        val layout = FruchtermanReingoldLayoutManager(this, iterations)
        (layout as GraphLayoutManager).buildTimeListener = ::onBuildFinish
        recyclerView.layoutManager = layout
    }

    public override fun setEdgeDecoration() {
        recyclerView.addItemDecoration(ArrowEdgeDecoration())
    }

    public override fun createGraph(): Graph {
        val graph = Graph()
        val nodesCount = intent.getIntExtra(NODES_KEY, 50)
        val edgesCount = intent.getIntExtra(EDGES_KEY, 50)
        for (i in 0 until nodesCount)
            graph.addNode(Node(nodeText))

        for (i in 0 until edgesCount) {
            val i1 = Random.nextInt(0, nodesCount)
            val i2 = Random.nextInt(0, nodesCount)
            graph.addEdge(graph.nodes[i1], graph.nodes[i2])
        }
        return graph
    }

    private fun onBuildFinish(millis: Long) {
        listMillis.add(millis)
        if (listMillis.size == 2) {
            val msg = "Build1: ${listMillis[0]}  Build2: ${listMillis[1]}"
            findViewById<TextView>(R.id.tv_build_log).text = msg
        }
    }
}