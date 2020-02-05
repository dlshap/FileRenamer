class FileLister {

    FileLister() {
        this.start()
    }

    static main(args) {
        new FileLister()
    }

    def start() {

        def startDir = "\\This PC\\David's iPhone\\"

        println "Starting at: $startDir"
        File fileDir = new File(startDir)
        printdir(fileDir, 0)
    }

    def spaces(int n) {
        def spaces = ""
        (0..n).each {spaces += " "}
        spaces
    }

    def printdir(File fileDir, int spaceCount) {
        def newSpaceCount = spaceCount + 2
        fileDir.eachFile { File eachFile ->
            if (eachFile.isDirectory()) {
                println "\n${spaces(newSpaceCount)}Dir: $eachFile.name"
                printdir(eachFile, newSpaceCount)
            } else {
                println "${spaces(newSpaceCount)}${eachFile.name}"
            }
        }
    }

}
