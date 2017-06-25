package kondol.function.activation

import kondol.function.MatrixFunction

interface ActivationFunction: MatrixFunction {
    
    companion object {
        val SIGMOID = SigmoidFunction()
        val STEP = StepFunction()
        val ReLU = ReLUFunction()
        val IDENTITY = IdentityFunction()
    }
}
