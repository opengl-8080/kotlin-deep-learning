package kondol.function

import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class SoftMaxFunctionTest {

    @Test
    fun test_softMax_function() {
        // setup
        val softMaxFunction = SoftMaxFunction()
        val matrix = Matrix(
            doubleArrayOf(1.1, 2.3),
            doubleArrayOf(1.4, 5.1)
        )
        
        // exercise
        val actual = softMaxFunction(matrix)
        
        // verify
        val exp1 = Math.exp(1.1)
        val exp2 = Math.exp(2.3)
        val exp3 = Math.exp(1.4)
        val exp4 = Math.exp(5.1)
        val sum = exp1 + exp2 + exp3 + exp4
        
        Assertions.assertThat(actual).isEqualTo(Matrix(
            doubleArrayOf(exp1 / sum, exp2 / sum),
            doubleArrayOf(exp3 / sum, exp4 / sum)
        ))
    }
}
