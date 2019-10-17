import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class SharedParentTest {

    private val binaryTree = BinaryTree(
        root = Node(
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
    )

    @Test
    fun `Shared parent is correctly found for siblings`() {
        assertSharedParent(5,11,6)
    }

    @Test
    fun `Shared parent is correctly found for nodes with different depth`() {
        assertSharedParent(4,7,2)
    }

    @Test
    fun `Shared parent is found correctly if the nodes have the same value`() {
        assertSharedParent(11, 11, 6)
    }

    @Test
    fun `Shared parent is not found if one node is not present in tree`() {
        binaryTree.getSharedParent(123, 321) shouldBe BinaryTree.SharedParentResult.NotFound
    }

    @Test
    fun `(root, root) node pair should not have shared parent`() {
        binaryTree.getSharedParent(2, 2) shouldBe BinaryTree.SharedParentResult.NotFound
    }

    private fun assertSharedParent(node1: Int, node2: Int, expectedParentValue: Int) {
        when (val sharedParent = binaryTree.getSharedParent(node1, node2)) {
            is BinaryTree.SharedParentResult.Found -> sharedParent.node.value shouldBe expectedParentValue
            BinaryTree.SharedParentResult.NotFound -> sharedParent shouldBe expectedParentValue
        }
    }

}
