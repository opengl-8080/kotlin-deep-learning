package kondol.function

import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class ReLUFunctionTest {

    @Test
    fun test_ReLU_function() {
        // setup
        val reLUFunction = ReLUFunction()
        val matrix = Matrix(-0.1, 0.0, 0.1)
        
        // exercise
        val actual = reLUFunction(matrix)
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            0.0, 0.0, 0.1
        ))
    }
}