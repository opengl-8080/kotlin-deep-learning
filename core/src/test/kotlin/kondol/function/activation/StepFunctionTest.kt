package kondol.function.activation

import kondol.function.activation.StepFunction
import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class StepFunctionTest {

    @Test
    fun test_step_function() {
        // setup
        val stepFunction = StepFunction()
        val matrix = Matrix(-0.1, 0.0, 0.1)
        
        // exercise
        val actual = stepFunction(matrix)
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            doubleArrayOf(0.0, 0.0, 1.0)
        ))
    }
}