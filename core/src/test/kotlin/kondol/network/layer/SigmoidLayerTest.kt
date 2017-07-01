package kondol.network.layer

import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class SigmoidLayerTest {

    @Test
    fun test_forward() {
        // setup
        val sigmoid = SigmoidLayer()
        val x = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )

        // exercise
        val actual = sigmoid.forward(x)

        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            doubleArrayOf(s(1.0), s(2.0), s(3.0)),
            doubleArrayOf(s(4.0), s(5.0), s(6.0))
        ))
    }

    @Test
    fun test_backward() {
        // setup
        val sigmoid = SigmoidLayer()
        val x = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        val y = sigmoid.forward(x)

        // exercise
        val dL = Matrix(
            intArrayOf(9, 8, 7),
            intArrayOf(6, 5, 4)
        )
        val actual = sigmoid.backward(dL)
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            doubleArrayOf(dL[0, 0]*y[0, 0]*(1-y[0, 0]),   dL[0, 1]*y[0, 1]*(1-y[0, 1]),   dL[0, 2]*y[0, 2]*(1-y[0, 2])),
            doubleArrayOf(dL[1, 0]*y[1, 0]*(1-y[1, 0]),   dL[1, 1]*y[1, 1]*(1-y[1, 1]),   dL[1, 2]*y[1, 2]*(1-y[1, 2]))
        ))
    }

    @Test
    fun throw_exception_if_backward_is_invoked_without_forward() {
        // setup
        val sigmoid = SigmoidLayer()
        val dL = Matrix(
            intArrayOf(9, 8, 7),
            intArrayOf(6, 5, 4)
        )

        // exercise, verify
        Assertions.assertThatThrownBy { sigmoid.backward(dL) }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessage("this layer's forward() method is not invoked yet.")
    }

    private fun s(d: Double) = 1.0 / (1.0 + Math.exp(-d))
}
