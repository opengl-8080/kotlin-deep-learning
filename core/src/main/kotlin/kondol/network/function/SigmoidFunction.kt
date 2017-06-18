package kondol.network.function

import kondol.matrix.Matrix
import kondol.matrix.operators.*

class SigmoidFunction: ActivationFunction {
    
    override fun invoke(matrix: Matrix) = 1.0 / (1.0 + (-matrix).exp())
}
