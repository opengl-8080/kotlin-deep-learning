package kondol.network

import kondol.matrix.Matrix
import kondol.network.layer.Layer

class NeuralNetwork (
    private vararg val layers: Layer
) {
    constructor(layerList: List<Layer>): this(*layerList.toTypedArray())
    
    fun forward(x: Matrix) = this.layers.fold(x) {accumulation, layer -> layer.forward(accumulation)}
}

