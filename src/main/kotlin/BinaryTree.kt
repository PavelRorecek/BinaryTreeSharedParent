import BinaryTree.SharedParentResult.*
import Node.PathResult

class BinaryTree(private val root: Node) {

    fun getSharedParent(value1: Int, value2: Int): SharedParentResult {
        return when {
            value1 == root.value || value2 == root.value -> NotFound
            value1 == value2 -> handleIdenticalNodes(value1)
            else -> handleNonIdenticalNodes(value1, value2)
        }
    }

    private fun handleNonIdenticalNodes(value1: Int, value2: Int): SharedParentResult {
        val path1 = root.pathToValue(value1)
        val path2 = root.pathToValue(value2)

        return if (path1 is PathResult.Found && path2 is PathResult.Found) {
            val sharedParent = path1.path
                .zip(path2.path)
                .takeWhile { pair -> pair.first == pair.second }
                .lastOrNull()
                ?.first

            if (sharedParent != null) Found(sharedParent)
            else NotFound
        } else {
            NotFound
        }
    }

    private fun handleIdenticalNodes(value: Int): SharedParentResult {
        val pathResult = root.pathToValue(value)

        return if (pathResult is PathResult.Found) {
            Found(pathResult.path.takeLast(2).first())
        } else {
            NotFound
        }
    }

    sealed class SharedParentResult {
        data class Found(val node: Node) : SharedParentResult()
        object NotFound : SharedParentResult()
    }

}
