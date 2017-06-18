package kondol.network.function

import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class SigmoidFunctionTest {
    @Test
    fun test_sigmoid_function() {
        // setup
        val sigmoid = SigmoidFunction()
        val matrix = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        
        // exercise
        val actual = sigmoid(matrix)
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            doubleArrayOf(s(1.0), s(2.0), s(3.0)),
            doubleArrayOf(s(4.0), s(5.0), s(6.0))
        ))
    }
    
    private fun s(d: Double) = 1.0 / (1.0 + Math.exp(-d))
}
