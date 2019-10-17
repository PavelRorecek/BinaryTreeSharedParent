import Node.PathResult.Found
import Node.PathResult.NotFound
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class PathFromRootTest {

    private val binaryTree = Node(
        value = 2,
        left = Node(
            value = 7,
            left = Node(2),
            right = Node(
                value = 6,
                left = Node(5),
                right = Node(11)
            )
        ),
        right = Node(
            value = 5,
            right = Node(
                value = 9,
                left = Node(4)
            )
        )
    )

    @Test
    fun `Path to node which has sibling should be correct`() {
        assertPathToNode(
            nodeValue = 11,
            expectedPath = listOf(2, 7, 6, 11)
        )
    }

    @Test
    fun `Path to node which doesn't have sibling should be correct`() {
        assertPathToNode(
            nodeValue = 4,
            expectedPath = listOf(2, 5, 9, 4)
        )
    }

    @Test
    fun `Path to root should contain only root node`() {
        assertPathToNode(
            nodeValue = binaryTree.value,
            expectedPath = listOf(binaryTree.value)
        )
    }

    @Test
    fun `Path to node that is not in tree should not exist`() {
        binaryTree.pathToValue(123) shouldBe NotFound
    }

    private fun assertPathToNode(nodeValue: Int, expectedPath: List<Int>) {
        when (val actualPath = binaryTree.pathToValue(nodeValue)) {
            is Found -> actualPath.path.map { it.value } shouldBe expectedPath
            NotFound -> actualPath shouldBe expectedPath
        }
    }

}
