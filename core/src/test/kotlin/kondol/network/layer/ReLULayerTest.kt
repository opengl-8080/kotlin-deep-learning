package kondol.network.layer

import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class ReLULayerTest {

    @Test
    fun test_forward() {
        // setup
        val layer = ReLULayer()
        val matrix = Matrix(-0.1, 0.0, 0.1)

        // exercise
        val actual = layer.forward(matrix)

        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            0.0, 0.0, 0.1
        ))
    }

    @Test
    fun test_backward() {
        // setup
        val layer = ReLULayer()
        val x = Matrix(-0.1, 0.0, 0.1)
        layer.forward(x)

        // exercise
        val dL = Matrix(2.0, 3.0, 4.0)
        val actual = layer.backward(dL)

        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            0.0, 0.0, 4.0
        ))
    }

    @Test
    fun throw_exception_if_backward_is_invoked_without_forward() {
        // setup
        val layer = ReLULayer()
        
        // exercise, verify
        val dL = Matrix(2.0, 3.0, 4.0)
        Assertions.assertThatThrownBy { layer.backward(dL) }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessage("this layer's forward() method is not invoked yet.")
    }
}