package kondol.network

import kondol.function.ActivationFunction
import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class LayerImplTest {

    @Test
    fun test_forward() {
        // setup
        val x = Matrix(1, 2, 3)
        val weight = Matrix(
            intArrayOf(1, 2),
            intArrayOf(3, 4),
            intArrayOf(5, 6)
        )
        val bias = Matrix(0.1, 0.2)
        val activationFunction = object : ActivationFunction {
            override fun invoke(matrix: Matrix): Matrix {
                return matrix + 100
            }
        }
        val layer = LayerImpl(weight = weight, bias = bias, activationFunction = activationFunction)
        
        // exercise
        val actual = layer.forward(x)
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            (1*1 + 2*3 + 3*5) + 0.1 + 100, (1*2 + 2*4 + 3*6) + 0.2 + 100
        ))
    }
}