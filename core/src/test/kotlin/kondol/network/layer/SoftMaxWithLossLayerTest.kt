package kondol.network.layer

import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class SoftMaxWithLossLayerTest {

    @Test
    fun test_forward() {
        // setup
        val layer = SoftMaxWithLossLayer()
        val x = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        val t = Matrix(
            intArrayOf(0, 0, 1),
            intArrayOf(0, 1, 0)
        )
        
        // exercise
        val actual = layer.forward(x, t)
        
        // verify
        Assertions.assertThat(actual).isEqualTo(
            this.crossEntropyError(this.softMax(x), t)
        )
    }

    @Test
    fun test_backward() {
        // setup
        val layer = SoftMaxWithLossLayer()
        val x = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        val t = Matrix(
            intArrayOf(0, 0, 1),
            intArrayOf(0, 1, 0)
        )
        layer.forward(x, t)

        // exercise
        val actual = layer.backward(1.0)

        // verify
        val y = this.softMax(x)
        Assertions.assertThat(actual).isEqualTo((y-t)/x.rowSize.toLong())
    }

    @Test
    fun throw_exception_if_backward_is_invoked_without_forward() {
        // setup
        val layer = SoftMaxWithLossLayer()

        // exercise, verify
        Assertions.assertThatThrownBy { layer.backward(1.0) }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessage("this layer's predict() method is not invoked yet.")
    }

    private fun softMax(matrix: Matrix): Matrix {
        val max = matrix.max()
        val tmp = matrix - max
        val exp = tmp.exp()
        return exp / exp.sum()
    }
    
    private fun crossEntropyError(y: Matrix, t: Matrix): Double {
        return -(t * (y + 1e-7).log()).sum()
    }
}
