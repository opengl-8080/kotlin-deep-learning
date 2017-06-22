package kondol.mnist

import java.nio.file.Path
import java.nio.file.Paths

class Mnist(private val downloadDir: Path) {
    
    companion object {
        private val DEFAULT_DOWNLOAD_DIR = Paths.get("mnist")
        private val TRAINING_IMAGE_FILE = Paths.get("training-image")
        private val TRAINING_LABEL_FILE = Paths.get("training-label")
        private val TEST_IMAGE_FILE = Paths.get("test-image")
        private val TEST_LABEL_FILE = Paths.get("test-label")
        
        fun download(downloadDir: Path = DEFAULT_DOWNLOAD_DIR): Mnist {
            MnistDownloader(downloadDir, TRAINING_IMAGE_FILE, TRAINING_LABEL_FILE, TEST_IMAGE_FILE, TEST_LABEL_FILE).download()
            return Mnist(downloadDir)
        }
    }
    
    
}
