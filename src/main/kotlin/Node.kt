import Node.PathResult.Found
import Node.PathResult.NotFound

data class Node(val value: Int, val left: Node? = null, val right: Node? = null) {

    fun pathToValue(value: Int): PathResult {
        return if (this.value == value) {
            Found(listOf(this))
        } else {
            listOfNotNull(left, right)
                .map { node ->
                    val pathResult = node.pathToValue(value)

                    if (pathResult is Found) {
                        Found(pathResult.path.prependNode(this))
                    } else {
                        pathResult
                    }
                }
                .filterIsInstance<Found>()
                .getOrElse(0) { NotFound }
        }
    }

    private fun List<Node>.prependNode(node: Node): List<Node> {
        return mutableListOf(node).apply {
            addAll(this@prependNode)
        }
    }

    sealed class PathResult {
        data class Found(val path: List<Node>) : PathResult()
        object NotFound : PathResult()
    }

}
