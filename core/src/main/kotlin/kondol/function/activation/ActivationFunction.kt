package kondol.function.activation

import kondol.matrix.Matrix

interface ActivationFunction {
    
    operator fun invoke(matrix: Matrix): Matrix
    
    companion object {
        val SIGMOID = SigmoidFunction()
        val STEP = StepFunction()
        val ReLU = ReLUFunction()
        val IDENTITY = IdentityFunction()
    }
}
